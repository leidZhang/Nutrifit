package main.frontend.view.user.userprofile;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.view.mainframe.FrontEnd;
import main.frontend.common.Content;
import main.frontend.common.ContentBuilder;
import main.frontend.component.NfEntry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Map;

public class UserProfilePage extends Content {
    private String pageName = "UserProfile";
    private IUserController controller = new UserController();
    private Map<String, NfEntry> entries;
    private boolean editable = false;

    public void displayUserInfo(User user) {
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

    public User getNewUserInfo(int id) {
        // get new input
        String name = entries.get("Name").getInput();
        String username = entries.get("Username").getInput();
        Date dateOfBirth = Date.valueOf(entries.get("Date of Birth").getInput());
        String sex = entries.get("Sex").getInput();
        double weight = Double.parseDouble(entries.get("Weight (kg)").getInput());
        double height = Double.parseDouble(entries.get("Height (cm)").getInput());
        int age = Integer.parseInt(entries.get("Age").getInput());

        // create new user
        return new User(id, name, username, sex, dateOfBirth, height, weight);
    }

    private void setEntryRegex() {
        entries.get("Date of Birth").setRegex(
                "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",
                "data input format should be yyyy-mm-dd"
                ); // set date format
    }

    private boolean verifyInput() {
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

    @Override
    public String showContent(JPanel content, FrontEnd frontEnd) {
        // get user from session
        User user = instance.getUser();

        // add submit listener
        ActionListener submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // verify input
                System.out.println(verifyInput());
                if (!verifyInput()) return;
                if (!editable) return;

                User newUser = getNewUserInfo(user.getId());
                Result res = controller.updateUser(newUser);

                // submit result
                if (res.getCode().equals("200")) {
                    Result userResult = controller.getUser(newUser.getUsername());

                    if (userResult.getCode().equals("200")) {
                        User user = (User) userResult.getData();
                        instance.setUser(user); // update session
                        displayUserInfo(user); // update form

                        editable = false; // update editable
                        setEditable(false); // disable entries 

                        JOptionPane.showMessageDialog(content, "Information updated!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(content, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(content, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        // add modify listener
        ActionListener modifyListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Modify Button clicked!");

                editable = !editable;
                if (editable) {
                    setEditable(true);
                } else {
                    setEditable(false);
                }
            }
        };

        // construct page
        ContentBuilder builder = new UserProfileBuilder(content);
        UserProfileDirector director = new UserProfileDirector(builder);
        director.constructPage("My Profile", submitListener, modifyListener);

        // get entries and setup entries
        entries = ((UserProfileBuilder) builder).getFormData();
        displayUserInfo(user);
        setEntryRegex();

        return "Switch to " + pageName;
    }
}
