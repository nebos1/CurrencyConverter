package org.cc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterUI extends JFrame {
    private JPanel main_panel;
    private JTextField input_value;
    private JButton value_select_left;
    private JButton value_select_right;
    private JButton convert;
    private JLabel output_value;
    // logic + values
    private final ConverterLogic logic = new ConverterLogic();
    private String FromCode = "BGN";
    private String ToCode = "EUR";

    public ConverterUI() {
        // JPanel settings
        setContentPane(main_panel);
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // auto pack all elements inside main panel
        setLocationRelativeTo(null); // center position

        // choice buttons
        value_select_left.setText(FromCode);
        value_select_right.setText(ToCode);
        // selection FROM
        value_select_left.addActionListener(e -> {
            String choice = ChooseCurrency(FromCode);
            if (choice != null) {
                FromCode = choice;
                value_select_left.setText(FromCode);
            }
        });
        // selection TO
        value_select_right.addActionListener(e -> {
            String choice = ChooseCurrency(ToCode);
            if (choice != null) {
                ToCode = choice;
                value_select_right.setText(ToCode);
            }
        });

        // converting button + enter key
        convert.addActionListener(e -> doConvert());

        input_value.addActionListener(e -> doConvert());

        setVisible(true);
    }

    // takes the sum, converts it and shows the result
    private String ChooseCurrency(String CurrentCode) {
        String[] possibilities = logic.getCurrencyCodes();
        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Choose currency:",
                "Currency Selection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                CurrentCode
        );
        return choice;
    }


    private void doConvert() {
        try {
            String inputText = input_value.getText().trim();
            if (inputText.isEmpty()) {
                throw new IllegalArgumentException("Error! Input is empty!");
            }
            double amount = Double.parseDouble(inputText);
            double result = logic.Convert(amount, FromCode, ToCode);
            output_value.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error! Invalid number format!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Conversion Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
