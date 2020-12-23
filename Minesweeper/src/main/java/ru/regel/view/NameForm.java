package ru.regel.view;

import javax.swing.*;
import java.awt.*;

public class NameForm {
    private final JFrame frame = new JFrame("You won, add name");
    private final JButton buttonSubmit = new JButton("Submit");
    private final JPanel panel = new JPanel();
    private final JTextField textField = new JTextField(20);

    public NameForm() {
        init();
    }

    private void init() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setMaximumSize(new Dimension(10000, 200));
        panel.add(textField);
        panel.add(buttonSubmit);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.pack();
    }

    JFrame getFrame() {
        return frame;
    }

    JButton getButtonSubmit() {
        return buttonSubmit;
    }

    JTextField getTextField() {
        return textField;
    }

}
