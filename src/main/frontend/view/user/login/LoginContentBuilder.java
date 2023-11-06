package main.frontend.view.user.login;

import main.frontend.common.ContentBuilder;
import main.frontend.component.NfEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoginContentBuilder extends ContentBuilder {
    private Map<String, NfEntry> entries = new LinkedHashMap<>();

    public LoginContentBuilder(JPanel page) {
        super(page);

        constraints = new GridBagConstraints();
        page.setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridwidth = 0; // one component per row
    }

    @Override
    public void buildMainContent() {
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            constraints.gridy = gridy++;
            page.add(entry.getValue(), constraints);
        }
    }

    @Override
    public void setUp() {
        String[] names = {"Username", "Password"};
        for (int i=0; i<names.length; i++) {
            entries.put(names[i], new NfEntry(25, 400));
        }

        // set entries title
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            entry.getValue().setTitle(entry.getKey());
        }
    }

    public void buildRegisterButton(ActionListener listener) {
        // Add some empty space after the content
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(10), constraints);

        JButton submitButton = new JButton("Register");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.addActionListener(listener);
        constraints.gridx = 0;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        page.add(submitButton, constraints);
    }

    public void buildSubmitButton(ActionListener listener) {
        JButton submitButton = new JButton("Login");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.addActionListener(listener);
        constraints.gridx = 1;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        page.add(submitButton, constraints);
    }

    public Map<String, NfEntry> getFormData() {
        return entries;
    }
}
