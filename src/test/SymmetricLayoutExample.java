package test;

import javax.swing.*;
import java.awt.*;

public class SymmetricLayoutExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Symmetric Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        addComponents(panel);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    private static void addComponents(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Set insets for spacing

        // 第一行三个构件
        gbc.gridx = 0;
        gbc.gridy = 0;
        addComponent(panel, new JTextField(), gbc);

        gbc.gridx = 1;
        addComponent(panel, new JTextField(), gbc);

        gbc.gridx = 2;
        addComponent(panel, new JButton("Button 3"), gbc);
        gbc.gridx = 3;
        addComponent(panel, new JButton("Button 3"), gbc);

        // 第二行一张折线图
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4; // 占据三列
        addComponent(panel, new JLabel("Line Chart"), gbc);

        // 第三行一个Label和一个entry
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // 恢复为默认值
        addComponent(panel, new JLabel("Label"), gbc);

        gbc.gridx = 1;
        addComponent(panel, new JTextField(), gbc);

        // 第四行一个label
        gbc.gridx = 0;
        gbc.gridy = 3;
        addComponent(panel, new JLabel("Another Label"), gbc);

        // 第五行两个entry
        gbc.gridx = 0;
        gbc.gridy = 4;
        addComponent(panel, new JTextField(), gbc);

        gbc.gridx = 1;
        addComponent(panel, new JTextField(), gbc);

        // 最后一行一个按钮
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3; // 占据三列
        addComponent(panel, new JButton("Submit"), gbc);
    }

    private static void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc) {
        panel.add(component, gbc);
    }
}
