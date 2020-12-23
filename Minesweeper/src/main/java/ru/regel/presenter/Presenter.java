package ru.regel.presenter;


import ru.regel.highscrore.HighScore;
import ru.regel.highscrore.TableModel;
import ru.regel.model.GameMode;
import ru.regel.model.Model;
import ru.regel.model.OpenedTile;
import ru.regel.timer.GameTimer;
import ru.regel.view.View;

import java.util.List;
import java.util.Set;

public class Presenter implements ViewListener, TimerListener, ModelListener {

    private final View view;
    private final Model model;
    private final GameTimer timer = new GameTimer();
    private final TableModel recordModel = new TableModel();


    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
        view.addListener(this);
        model.addListenerToModel(this);
        timer.addTimeListener(this);
        recordModel.addListener(this);
    }

    @Override
    public void timeChanged() {
        view.updateTime(timer.getText());
    }

    @Override
    public void onSpecialGameClicked() {
        view.showSpecialChoiceWindow();
        timer.stopTimer();
    }

    @Override
    public void startSpecialGame(int columns, int rows, int minesCount) {
        model.startNewSpecialGame(columns, rows, minesCount);
        view.reset(model.getBoardHeight(), model.getBoardWidth());
    }

    @Override
    public void onNewGameClicked(GameMode mode) {
        model.startGameWithMode(mode);
        view.reset(model.getBoardHeight(), model.getBoardWidth());
        timer.stopTimer();
    }

    @Override
    public void onExitGameClicked() {
        model.exit();
    }

    @Override
    public void gameWon() {
        timer.stopTimer();
        view.showNameForm();
        view.showWinFace();
    }

    @Override
    public void gameLost() {
        view.showSadFace();
        timer.stopTimer();
    }

    @Override
    public void onLeftButtonClicked(int row, int col) {
        timer.startTimer();
        model.update(row, col);
    }

    @Override
    public void onNewSubmitClicked() {
        view.showNameFromForm();
    }

    @Override
    public void highScoresAreRead(List<HighScore> highScores) {
        view.showTable(highScores);
    }

    @Override
    public void nameIsRead(String name) {
        int result = timer.getTime();
        view.closeNameWindow();
        recordModel.addNewHighScore(name, result);
        recordModel.getHighScores();
    }

    @Override
    public void tilesOpened(Set<OpenedTile> openedTiles) {
        view.openTiles(openedTiles);
    }

    @Override
    public void putFlag(int row, int col) {
        view.putFlag(row, col);
    }

    @Override
    public void deleteFlag(int row, int col) {
        view.deleteFlag(row, col);
    }

    @Override
    public void onRightButtonClicked(int row, int col) {
        model.changeFlag(row, col);
    }

    @Override
    public void updateLeftMinesCount(long leftMinesCount) {
        view.updateLeftMinesCount(leftMinesCount);
    }

    @Override
    public void onWheelClicked(int row, int column) {
        model.updateNearTile(row, column);
    }

    @Override
    public void onRecordsClicked() {
        recordModel.getHighScores();
    }
}
