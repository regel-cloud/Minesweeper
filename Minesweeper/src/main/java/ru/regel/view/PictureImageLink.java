package ru.regel.view;

public enum PictureImageLink {
    SMILEY_IMAGE_ICON_LINK("images/smiley.png"),
    EMPTY_CELL_IMAGE_LINK("images/t.png"),
    PIT_IMAGE_ICON("images/pit.png"),
    CRY_IMAGE_ICON("images/cry.png"),
    START_FACE_IMAGE_LINK("images/start_face.jpg"),
    MINE_ICON("images/sea_mine.png"),
    FLAG_ICON("images/flag.png"),
    CURSOR_ICON("images/shovel.png");

    String link;

    PictureImageLink(String link) {
        this.link = link;
    }
}
