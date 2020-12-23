package ru.regel.view;

import java.util.Arrays;

public enum NumberImageLink {
    ONE_IMAGE_ICON(1, "images/1.png"),
    TWO_IMAGE_ICON(2, "images/2.png"),
    THREE_IMAGE_ICON(3, "images/3.png"),
    FOUR_IMAGE_ICON(4, "images/4.png"),
    FIVE_IMAGE_ICON(5, "images/5.png"),
    SIX_ICON(6, "images/6.png"),
    SEVEN_ICON(7, "images/7.png"),
    EIGHT_ICON(8, "images/8.png");

    Integer number;
    String link;

    NumberImageLink(Integer number, String link) {
        this.number = number;
        this.link = link;
    }

    public static NumberImageLink getLinkByNumber(Integer key) throws Exception {
        return Arrays.stream(NumberImageLink.values()).filter(v ->
                v.number.equals(key)).findFirst().orElseThrow(() ->
                new Exception(String.format("Unknown TestEnum.key: '%s'", key)));
    }

}
