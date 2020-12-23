package ru.regel.view;

import javax.swing.*;
import java.awt.*;

public class Board {
    private static final int BUTTONS_DIMENSION = 25;
    private final int width;
    private final int height;
    final JButton[][] boardButtons;
    JPanel minesPanel;

    public Board(int height, int width) {
        this.width = width;
        this.height = height;
        boardButtons = new JButton[this.height][this.width];
    }

    JPanel createButtons() {
        minesPanel = new JPanel();
        minesPanel.setLayout(new GridLayout(height, width));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boardButtons[i][j] = new JButton("");
                boardButtons[i][j].setBackground(Color.WHITE);
                boardButtons[i][j].setPreferredSize(new Dimension(BUTTONS_DIMENSION, BUTTONS_DIMENSION));
                minesPanel.add(boardButtons[i][j]);
            }
        }
        minesPanel.setVisible(true);
        return minesPanel;
    }

    JButton[][] getBoardButtons() {
        return boardButtons;
    }
}
