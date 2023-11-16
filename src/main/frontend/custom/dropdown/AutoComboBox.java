package main.frontend.custom.dropdown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AutoComboBox<T> extends JPanel {
    private JLabel titleField;
    JComboBox comboBox;
    private JLabel messageField;
    private Object[] itemList;

    public AutoComboBox(int height, int width) {
        titleField = new JLabel("");
        comboBox = new JComboBox<>();
        messageField = new JLabel("");

        initialize(height, width);
        setUp();
    }

    private void initialize(int height, int width) {
        setLayout(new GridLayout(3, 1));

        Dimension dimension = new Dimension(width, height);
        titleField.setPreferredSize(dimension);
        titleField.setMinimumSize(dimension);
        comboBox.setPreferredSize(dimension);
        comboBox.setMinimumSize(dimension);
        messageField.setPreferredSize(dimension);
        messageField.setMinimumSize(dimension);

        Font titleFont = new Font("Arial", Font.PLAIN, 18);
        titleField.setFont(titleFont);
        messageField.setForeground(Color.RED);

        add(titleField);
        add(comboBox);
        add(messageField);
        setBackground(Color.white);
    }

    private void setUp() {
        comboBox.setEditable(true);

        KeyListener keyListener = handleFilter();
        comboBox.getEditor().getEditorComponent().addKeyListener(keyListener);
    }

    private KeyListener handleFilter() {
        KeyListener listener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String input = comboBox.getEditor().getItem().toString().toLowerCase();
                comboBox.removeAllItems();
                for (Object item : itemList) {
                    if (item.toString().toLowerCase().contains(input)) {
                        comboBox.addItem(item);
                    }
                }
                comboBox.getEditor().setItem(input);
                comboBox.showPopup();
            }
        };

        return listener;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList.toArray();
        for (Object item : this.itemList) {
            comboBox.addItem(item);
        }
    }

    public void setTitleField(String title) {
        titleField.setText(title);
    }

    private boolean isInList() {
        if (itemList == null) return false;

        for (Object item : itemList) {
            String itemString = item.toString().toLowerCase();
            String input = comboBox.getEditor().getItem().toString().toLowerCase();
            if (itemString.equals(input)) {
                return true;
            }
        }

        return false;
    }

    public boolean verifyInput() {
        messageField.setText("");
        if (!isInList()) {
            messageField.setText("Input should be one of the item in the list");
            return false;
        }
        return true;
    }
}
