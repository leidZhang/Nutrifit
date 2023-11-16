package main.frontend.view.home;

import main.frontend.common.Content;

import javax.swing.*;
import java.awt.*;


public class Home extends Content {
    @Override
    public String showContent(JPanel content) {
        content.removeAll();

        content.setLayout(new GridBagLayout());

        // set up button attributes
        JLabel label = new JLabel("Welcome to Home Page"); // get login user info
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(200, 20));

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

        return "Switch to Home Page";
    }
}
