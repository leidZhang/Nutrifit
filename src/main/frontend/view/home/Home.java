package main.frontend.view.home;

import main.backend.user.entity.User;
import main.frontend.FrontEnd;
import main.frontend.component.NfEntry;
import main.frontend.session.UserSession;
import main.frontend.common.IContent;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Home implements IContent {
    UserSession instance = UserSession.getInstance();
    @Override
    public String showContent(JPanel content, FrontEnd frontEnd) {
        User user = instance.getUser();

        // remove all
        content.removeAll();

        content.setLayout(new GridBagLayout());

        NfEntry entry1 = new NfEntry(25, 150);
        content.add(entry1);


        // set up button attributes
//        JLabel label = new JLabel("hello " + user.getName()); // get login user info
//        label.setForeground(Color.BLACK);
//        label.setPreferredSize(new Dimension(100, 20));
//
//        // set up GridBagLayout
//        content.setLayout(new GridBagLayout());
//        GridBagConstraints gridBagConstraints = new GridBagConstraints();
//        // set up common constraints for buttons
//        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST; // align: top left
//        gridBagConstraints.weightx = 1.0; // allocate horizontal space
//        gridBagConstraints.weighty = 1.0; // allocate vertical space
//        gridBagConstraints.insets = new Insets(5, 5, 5, 5); // component border
//
//        // add button to the first row and first column
//        gridBagConstraints.gridx = 0; // column 0
//        gridBagConstraints.gridy = 0; // row 0
//        content.add(label, gridBagConstraints);



        return "Switch to homepage";
    }
}
