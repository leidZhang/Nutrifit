package main.frontend.view.user;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.session.UserSession;
import main.frontend.view.IContent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UserProfile implements IContent {
    UserSession instance = UserSession.getInstance();

    @Override
    public String showContent(JPanel content) {
        // the code below is just a demonstration, implement ur user profile components...

        User userNow = instance.getUser();

        // set up layout
        content.removeAll();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // get data from main.backend
        assert userNow != null;
        // get user attributes
        String name = userNow.getName();
        String username = userNow.getUsername();
        String sex = userNow.getSex();
        String age = String.valueOf(userNow.getAge());
        String height = String.valueOf(userNow.getHeight());
        String weight = String.valueOf(userNow.getWeight());
        String dateOfBirth = userNow.getDateOfBirth().toString();

        // implement user profile info...
        JLabel nameLabel = new JLabel("Name: " + name);
        nameLabel.setFont(nameLabel.getFont().deriveFont(16.0f));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(nameLabel);


        JLabel usernameLabel = new JLabel("Username: " + username);
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(16.0f));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(usernameLabel);

        JLabel sexLabel = new JLabel("Sex: " + sex);
        sexLabel.setFont(sexLabel.getFont().deriveFont(16.0f));
        sexLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(sexLabel);

        JLabel ageLabel = new JLabel("Age: " + age);
        ageLabel.setFont(ageLabel.getFont().deriveFont(16.0f));
        ageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(ageLabel);

        JLabel heightLabel = new JLabel("Height: " + height);
        heightLabel.setFont(heightLabel.getFont().deriveFont(16.0f));
        heightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(heightLabel);

        JLabel weightLabel = new JLabel("Weight: " + weight);
        weightLabel.setFont(weightLabel.getFont().deriveFont(16.0f));
        weightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(weightLabel);

        JLabel dobLabel = new JLabel("Date of Birth: " + dateOfBirth);
        dobLabel.setFont(dobLabel.getFont().deriveFont(16.0f));
        dobLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(dobLabel);

        // update button
        JButton updateButton = new JButton("update information");
        updateButton.setFont(updateButton.getFont().deriveFont(16.0f));
        content.add(updateButton);

        // 添加登录按钮的点击事件监听器
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                IContent updateUserPage = new UpdateUserProfile();
                updateUserPage.showContent(content);
                content.revalidate();
                content.repaint();

            }
        });

        return "Switch to UserProfile";
    }
}
