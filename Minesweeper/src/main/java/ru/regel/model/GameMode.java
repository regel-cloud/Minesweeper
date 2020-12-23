package ru.regel.model;

public enum GameMode {
    BEGINNER_MODE(9,9,10),
    DEFAULT_MODE(9,9,10),
    EXPERT_MODE(16,30,129),
    INTERMEDIATE_MODE(16,16,8);

    int height;
    int width;
    int minesCount;

    GameMode(int height, int width, int minesCount) {
        this.height = height;
        this.width = width;
        this.minesCount = minesCount;
    }
}
