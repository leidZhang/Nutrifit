package main.frontend.view.user;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.view.IContent;

import javax.swing.*;
import java.awt.*;

public class UserProfile implements IContent {
    IUserController user = new UserController();

    @Override
    public String showContent(JPanel content) {
        // the code below is just a demonstration, implement ur user profile components...
        // set up layout
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // get data from main.backend
        Result res = user.getUser("js288c");
        System.out.println(res.getCode());

        if (res.getCode().equals("200")) {
            User user = (User) res.getData();
            // get user attributes
            String name = user.getName();
            String username = user.getUsername();
            String sex = user.getSex();
            String age = String.valueOf(user.getAge());
            String height = String.valueOf(user.getHeight());
            String weight = String.valueOf(user.getWeight());
            String dateOfBirth = user.getDateOfBirth().toString();

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
        } else if (res.getCode().equals("-1")) {
            JLabel message = new JLabel(res.getMessage());
            message.setFont(message.getFont().deriveFont(16.0f));
            message.setAlignmentX(Component.CENTER_ALIGNMENT);
            content.add(message);
        }

        return "Switch to UserProfile";
    }
}
