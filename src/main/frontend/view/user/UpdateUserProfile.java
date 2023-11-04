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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UpdateUserProfile implements IContent {

    IUserController userController = new UserController();

    UserSession instance = UserSession.getInstance();

    @Override
    public String showContent(JPanel content) {
        // set up layout
        content.removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(20, 15));

        // id
        JLabel idLabel = new JLabel("id:");
        JTextField idField = new JTextField(20);
        idField.setName("idField");

        // name
        JLabel nameLabel = new JLabel("name:");
        JTextField nameField = new JTextField(20);
        nameField.setName("nameField");

        // username
        JLabel usernameLabel = new JLabel("user name:");
        JTextField usernameField = new JTextField(20);
        usernameField.setName("usernameField");

        // sex
        JLabel sexLabel = new JLabel("sex:");
        JTextField sexField = new JTextField(20);
        sexField.setName("sexField");

        // dateOfBirth
        JLabel dateOfBirthLabel = new JLabel("dateOfBirth:");
        JTextField dateOfBirthField = new JTextField(20);
        dateOfBirthField.setName("dateOfBirthField");

        //height
        JLabel heightLabel = new JLabel("height:");
        JTextField heightField = new JTextField(20);
        heightField.setName("heightField");

        //weight
        JLabel weightLabel = new JLabel("weight:");
        JTextField weightField = new JTextField(20);
        weightField.setName("weightField");

        //age
        JLabel ageLabel = new JLabel("age:");
        JTextField ageField = new JTextField(20);
        ageField.setName("ageField");

        //submit button
        JButton submitButton = new JButton("submit");
        submitButton.setFont(submitButton.getFont().deriveFont(16.0f));
        submitButton.setName("submitButton");

        //back button
        JButton backButton = new JButton("back");
        backButton.setFont(backButton.getFont().deriveFont(16.0f));

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(sexLabel);
        panel.add(sexField);
        panel.add(dateOfBirthLabel);
        panel.add(dateOfBirthField);
        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(weightLabel);
        panel.add(weightField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(new JLabel());
        panel.add(submitButton);
        panel.add(new JLabel());
        panel.add(backButton);
        content.add(panel);

        // addActionListener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                String name = nameField.getText();
                String username = usernameField.getText();
                String sex = sexField.getText();
                String dateOfBirth = dateOfBirthField.getText();
                System.out.println(dateOfBirth);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date;
                try {
                    date = format.parse(dateOfBirth);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());
                int age = Integer.parseInt(ageField.getText());

                User userNow = new User(name, username, sex, sqlDate, height, weight, age);
                Result res = userController.updateUser(userNow);
                System.out.println(res.getCode());
                if (Objects.equals(res.getCode(), "200")) {
                    instance.setUser(userNow);
                    content.removeAll();
                    JOptionPane.showMessageDialog(null, "update success");
                    IContent up = new UserProfile();
                    up.showContent(content);
                    content.revalidate();
                    content.repaint();
                    // switch to home page

                }
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "update fail");
                    throw exception;
                }
            }
        });


        // addActionListener
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                IContent up = new UserProfile();
                up.showContent(content);
                content.revalidate();
                content.repaint();
            }
        });
        return "Switch to Update";
    }
}
