import javax.swing.*;
import java.awt.*;

public class Viewer {
    JTextField display;
    private Controller controller;

    public Viewer() {
        this.controller = new Controller(this);

        display = new JTextField();
        display.setBackground(Color.PINK);
        display.setFont(new Font("Tahoma", Font.BOLD, 35));
        display.setBounds(10, 22, 426, 62);
        display.setAutoscrolls(false);
        display.setEditable(false);
        display.setAlignmentX(5.0f);
        display.setColumns(10);

        createButton("⌫", 10, 106, "Del");
        createButton("C", 119, 106, "Del_all");
        createButton("7", 10, 179, "Seven");
        createButton("8", 119, 179, "Eight");
        createButton("4", 10, 252, "Four");
        createButton("5", 119, 252, "Five");
        createButton("1", 10, 325, "One");
        createButton("2", 119, 325, "Two");
        createButton("(", 228, 106, "Open_p");
        createButton("9", 228, 179, "Nine");
        createButton("6", 228, 252, "Six");
        createButton("3", 228, 325, "Three");
        createButton(")", 337, 106, "Close_p");
        createButton("0", 10, 398, "Zero");
        createButton(",", 119, 398, "Dot");
        createButton("=", 228, 398, "Equal");
        createButton("+", 337, 179, "Plus");
        createButton("-", 337, 252, "Minus");
        createButton("\u00D7", 337, 325, "Multiply");
        createButton("\u00F7", 337, 398, "Divide");

        JFrame frmCalculator = new JFrame();
        for (JButton button : buttons) {
            button.setForeground(Color.PINK);
        }
        frmCalculator.setResizable(false);
        frmCalculator.setAlwaysOnTop(true);
        frmCalculator.setLayout(null);
        frmCalculator.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\TechLine\\Desktop\\untitled1\\src\\475497.png"));
        frmCalculator.setTitle("Calculator");
        frmCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCalculator.setVisible(true);
        frmCalculator.setBounds(100, 100, 461, 516);
        frmCalculator.add(display);

        Color numberButtonBackground = Color.BLACK;

// Change the background color of number buttons to black
        for (JButton button : buttons) {
            if (button.getText().matches("[0-9]")) {
                button.setBackground(numberButtonBackground);
            }
        }

        Color specialButtonBackground = Color.BLACK;

// Change the background color of specific buttons to black
        for (JButton button : buttons) {
            String buttonText = button.getText();
            if (buttonText.matches("[⌫C)(,*=+\\u00F7\u00D7\\-]")) {
                button.setBackground(specialButtonBackground);
            }
        }


        for (JButton button : buttons) {
            frmCalculator.add(button);
        }
    }

    private void createButton(String text, int x, int y, String actionCommand) {
        JButton button = new JButton(text);

        button.setBounds(x, y, 99, 62);
        button.setActionCommand(actionCommand);
        button.setFont(new Font("", Font.BOLD, 25));
        button.setFocusPainted(false);
        button.addActionListener(controller);
        buttons.add(button);
    }


    public void appendToDisplay(String text) {
        display.setText(display.getText() + text);
    }

    private java.util.List<JButton> buttons = new java.util.ArrayList<>();
}
