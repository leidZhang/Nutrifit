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

public class Registration implements IContent {

    IUserController userController = new UserController();

    UserSession instance = UserSession.getInstance();

    @Override
    public String showContent(JPanel content) {
        // set up layout
        content.removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(20, 15));

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
//        JTextField dateOfBirthField = new JTextField(20);

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
        JButton submitButton = new JButton("submit and register");
        submitButton.setFont(submitButton.getFont().deriveFont(16.0f));
        submitButton.setName("submitButton");

        //back button
        JButton backButton = new JButton("back");
        backButton.setFont(backButton.getFont().deriveFont(16.0f));

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("time selecter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            TimePickerPanel timePicker = new TimePickerPanel();
            JPanel timePanel = new JPanel();
            timePanel.add(timePicker.getComponent());
            timePanel.setName("timePanel");

            panel.add(nameLabel);
            panel.add(nameField);
            panel.add(usernameLabel);
            panel.add(usernameField);
            panel.add(sexLabel);
            panel.add(sexField);
            panel.add(dateOfBirthLabel);
            panel.add(timePanel);
//        panel.add(dateOfBirthField);
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
                        Date selectedTime = timePicker.getSelectedTime();
                        // Create a SimpleDateFormat object to format a date into "yyyy-MM-dd" format
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        // Convert a Date object to a string using the format method of SimpleDateFormat
                        String formattedDate = dateFormat.format(selectedTime);
                        System.out.println("Convert the current date to yyyy-MM-dd format: " + formattedDate);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date;
                        try {
                            date = format.parse(formattedDate);
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        double height = Double.parseDouble(heightField.getText());
                        double weight = Double.parseDouble(weightField.getText());
                        int age = Integer.parseInt(ageField.getText());

                        // Registration logic is performed here, you can save the username to the database or perform other operations
                        User userNow = new User(name, username, sex, sqlDate, height, weight, age);
                        Result res = userController.save(userNow);
                        System.out.println(res.getCode());
                        if (Objects.equals(res.getCode(), "200")) {
                            instance.setUser(userNow);
                            content.removeAll();
                            JOptionPane.showMessageDialog(null, "register success");
                            IContent up = new UserProfile();
                            up.showContent(content);
                            content.revalidate();
                            content.repaint();
                            // switch to user profile page
                        }
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "update fail");
                        throw exception;
                    }
                }
            });
        });

        // addActionListener
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                IContent index = new Index();
                index.showContent(content);
                content.revalidate();
                content.repaint();
            }
        });


        return "Switch to Registration";
    }
}
