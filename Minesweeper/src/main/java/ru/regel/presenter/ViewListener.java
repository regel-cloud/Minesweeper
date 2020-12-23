package ru.regel.presenter;

import ru.regel.model.GameMode;

public interface ViewListener {

    void onNewGameClicked(GameMode mode);

    void onExitGameClicked();

    void onLeftButtonClicked(int row,int col);

    void onRightButtonClicked(int row,int col);

    void onSpecialGameClicked();

    void onNewSubmitClicked();

    void nameIsRead(String name);

    void onWheelClicked(int row, int column);

    void onRecordsClicked();

    void startSpecialGame(int columns, int rows, int minesCount);
}
