package main.frontend.view.home;

import main.frontend.view.IContent;

import javax.swing.*;
import java.awt.*;

public class HomePage implements IContent {
    @Override
    public String showContent(JPanel content) {
        // set up button attributes
        JLabel label = new JLabel("hello world!");
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(100, 20));

        // set up GridBagLayout
        content.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        // set up common constraints for buttons
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST; // align: top left
        gridBagConstraints.weightx = 1.0; // allocate horizontal space
        gridBagConstraints.weighty = 1.0; // allocate vertical space
        gridBagConstraints.insets = new Insets(5, 5, 5, 5); // component border

        // add button to the first row and first column
        gridBagConstraints.gridx = 0; // column 0
        gridBagConstraints.gridy = 0; // row 0
        content.add(label, gridBagConstraints);

        return "Switch to homepage";
    }
}
