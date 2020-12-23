package ru.regel.highscrore;

import ru.regel.presenter.ModelListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TableModel {

    private final List<HighScore> highScores = new ArrayList<>();
    private final List<ModelListener> listeners = new ArrayList<>();

    private final FileWorker fileWriter = new FileWorker();

    public void getHighScores() {
        addFromFile();
        highScores.sort(Comparator.comparingInt(HighScore::getTime));
        addToFile();
        notifyListenersHighScoresAreRead(highScores);
    }

    private void addFromFile() {
        highScores.addAll(fileWriter.read());
    }

    private void addToFile() {
        fileWriter.write(highScores);
    }

    public void addNewHighScore(String name, int result) {
        highScores.add(new HighScore(name, result));
    }

    private void notifyListenersHighScoresAreRead(List<HighScore> highScores) {
        for (final ModelListener listener : listeners) {
            listener.highScoresAreRead(highScores);
        }
    }

    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }
}
