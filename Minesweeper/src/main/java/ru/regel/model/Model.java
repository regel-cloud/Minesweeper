package ru.regel.model;

import ru.regel.presenter.ModelListener;

import java.util.*;

public class Model {

    private int boardWidth;
    private int boardHeight;
    private int minesCount;
    private Tile[][] board;
    private final List<ModelListener> listeners = new ArrayList<>();
    private final Set<OpenedTile> openedTiles = new HashSet<>();

    public Model() {
        startGameWithMode(GameMode.DEFAULT_MODE);
    }

    public void update(int row, int column) {
        int notOpenedTilesCount = 0;
        if (!board[row][column].hasFlag()) {
            if (board[row][column].hasMine()) {
                finishGame();
                notifyListenersOnGameLost();
                openedTiles.clear();
            } else {
                openTile(row, column);
                notifyListenersOnOpenedTile();
            }
            for (Tile[] tiles : board) {
                for (Tile tile : tiles) {
                    if (!tile.isOpened()) {
                        notOpenedTilesCount++;
                    }
                }
            }
            if (isGameWon(notOpenedTilesCount)) {
                finishGame();
                notifyListenersOnGameWon();
                openedTiles.clear();
            }
        }
    }

    private OpenedTile getOpenedTile(int row, int column) {
        if (board[row][column].hasMine()) {
            return new MineTile(row, column);
        } else if (board[row][column].getSurroundingBombs() > 0) {
            return new NumberTile(row, column, board[row][column].getSurroundingBombs());
        } else {
            return new EmptyTile(row, column);
        }
    }

    private Set<OpenedTile> updateOpenTiles() {
        Set<OpenedTile> openTiles = new HashSet<>();
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column].isOpened()) {
                    openTiles.add(getOpenedTile(row, column));
                }
            }
        }
        return openTiles;
    }

    private boolean isGameWon(int notOpenedTilesCount) {
        return notOpenedTilesCount == minesCount;
    }

    private void finishGame() {
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                tile.setOpened(true);
            }
        }
        openedTiles.addAll(updateOpenTiles());
    }

    public void startNewGame() {
        init();
        generateMines();
        countMines();
    }

    public void init() {
        board = new Tile[boardHeight][boardWidth];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new Tile();
            }
        }
    }

    public void startGameWithMode(GameMode mode) {
        this.boardWidth = mode.width;
        this.boardHeight = mode.height;
        this.minesCount = mode.minesCount;
        startNewGame();
    }

    public void startNewSpecialGame(int boardWidth, int boardHeight, int minesCount) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.minesCount = minesCount;
        startNewGame();
    }

    public void exit() {
        System.exit(0);
    }

    public void changeFlag(int row, int col) {
        boolean isFlagged = board[row][col].hasFlag();
        board[row][col].setFlag(!isFlagged);
        if (board[row][col].hasFlag()) {
            notifyListenersPutFlag(row, col);
        } else {
            notifyListenersDeleteFlag(row, col);
        }
        notifyListenersUpdateLeftMinesCount();
    }

    public void generateMines() {
        int count = 0;
        Random random = new Random();
        while (count < minesCount) {
            int row = random.nextInt(boardHeight);
            int column = random.nextInt(boardWidth);
            if (board[row][column].hasMine()) {
                continue;
            }
            board[row][column].setMine(true);
            count++;
        }
    }

    public void updateCount(int parameterRow, int parameterColumn) {
        if (!board[parameterRow][parameterColumn].hasMine()) {
            return;
        }
        for (int row = parameterRow - 1; row <= parameterRow + 1; row++) {
            for (int column = parameterColumn - 1; column <= parameterColumn + 1; column++) {
                if (row >= 0 && column >= 0 && row < board.length && column < board.length
                        && !board[row][column].hasMine()) {
                    board[row][column].surroundingBombs++;
                }
            }
        }
    }

    public void countMines() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                updateCount(row, col);
            }
        }
    }

    public void openTile(int parameterRow, int parameterColumn) {
        if (board[parameterRow][parameterColumn].getSurroundingBombs() != 0) {
            board[parameterRow][parameterColumn].setOpened(true);
        } else {
            for (int row = parameterRow - 1; row <= parameterRow + 1; row++) {
                for (int column = parameterColumn - 1; column <= parameterColumn + 1; column++) {
                    if (row >= 0 && column >= 0 && row < board.length && column < board.length
                            && !board[row][column].isOpened()) {
                        board[row][column].setOpened(true);
                        openTile(row, column);
                    }
                }
            }
        }
        openedTiles.addAll(updateOpenTiles());
    }

    private long getLeftMinesCount() {
        long flaggedCount = Arrays
                .stream(board)
                .flatMap(Arrays::stream)
                .filter(k -> (k.hasFlag() && k.hasMine())).count();
        return minesCount - flaggedCount;
    }

    private void notifyListenersUpdateLeftMinesCount() {
        for (final ModelListener listener : listeners) {
            listener.updateLeftMinesCount(getLeftMinesCount());
        }
    }

    private void notifyListenersOnGameWon() {
        for (final ModelListener listener : listeners) {
            listener.gameWon();
            listener.tilesOpened(openedTiles);
        }
    }

    private void notifyListenersOnGameLost() {
        for (final ModelListener listener : listeners) {
            listener.gameLost();
            listener.tilesOpened(openedTiles);
        }
    }

    private void notifyListenersOnOpenedTile() {
        for (final ModelListener listener : listeners) {
            listener.tilesOpened(openedTiles);
        }
    }

    private void notifyListenersDeleteFlag(int row, int col) {
        for (final ModelListener listener : listeners) {
            listener.deleteFlag(row, col);
        }
    }

    private void notifyListenersPutFlag(int row, int col) {
        for (final ModelListener listener : listeners) {
            listener.putFlag(row, col);
        }
    }

    public void addListenerToModel(ModelListener listener) {
        listeners.add(listener);
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void updateNearTile(int parameterRow, int parameterColumn) {
        if (board[parameterRow][parameterColumn].isOpened()
                && board[parameterRow][parameterColumn].getSurroundingBombs() > 0 &&
                countFlags(parameterRow, parameterColumn) == board[parameterRow][parameterColumn].getSurroundingBombs()) {
            openTile(parameterRow, parameterColumn);
        }
    }

    private int countFlags(int parameterRow, int parameterColumn) {
        int flagCount = 0;
        for (int row = parameterRow - 1; row <= parameterRow + 1; row++) {
            for (int column = parameterColumn - 1; column <= parameterColumn + 1; column++) {
                if (row >= 0 && column >= 0 && row < board.length && column < board.length
                        && !board[row][column].hasFlag()) {
                    flagCount++;
                }

            }
        }
        return flagCount;
    }
}



