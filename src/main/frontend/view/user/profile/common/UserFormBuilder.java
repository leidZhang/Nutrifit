package main.frontend.view.user.profile.common;

import main.frontend.common.ContentBuilder;
import main.frontend.custom.NfEntry;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserFormBuilder extends ContentBuilder {
    protected Map<String, NfEntry> entries = new LinkedHashMap<>();

    public UserFormBuilder(JPanel page) {
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

    public void setHeadHorizontalSpace(int width) {
        // Add some empty space after the content
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(10), constraints);

        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0;
        page.add(Box.createHorizontalStrut(width), constraints);
    }

    public void setTailHorizontalSpace(int width) {
        page.add(Box.createHorizontalStrut(width), constraints);
    }

    @Override
    public void setUp() {
        // create entries
        String[] names = {"Name", "Username", "Password", "Sex", "Date of Birth", "Height (cm)", "Weight (kg)", "Age"};
        for (int i=0; i<names.length; i++) {
            if (names[i].equals("Password")) {
                entries.put(names[i], new NfEntry(25, 250, "password"));
            } else {
                entries.put(names[i], new NfEntry(25, 250));
            }
        }

        // set entries title
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            entry.getValue().setTitle(entry.getKey());
            if (entry.getKey().equals("Age")) entry.getValue().setEditable(false);
        }
    }

    public Map<String, NfEntry> getFormData() {
        return entries;
    }

    public void enableForm(boolean flag) {
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            if (entry.getKey().equals("Age")) continue;
            entry.getValue().setEditable(flag);
        }
    }
}
