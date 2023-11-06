package main.frontend.component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class NfEntry extends JPanel {
    private JLabel titleField;
    private JTextField textField;
    private JLabel messageField;
    private String regex; // regular expression
    private String verifyMessage;

    public NfEntry(int height, int width) {
        setLayout(new GridLayout(3, 1));

        titleField = new JLabel("");
        textField = new JTextField();
        messageField = new JLabel("");

        Dimension dimension = new Dimension(width, height);
        titleField.setPreferredSize(dimension);
        textField.setPreferredSize(dimension);
        messageField.setPreferredSize(dimension);
        titleField.setMinimumSize(dimension);
        textField.setMinimumSize(dimension);
        messageField.setMinimumSize(dimension);

        Font titleFont = new Font("Arial", Font.PLAIN, 18);
        Font entryFont = new Font("Arial", Font.PLAIN, 16);
        titleField.setFont(titleFont);
        textField.setFont(entryFont);
        messageField.setForeground(Color.RED);

        add(titleField);
        add(textField);
        add(messageField);

        textField.setBorder(new LineBorder(Color.BLUE, 1));
        setBackground(Color.white);
    }

    public boolean verifyInput() {
        String data = getInput();

        if (regex != null && !data.matches(regex)) {
            setMessage(verifyMessage);
            return false;
        }

        setMessage(""); // clear message
        return true;
    }

    public String getInput() {
        return textField.getText();
    }

    public void setTitle(String title) {
        titleField.setText(title);
    }

    public void setEntry(String data) {
        textField.setText(data);
    }

    public void setMessage(String message) {
        messageField.setText(message);
    }

    public void setRegex(String regex, String verifyMessage) {
        this.regex = regex;
        this.verifyMessage = verifyMessage;
    }

    public void setEditable(boolean flag) {
        textField.setEditable(flag);
    }
}
