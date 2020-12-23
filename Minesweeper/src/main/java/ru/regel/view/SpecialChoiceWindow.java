package ru.regel.view;

import javax.swing.*;

public class SpecialChoiceWindow {
    private static final int VALUE = 1;
    private static final int MIN = 1;
    private static final int MAX = 1000;
    private static final int STEP = 1;
    private final JPanel panel;
    private final int optionRows;
    private final JSpinner spinnerRows;
    private final JSpinner spinnerCol;
    private final JSpinner mineSpinner;

    public SpecialChoiceWindow() {

        JLabel rows = new JLabel("rows");
        SpinnerNumberModel rowModel = new SpinnerNumberModel(VALUE, MIN, MAX, STEP);
        spinnerRows = new JSpinner(rowModel);

        JLabel columns = new JLabel("columns");
        SpinnerNumberModel columnModel = new SpinnerNumberModel(VALUE, MIN, MAX, STEP);
        spinnerCol = new JSpinner(columnModel);

        JLabel mines = new JLabel("mines");
        SpinnerNumberModel mineModel = new SpinnerNumberModel(VALUE, MIN, MAX, STEP);
        mineSpinner = new JSpinner(mineModel);

        panel = new JPanel();
        panel.add(rows);
        panel.add(spinnerRows);
        panel.add(columns);
        panel.add(spinnerCol);
        panel.add(mines);
        panel.add(mineSpinner);

        optionRows = JOptionPane.showOptionDialog(null, panel,
                "Enter your dimensions", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

    }

    JPanel getPanel() {
        return panel;
    }

    int getOptionRows() {
        return optionRows;
    }

    JSpinner getSpinnerRows() {
        return spinnerRows;
    }

    JSpinner getSpinnerCol() {
        return spinnerCol;
    }

    JSpinner getMineSpinner() {
        return mineSpinner;
    }
}
