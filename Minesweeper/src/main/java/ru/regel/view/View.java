package ru.regel.view;

import ru.regel.highscrore.HighScore;
import ru.regel.model.*;
import ru.regel.presenter.ViewListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class View {
    private final ArrayList<ViewListener> listeners;
    private GameWindow gameWindow;
    private final NameForm nameForm;
    private SpecialChoiceWindow specialChoiceWindow;

    public View() {
        this.listeners = new ArrayList<>();
        this.gameWindow = new GameWindow();
        this.gameWindow.setScreen();
        this.gameWindow.setMenu();
        this.gameWindow.setTopPanel();
        this.gameWindow.setDefaultBoard();
        this.gameWindow.setPanel();
        this.gameWindow.screen.add(gameWindow.getComposite());
        this.gameWindow.screen.pack();
        nameForm = new NameForm();
        addActionListenerToMenu();
        addActionListenerToSmileyButton();
        addActionListenerToBoardButton();
    }

    public void reset(int boardHeight, int boardWidth) {
        gameWindow.getScreen().dispose();
        gameWindow = new GameWindow();
        gameWindow.setScreen();
        gameWindow.setMenu();
        gameWindow.setTopPanel();
        gameWindow.setBoard(new Board(boardHeight, boardWidth));
        gameWindow.setPanel();
        gameWindow.getScreen().add(gameWindow.getComposite());
        gameWindow.getScreen().pack();
        addActionListenerToMenu();
        addActionListenerToSmileyButton();
        addActionListenerToBoardButton();
    }

    public void addActionListenerToMenu() {
        JMenuItem newGameMenu = gameWindow.getMenuPanel().getNewGameMenuItem();
        JMenuItem beginnerMenuItem = gameWindow.getMenuPanel().getBeginnerMenuItem();
        JMenuItem intermediateMenuItem = gameWindow.getMenuPanel().getIntermediateMenuItem();
        JMenuItem expertMenuItem = gameWindow.getMenuPanel().getExpertMenuItem();
        JMenuItem aboutMenuItem = gameWindow.getMenuPanel().getAboutMenuItem();
        JMenuItem specialMenuItem = gameWindow.getMenuPanel().getSpecialMenuItem();
        JMenuItem exitMenuItem = gameWindow.getMenuPanel().getExitMenuItem();
        JMenuItem recordsMenuItem = gameWindow.getMenuPanel().getRecordsMenuItem();

        ActionListener menuListener = ae -> {
            if (ae.getSource() == newGameMenu) {
                notifyListenersOnNewGameClicked();
            }
            if (ae.getSource() == beginnerMenuItem) {
                notifyListenersOnBeginnerGameClicked();
            }
            if (ae.getSource() == intermediateMenuItem) {
                notifyListenersOnInterGameClicked();
            }
            if (ae.getSource() == expertMenuItem) {
                notifyListenersOnExpertGameClicked();
            }
            if (ae.getSource() == specialMenuItem) {
                notifyListenersOnSpecialGameClicked();
            }
            if (ae.getSource() == recordsMenuItem) {
                notifyListenersOnRecordsClicked();
            }
            if (ae.getSource() == exitMenuItem) {
                notifyListenersOnExitGameClicked();
                if (gameWindow.getScreen() != null) {
                    gameWindow.getScreen().dispose();
                }
            }
            if (ae.getSource() == aboutMenuItem) {
                System.out.println("Minesweeper game");
            }
        };
        newGameMenu.addActionListener(menuListener);
        beginnerMenuItem.addActionListener(menuListener);
        intermediateMenuItem.addActionListener(menuListener);
        expertMenuItem.addActionListener(menuListener);
        specialMenuItem.addActionListener(menuListener);
        exitMenuItem.addActionListener(menuListener);
        aboutMenuItem.addActionListener(menuListener);
        recordsMenuItem.addActionListener(menuListener);
    }


    public void addActionListenerToSmileyButton() {
        gameWindow.getSmileyButton().addActionListener(e -> notifyListenersOnSmileyClicked());
    }

    public void addActionListenerToBoardButton() {
        JButton[][] board = gameWindow.getBoard().boardButtons;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int finalRow1 = row;
                int finalCol1 = col;
                board[row][col].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            notifyListenersOnLeftButtonClick(finalRow1, finalCol1);
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            notifyListenersOnRightButtonClick(finalRow1, finalCol1);
                        } else if (e.getButton() == MouseEvent.MOUSE_WHEEL) {
                            notifyListenersOnWheelClick(finalRow1, finalCol1);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
            }

        }
    }

    public void addListenerToSubmit() {
        nameForm.getButtonSubmit().addActionListener(e -> notifyListenersOnSubmitClicked());
    }


    private void addListenerToSpecialChoiceWindow() {
        int optionRows = specialChoiceWindow.getOptionRows();
        if (optionRows == JOptionPane.CANCEL_OPTION) {
            notifyListenersOnNewGameClicked();
            specialChoiceWindow.getPanel().setEnabled(false);
        } else if (optionRows == JOptionPane.OK_OPTION) {
            int minesCount = (Integer) specialChoiceWindow.getMineSpinner().getValue();
            int columnsCount = (Integer) specialChoiceWindow.getSpinnerCol().getValue();
            int rowsCount = (Integer) specialChoiceWindow.getSpinnerRows().getValue();
            notifyListenerOkOption(columnsCount, rowsCount, minesCount);
        }
    }

    public void closeNameWindow() {
        nameForm.getFrame().dispose();
    }

    public void showTable(List<HighScore> highScores) {
        HighScoreTable highScoreTable = new HighScoreTable();
        highScoreTable.showJTable(highScores);
    }

    public void updateLeftMinesCount(long leftMinesCount) {
        gameWindow.getMinesTextField().setText(String.valueOf(leftMinesCount));
    }

    public void showSpecialChoiceWindow() {
        specialChoiceWindow = new SpecialChoiceWindow();
        addListenerToSpecialChoiceWindow();
    }

    public void updateTime(String text) {
        gameWindow.getTimeTextField().setText(text);
    }

    public void openTiles(Set<OpenedTile> openedTiles) {
        ImageIcon icon;
        for (OpenedTile tile : openedTiles) {
            if (tile instanceof MineTile) {
                icon = getLoader().createPitImageIcon();
            } else if (tile instanceof NumberTile) {
                NumberTile numberTile = (NumberTile) tile;
                int number = numberTile.getSurroundingBombs();
                icon = createNumberImageIcon(number);
            } else {
                icon = getLoader().createEmptyIcon();
            }
            setImageToButton(tile.getRow(), tile.getColumn(), icon);
        }
    }

    private ImageIcon createNumberImageIcon(int number) {
        return getLoader().createNumberIcon(number);
    }

    private void setImageToButton(int row, int column, ImageIcon icon) {
        getBoardButtons()[row][column].setIcon(icon);
    }

    public void putFlag(int row, int col) {
        setImageToButton(row, col, getLoader().createFlagIcon());
    }

    public void deleteFlag(int row, int col) {
        setImageToButton(row, col, getLoader().createEmptyIcon());
    }

    public void showWinFace() {
        gameWindow.getSmileyButton().setIcon(getLoader().createSmileyIcon());
    }

    public void showSadFace() {
        gameWindow.getSmileyButton().setIcon(getLoader().createCryImageIcon());
    }

    public void showNameForm() {
        nameForm.getFrame().setVisible(true);
        addListenerToSubmit();
    }

    public void showNameFromForm() {
        String name = nameForm.getTextField().getText();
        notifyListenersNameIsRead(name);

    }

    private void notifyListenersOnWheelClick(int row, int column) {
        for (final ViewListener listener : listeners) {
            listener.onWheelClicked(row, column);
        }
    }

    private void notifyListenerOkOption(int columnsCount, int rowsCount, int minesCount) {
        for (final ViewListener listener : listeners) {
            listener.startSpecialGame(columnsCount, rowsCount, minesCount);
        }
    }

    private void notifyListenersOnRightButtonClick(int row, int col) {
        for (final ViewListener listener : listeners) {
            listener.onRightButtonClicked(row, col);
        }
    }

    private void notifyListenersOnLeftButtonClick(int row, int col) {
        for (final ViewListener listener : listeners) {
            listener.onLeftButtonClicked(row, col);
        }
    }

    private void notifyListenersOnExpertGameClicked() {
        for (final ViewListener listener : listeners) {
            listener.onNewGameClicked(GameMode.EXPERT_MODE);
        }
    }

    private void notifyListenersOnSpecialGameClicked() {
        for (final ViewListener listener : listeners) {
            listener.onSpecialGameClicked();
        }
    }

    private void notifyListenersOnInterGameClicked() {
        for (final ViewListener listener : listeners) {
            listener.onNewGameClicked(GameMode.INTERMEDIATE_MODE);
        }
    }

    private void notifyListenersOnExitGameClicked() {
        for (final ViewListener listener : listeners) {
            listener.onExitGameClicked();
        }
    }

    private void notifyListenersOnBeginnerGameClicked() {
        for (final ViewListener listener : listeners) {
            listener.onNewGameClicked(GameMode.BEGINNER_MODE);
        }
    }


    private void notifyListenersOnNewGameClicked() {
        for (final ViewListener listener : listeners) {
            listener.onNewGameClicked(GameMode.DEFAULT_MODE);
        }
    }

    private void notifyListenersOnSmileyClicked() {
        for (final ViewListener listener : listeners) {
            listener.onNewGameClicked(GameMode.DEFAULT_MODE);
        }
    }

    private void notifyListenersOnSubmitClicked() {
        for (final ViewListener listener : listeners) {
            listener.onNewSubmitClicked();
        }
    }

    private void notifyListenersOnRecordsClicked() {
        for (final ViewListener listener : listeners) {
            listener.onRecordsClicked();
        }
    }
    private void notifyListenersNameIsRead(String name) {
        for (final ViewListener listener : listeners) {
            listener.nameIsRead(name);
        }
    }

    public void addListener(final ViewListener listener) {
        listeners.add(listener);
    }

    private ImageLoader getLoader() {
        return gameWindow.getLoader();
    }

    private JButton[][] getBoardButtons() {
        return gameWindow.getBoard().getBoardButtons();
    }
}
