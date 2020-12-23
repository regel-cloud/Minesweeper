package ru.regel.view;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private static final String TITLE = "MINESWEEPER";
    private static final int DEFAULT_BOARD_WIDTH = 9;
    private static final int DEFAULT_BOARD_HEIGHT = 9;
    private static final int SMILEY_DIMENSION = 25;
    final JFrame screen = new JFrame(TITLE);
    private final JPanel composite = new JPanel();
    private final JButton smileyButton = new JButton("");
    private final JPanel topPanel = new JPanel();
    private Board board;
    private final MenuPanel menuPanel = new MenuPanel();
    private final JTextField timeTextField = new JTextField(3);
    private final JTextField minesTextField = new JTextField(3);
    private final JPanel timePanel = new JPanel();
    private final JPanel minePanel = new JPanel();
    private final ImageLoader loader = new ImageLoader();

    void setDefaultBoard() {
        board = new Board(DEFAULT_BOARD_HEIGHT, DEFAULT_BOARD_WIDTH);
    }

    void setPanel() {
        composite.setLayout(new BorderLayout());
        composite.add(topPanel, BorderLayout.NORTH);
        composite.add(board.createButtons(), BorderLayout.CENTER);
    }

    void setTopPanel() {
        smileyButton.setPreferredSize(new Dimension(SMILEY_DIMENSION, SMILEY_DIMENSION));
        smileyButton.setIcon(loader.createStartImageIcon());
        topPanel.add(smileyButton);
        setTimerPanel();
        setMinesPanel();
    }

    private void setMinesPanel() {
        topPanel.add(minePanel);
        minesTextField.setHorizontalAlignment(JTextField.RIGHT);
        minesTextField.setEditable(false);
        timePanel.add(minesTextField);
    }

    void setScreen() {
        screen.setIconImage(loader.createAppIcon());
        screen.setCursor(createCursor());
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);
        screen.setResizable(true);
    }

    void setMenu() {
        JMenuBar menu = menuPanel.createMenuBar();
        screen.setJMenuBar(menu);
    }


    private Cursor createCursor() {
        return Toolkit.getDefaultToolkit().createCustomCursor(loader.
                        createCursorImage(),
                new Point(0, 0), "custom cursor");

    }

    void setTimerPanel() {
        topPanel.add(timePanel);
        timeTextField.setHorizontalAlignment(JTextField.RIGHT);
        timeTextField.setEditable(false);
        timePanel.add(timeTextField);
    }

    JPanel getComposite() {
        return composite;
    }

    JButton getSmileyButton() {
        return smileyButton;
    }

    Board getBoard() {
        return board;
    }

    MenuPanel getMenuPanel() {
        return menuPanel;
    }

    JFrame getScreen() {
        return screen;
    }

    ImageLoader getLoader() {
        return loader;
    }

    void setBoard(Board board) {
        this.board = board;
    }

    JTextField getTimeTextField() {
        return timeTextField;
    }

    JTextField getMinesTextField() {
        return minesTextField;
    }
}



