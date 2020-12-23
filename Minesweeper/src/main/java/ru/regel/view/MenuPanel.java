package ru.regel.view;

import javax.swing.*;

public class MenuPanel {

    private static final String NEW_GAME = "New Game";
    private static final String GAME = "Game";
    private static final String EXPERT = "Expert";
    private static final String INTERMEDIATE = "Intermediate";
    private static final String BEGINNER = "Beginner";
    private static final String SPECIAL = "Special";
    private static final String EXIT = "Exit";
    private static final String ABOUT = "About MineSweeper....";
    private static final String HIGH_SCORES = "High scores";
    private static final String HELP = "Help";

    final JMenuItem newGameMenuItem = new JMenuItem(NEW_GAME);
    final JMenuItem beginnerMenuItem = new JMenuItem(BEGINNER);
    final JMenuItem intermediateMenuItem = new JMenuItem(INTERMEDIATE);
    final JMenuItem expertMenuItem = new JMenuItem(EXPERT);
    final JMenuItem specialMenuItem = new JMenuItem(SPECIAL);
    final JMenuItem exitMenuItem = new JMenuItem(EXIT);
    final JMenuItem aboutMenuItem = new JMenuItem(ABOUT);
    final JMenuItem recordsMenuItem = new JMenuItem(HIGH_SCORES);

    public JMenuBar createMenuBar() {

        JMenuBar mBar = new JMenuBar();
        JMenu game = new JMenu(GAME);
        JMenu help = new JMenu(HELP);

        game.add(newGameMenuItem);
        game.add(beginnerMenuItem);
        game.add(intermediateMenuItem);
        game.add(expertMenuItem);
        game.add(specialMenuItem);
        game.add(recordsMenuItem);
        game.add(exitMenuItem);

        help.add(aboutMenuItem);
        mBar.add(game);
        mBar.add(help);
        return mBar;
    }

    JMenuItem getNewGameMenuItem() {
        return newGameMenuItem;
    }

    JMenuItem getBeginnerMenuItem() {
        return beginnerMenuItem;
    }

    JMenuItem getIntermediateMenuItem() {
        return intermediateMenuItem;
    }

    JMenuItem getExpertMenuItem() {
        return expertMenuItem;
    }

    JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    JMenuItem getSpecialMenuItem() {
        return specialMenuItem;
    }

    JMenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    JMenuItem getRecordsMenuItem() {
        return recordsMenuItem;
    }
}
