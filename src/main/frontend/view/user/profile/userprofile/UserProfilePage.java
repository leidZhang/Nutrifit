package main.frontend.view.user.profile.userprofile;

import main.backend.common.Result;
import main.backend.user.entity.User;
import main.frontend.common.ContentBuilder;
import main.frontend.custom.NfEntry;
import main.frontend.view.user.profile.common.UserFormPage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class UserProfilePage extends UserFormPage {
    private String pageName = "UserProfile";

    private void displayUserInfo(User user) {
        // get user info
        String name = user.getName();
        String username = user.getUsername();
        String dateOfBirth = String.valueOf(user.getDateOfBirth());
        String sex = user.getSex();
        String password = user.getPassword();
        String weight = String.valueOf(user.getWeight());
        String height = String.valueOf(user.getHeight());
        String age = String.valueOf(user.getAge());

        // apply to the user info form
        entries.get("Name").setEntry(name);
        entries.get("Username").setEntry(username);
        entries.get("Date of Birth").setEntry(dateOfBirth);
        entries.get("Password").setEntry(password);
        entries.get("Sex").setEntry(sex);
        entries.get("Weight (kg)").setEntry(weight);
        entries.get("Height (cm)").setEntry(height);
        entries.get("Age").setEntry(age);
    }

    private User getNewUserInfo(int id) {
        // get new input
        User user = getNewUser(entries);
        user.setId(id);

        return user;
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
