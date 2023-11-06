package main.frontend.common;

import javax.swing.*;
import java.awt.*;

public abstract class ContentBuilder {
    protected JPanel page;
    protected GridBagConstraints constraints;
    protected int gridy = 0;

    public ContentBuilder(JPanel page) {
        this.page = page;
    }

    public void buildTitle(String title) {
        JLabel titleLabel = new JLabel(title);
        Font titleFont = new Font("Arial", Font.BOLD, 25);
        titleLabel.setFont(titleFont);

        constraints.gridy = gridy++;
        page.add(titleLabel, constraints);

        // Add some empty space after the title
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(20), constraints);
    }
    public abstract void buildMainContent();

    public abstract void setUp();

    public void clearPage() {
        page.removeAll();
    }
}
