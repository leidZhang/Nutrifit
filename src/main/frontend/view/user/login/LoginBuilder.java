package main.frontend.view.user.login;

import main.frontend.common.ContentBuilder;
import main.frontend.custom.entry.NfEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoginBuilder extends ContentBuilder {
    private Map<String, NfEntry> entries = new LinkedHashMap<>();
    private Map<String, JButton> buttonMap = new LinkedHashMap<>();

    public LoginBuilder(JPanel page) {
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

        buildRegisterButton();
        buildSubmitButton();
    }

    @Override
    public void setUp() {
        String[] names = {"Username", "Password"};
        for (int i=0; i<names.length; i++) {
            if (names[i].equals("Password")) {
                entries.put(names[i], new NfEntry(25, 400, "password"));
            } else {
                entries.put(names[i], new NfEntry(25, 400));
            }

        }

        // set entries title
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            entry.getValue().setTitle(entry.getKey());
        }
    }

    public void buildRegisterButton() {
        // Add some empty space after the content
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(10), constraints);

        JButton button = new JButton("Register");
        button.setPreferredSize(new Dimension(100, 30));
        constraints.gridx = 0;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        page.add(button, constraints);

        buttonMap.put("Register", button);
    }

    public void buildSubmitButton() {
        JButton button = new JButton("Login");
        button.setPreferredSize(new Dimension(100, 30));
        constraints.gridx = 1;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        page.add(button, constraints);

        buttonMap.put("Login", button);
    }

    public Map<String, NfEntry> getFormData() {
        return entries;
    }

    public Map<String, JButton> getButtonMap() {
        return buttonMap;
    }
}
