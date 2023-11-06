package main.frontend.view.user.userform;

import main.frontend.common.PageBuilder;
import main.frontend.component.NfEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserProfileBuilder extends PageBuilder {
    private Map<String, NfEntry> entries = new LinkedHashMap<>();

    public UserProfileBuilder(JPanel page) {
        super(page);

        constraints = new GridBagConstraints();
        page.setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridwidth = 0; // one component per row
    }

    @Override
    public void setUp() {
        // create entries
        String[] names = {"Name", "Username", "Sex", "Date of Birth", "Height (cm)", "Weight (kg)", "Age"};
        for (int i=0; i<names.length; i++) {
            entries.put(names[i], new NfEntry(25, 250));
        }

        // set entries title
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            entry.getValue().setTitle(entry.getKey());
        }
    }

    @Override
    public void buildMainContent() {
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            constraints.gridy = gridy++;
            page.add(entry.getValue(), constraints);
        }
    }

    public void buildOperations(ActionListener listener) {
        // Add some empty space after the content
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(10), constraints);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(listener);
        constraints.gridy = gridy++;
        page.add(submitButton, constraints);
    }

    public Map<String, NfEntry> getFormData() {
        return entries;
    }
}
