package main.frontend.view.user.userprofile;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.common.Content;
import main.frontend.common.ContentBuilder;
import main.frontend.custom.NfEntry;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Map;

public class UserProfilePage extends Content {
    private String pageName = "UserProfile";
    private IUserController controller = new UserController();
    private Map<String, NfEntry> entries;
    private boolean editable = false;

    private void displayUserInfo(User user) {
        // get user info
        String name = user.getName();
        String username = user.getUsername();
        String dateOfBirth = String.valueOf(user.getDateOfBirth());
        String sex = user.getSex();
        String weight = String.valueOf(user.getWeight());
        String height = String.valueOf(user.getHeight());
        String age = String.valueOf(user.getAge());

        // apply to the user info form
        entries.get("Name").setEntry(name);
        entries.get("Username").setEntry(username);
        entries.get("Date of Birth").setEntry(dateOfBirth);
        entries.get("Sex").setEntry(sex);
        entries.get("Weight (kg)").setEntry(weight);
        entries.get("Height (cm)").setEntry(height);
        entries.get("Age").setEntry(age);
    }

    protected User getNewUser(Map<String, NfEntry> entries) {
        String name;
        try {
            name = entries.get("Name").getInput();
        } catch (Exception input) {
            JOptionPane.showMessageDialog(null, "Invalid name value");
            throw new IllegalArgumentException("Invalid name value: " + entries.get("Name").getInput(), input);
        }
        String username;
        try {
            username = entries.get("Username").getInput();
        } catch (Exception input) {
            JOptionPane.showMessageDialog(null, "Invalid username value");
            throw new IllegalArgumentException("Invalid username value: " + entries.get("Username").getInput(), input);
        }
        Date dateOfBirth;
        try {
            dateOfBirth = Date.valueOf(entries.get("Date of Birth").getInput());
        } catch (Exception input) {
            JOptionPane.showMessageDialog(null, "Invalid dateOfBirth value");
            throw new IllegalArgumentException("Invalid dateOfBirth value: " + Date.valueOf(entries.get("Date of Birth").getInput()), input);
        }
        String sex;
        try {
            sex = entries.get("Sex").getInput();
        } catch (Exception input) {
            JOptionPane.showMessageDialog(null, "Invalid sex value");
            throw new IllegalArgumentException("Invalid sex value: " + entries.get("Sex").getInput(), input);
        }
        double weight;
        try {
            weight = Double.parseDouble(entries.get("Weight (kg)").getInput());
        } catch (Exception input) {
            JOptionPane.showMessageDialog(null, "Invalid weight value");
            throw new IllegalArgumentException("Invalid weight value: " + Double.parseDouble(entries.get("Weight (kg)").getInput()), input);
        }
        double height;
        try {
            height = Double.parseDouble(entries.get("Height (cm)").getInput());
        } catch (Exception input) {
            JOptionPane.showMessageDialog(null, "Invalid height value");
            throw new IllegalArgumentException("Invalid height value: " + Double.parseDouble(entries.get("Height (cm)").getInput()), input);
        }

        // create new user
        return new User(name, username, sex, dateOfBirth, height, weight);
    }

    private User getNewUserInfo(int id) {
        // get new input
        User user = getNewUser(entries);
        user.setId(id);

        return user;
    }

    protected void setEntryRegex(Map<String, NfEntry> entries) {
        entries.get("Date of Birth").setRegex(
                "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",
                "data input format should be yyyy-mm-dd"
                ); // set date format
    }

    protected boolean verifyInput(Map<String, NfEntry> entries) {
        boolean flag = true;

        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            flag = flag & entry.getValue().verifyInput();
        }

        return flag;
    }

    private void setEditable(boolean flag) {
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            if (entry.getKey().equals("Age")) continue;
            entry.getValue().setEditable(flag);
        }
    }

    private ActionListener handleSubmit(JPanel content, User user) {
        ActionListener listener = e -> {
            // verify input
            System.out.println(verifyInput(entries));
            if (!verifyInput(entries)) return;
            if (!editable) return;

            User newUser = getNewUserInfo(user.getId());
            Result res = controller.updateUser(newUser);

            // submit result
            if (res.getCode().equals("200")) {
                Result userResult = controller.getUser(newUser.getUsername());

                if (userResult.getCode().equals("200")) {
                    User user1 = (User) userResult.getData();
                    instance.setUser(user1); // update session
                    displayUserInfo(user1); // update form

                    editable = false; // update editable
                    setEditable(false); // disable entries

                    JOptionPane.showMessageDialog(content, "Information updated!", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(content, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(content, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        return listener;
    }

    private ActionListener handleModify() {
        ActionListener listener = e -> {
            System.out.println("Modify Button clicked!");

            editable = !editable;
            setEditable(editable);
        };

        return listener;
    }

    @Override
    public String showContent(JPanel content) {
        // get user from session
        User user = instance.getUser();

        ActionListener submitListener = handleSubmit(content, user); // add submit listener
        ActionListener modifyListener = handleModify(); // add modify listener

        // construct page
        ContentBuilder builder = new UserProfileBuilder(content);
        UserProfileDirector director = new UserProfileDirector(builder);
        director.constructPage("My Profile", submitListener, modifyListener);

        // get entries and setup entries
        entries = ((UserProfileBuilder) builder).getFormData();
        displayUserInfo(user);
        setEntryRegex(entries);

        return "Switch to " + pageName;
    }
}
