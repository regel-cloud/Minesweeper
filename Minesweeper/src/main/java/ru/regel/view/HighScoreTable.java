package ru.regel.view;

import ru.regel.highscrore.HighScore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HighScoreTable {
    private static final int SIZE = 300;
    private static final int SECONDS = 60;
    private static final String[] HEADERS = {"Name", "Time"};
    private final JTable highScores = new JTable();
    private final JFrame frame = new JFrame("Records");
    private final DefaultTableModel model = new DefaultTableModel();


    void showJTable(List<HighScore> highScores) {
        model.setColumnIdentifiers(HEADERS);
        this.highScores.setModel(model);
        for (HighScore highScore : highScores) {
            model.addRow(new Object[]{String.valueOf(highScore.getName()),
                    String.format("%02d:%02d", highScore.getTime() / SECONDS, highScore.getTime() % SECONDS)});
        }
        JScrollPane scroll = new JScrollPane(this.highScores);
        frame.add(scroll, BorderLayout.CENTER);
        frame.setSize(SIZE, SIZE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
