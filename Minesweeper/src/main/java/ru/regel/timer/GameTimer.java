package ru.regel.timer;

import ru.regel.presenter.TimerListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameTimer {

    private static final int SECONDS = 60;
    private final Timer timer;
    private int count;
    private final List<TimerListener> timeListeners;
    private String text;
    private int time;

    public GameTimer() {
        this.timeListeners = new ArrayList<>();
        timer = new Timer(1000, e -> {
            text = String.format("%02d:%02d", count / SECONDS, count % SECONDS);
            notifyListenerOnTimeChanged();
            time = count;
            count++;
        });
        timer.setInitialDelay(0);
    }

    private void notifyListenerOnTimeChanged() {
        for (final TimerListener listener : timeListeners) {
            listener.timeChanged();
        }
    }

    public void addTimeListener(TimerListener listener) {
        timeListeners.add(listener);
    }

    public int getTime() {
        return time;
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
        count = 0;
    }

    public String getText() {
        return text;
    }

}