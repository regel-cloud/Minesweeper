package ru.regel.presenter;

import ru.regel.highscrore.HighScore;
import ru.regel.model.OpenedTile;

import java.util.List;
import java.util.Set;

public interface ModelListener {

    void gameWon();

    void gameLost();

    void tilesOpened(Set<OpenedTile> openedTiles);

    void putFlag(int row, int col);

    void deleteFlag(int row, int col);

    void highScoresAreRead(List<HighScore> highScores);

    void updateLeftMinesCount(long leftMinesCount);
}
