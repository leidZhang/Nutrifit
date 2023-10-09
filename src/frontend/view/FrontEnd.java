package frontend.view;

import frontend.view.layout.IContent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FrontEnd extends JFrame implements ItemListener, ActionListener {
    private JPanel topBar;
    private JPanel sideBar;
    private JPanel content;

    public FrontEnd() {
        initialize(); // initialize GUI
    }

    private void initialize() {
        // initialize components
        topBar = createTopPanel();
        sideBar = createSideBar();
        content = createContent();

        // add components to the window
        getContentPane().add(topBar, BorderLayout.NORTH);
        getContentPane().add(sideBar, BorderLayout.WEST);
        getContentPane().add(content, BorderLayout.CENTER);

        // set window attributes
        setTitle("Nutrifit");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createContent() {
        JPanel content = new JPanel();

        // set content initial attributes
        content.setPreferredSize(new Dimension(getWidth() - sideBar.getWidth(), getHeight() - topBar.getWidth()));
        content.setBackground(Color.WHITE);

        return content;
    }

    private JPanel createTopPanel() {
        JPanel topBar = new JPanel();

        // set topBar attributes
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        topBar.setBorder(BorderFactory.createMatteBorder(1,0,2,0, Color.GRAY));
        topBar.setBackground(Color.WHITE);
        // set up layout
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
        // implement top bar menu
        JLabel placeholder = new JLabel("Placeholder");
        placeholder.setFont(placeholder.getFont().deriveFont(18.0f));
        placeholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        topBar.add(placeholder);

        return topBar;
    }

    private JPanel createSideBar() {
        JPanel sideBar = new JPanel();

        // set sideBar attributes
        sideBar.setPreferredSize(new Dimension(200, getHeight()));
        sideBar.setBorder(BorderFactory.createMatteBorder(0,0,0,2, Color.GRAY));
        sideBar.setBackground(Color.WHITE);
        // set up layout
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));

        // implement sideBar menu...
        JLabel profile = new JLabel("Profile");
        profile.setFont(profile.getFont().deriveFont(18.0f));
        profile.setAlignmentX(Component.CENTER_ALIGNMENT);
        sideBar.add(profile);

        JLabel exerciseLog = new JLabel("Exercise Log");
        exerciseLog.setFont(exerciseLog.getFont().deriveFont(18.0f));
        exerciseLog.setAlignmentX(Component.CENTER_ALIGNMENT);
        sideBar.add(exerciseLog);

        return sideBar;
    }

    public void switchContentPanel(IContent IContent) {
        String message = IContent.showContent(content);
        content.revalidate();
        content.repaint();
        System.out.println(message);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // implement actionPerformed...
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // implement itemStateChanged...
    }
}
