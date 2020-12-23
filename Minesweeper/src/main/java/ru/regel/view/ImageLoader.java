package ru.regel.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {

    private final Map<Integer, ImageIcon> numberIcons = new HashMap<>();
    private final Map<PictureImageLink, ImageIcon> pictureIcons = new HashMap<>();
    private final Map<PictureImageLink, Image> appIcons = new HashMap<>();

    ImageIcon createStartImageIcon() {
        return getPictureImageIcon(PictureImageLink.START_FACE_IMAGE_LINK);
    }

    ImageIcon createFlagIcon() {
        return getPictureImageIcon(PictureImageLink.FLAG_ICON);
    }

    ImageIcon createPitImageIcon() {
        return getPictureImageIcon(PictureImageLink.PIT_IMAGE_ICON);
    }

    ImageIcon createCryImageIcon() {
        return getPictureImageIcon(PictureImageLink.CRY_IMAGE_ICON);
    }

    ImageIcon createNumberIcon(Integer number) {
        NumberImageLink link = null;
        try {
            link = NumberImageLink.getLinkByNumber(number);

        } catch (Exception e) {
            e.getMessage();
        }
        return getNumberImageIcon(link);
    }

    ImageIcon createEmptyIcon() {
        return getPictureImageIcon(PictureImageLink.EMPTY_CELL_IMAGE_LINK);
    }

    ImageIcon createSmileyIcon() {
        return getPictureImageIcon(PictureImageLink.SMILEY_IMAGE_ICON_LINK);
    }

    Image createAppIcon() {
        return getAppIcon(PictureImageLink.MINE_ICON);
    }

    Image createCursorImage() {
        return getAppIcon(PictureImageLink.CURSOR_ICON);
    }

    private Image createImage(String imageLink) {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(imageLink);
        Image image = null;
        try {
            image = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    }

    private ImageIcon getNumberImageIcon(NumberImageLink imageLink) {
        return numberIcons.computeIfAbsent(imageLink.number, newNumber ->
                new ImageIcon(createImage(imageLink.link)));
    }

    private ImageIcon getPictureImageIcon(PictureImageLink imageLink) {
        return pictureIcons.computeIfAbsent(imageLink, link ->
                new ImageIcon(createImage(imageLink.link)));
    }

    private Image getAppIcon(PictureImageLink imageLink) {
        return appIcons.computeIfAbsent(imageLink, link ->
                createImage(imageLink.link));
    }
}

