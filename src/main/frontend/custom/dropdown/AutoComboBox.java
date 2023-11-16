package main.frontend.custom.dropdown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AutoComboBox<T> extends JPanel {
    private JLabel titleField;
    JComboBox comboBox;
    Object[] itemList;

    public AutoComboBox(int height, int width) {
        titleField = new JLabel("");
        comboBox = new JComboBox<>();
        comboBox.setEditable(true);

        initialize(height, width);
        setUp();
    }

    private void initialize(int height, int width) {
        setLayout(new GridLayout(2, 1));

        Dimension dimension = new Dimension(width, height);
        titleField.setPreferredSize(dimension);
        titleField.setMinimumSize(dimension);
        comboBox.setPreferredSize(dimension);
        comboBox.setMinimumSize(dimension);

        Font titleFont = new Font("Arial", Font.PLAIN, 18);
        titleField.setFont(titleFont);

        add(titleField);
        add(comboBox);
        setBackground(Color.white);
    }

    private void setUp() {
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
}
