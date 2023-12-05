package main.frontend.view.mainframe.component;

import main.frontend.common.ContentBuilder;
import main.frontend.session.UserSession;
import main.frontend.view.mainframe.IMainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class SideBarBuilder extends ContentBuilder {
    private AccountPanel accountPanel;
    private Map<String, JButton> buttonMap = new LinkedHashMap<>();

    public SideBarBuilder(JPanel page) {
        super(page);
        accountPanel = new AccountPanel();
    }

    @Override
    public void buildTitle(String title) {
        constraints.gridy = gridy++;
        accountPanel.getUsernameLabel().setText("User: " + title);
        page.add(accountPanel, constraints);
    }

    @Override
    public void setUp() {
        constraints = new GridBagConstraints();
        page.setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 0; // one component per row

        Dimension dimension = new Dimension(200, 50);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        String[] pageList = {"Home", "My Profile", "My Exercise Log", "My Meal Log"};
        for (String s : pageList) {
            buttonMap.put(s, new JButton(s));
            buttonMap.get(s).setPreferredSize(dimension);
            buttonMap.get(s).setFont(buttonFont);
        }
    }

    private void buildButtons() {
        // Add some empty space after the content
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(10), constraints);

        for (Map.Entry<String, JButton> entry : buttonMap.entrySet()) {
            constraints.gridy = gridy++;
            page.add(entry.getValue(), constraints);
        }
    }

    public void buildFooter() {
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(350), constraints);

        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        JLabel versionLabel = new JLabel("ver.0.1.0");
        versionLabel.setFont(buttonFont);

        constraints.gridy = gridy++;
        page.add(versionLabel, constraints);
    }

    @Override
    public void buildMainContent() {
        buildButtons();
        buildFooter();
    }

    public Map<String, JButton> getButtonMap() {
        buttonMap.put("Log out", accountPanel.getLogoutButton());

        return buttonMap;
    }

    public JLabel getLabel() {
        return accountPanel.getUsernameLabel();
    }
}
