package main.frontend.view.user.userprofile;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.view.mainframe.FrontEnd;
import main.frontend.common.Content;
import main.frontend.common.PageBuilder;
import main.frontend.component.NfEntry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Map;

public class RegisterPage extends Content {
    private String pageName = "Register Page";
    private IUserController controller = new UserController();
    private Map<String, NfEntry> entries;

    public User getNewUser() {
        // get new input
        String name = entries.get("Name").getInput();
        String username = entries.get("Username").getInput();
        Date dateOfBirth = Date.valueOf(entries.get("Date of Birth").getInput());
        String sex = entries.get("Sex").getInput();
        double weight = Double.parseDouble(entries.get("Weight (kg)").getInput());
        double height = Double.parseDouble(entries.get("Height (cm)").getInput());
        int age = Integer.parseInt(entries.get("Age").getInput());

        // create new user
        return new User(name, username, sex, dateOfBirth, height, weight, age);
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

    @Override
    public String showContent(JPanel content, FrontEnd frontEnd) {
        // add listener
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // verify input
                System.out.println(verifyInput());
                if (!verifyInput()) return;

                User newUser = getNewUser();
                Result res = controller.save(newUser);

                // submit result
                if (res.getCode().equals("200")) {
                    JOptionPane.showMessageDialog(content, "Information updated!", "Message", JOptionPane.INFORMATION_MESSAGE);

                    // Remove the login page components from the content panel
                    content.removeAll();

                    // Revalidate and repaint to update the content panel
                    content.revalidate();
                    content.repaint();

                    frontEnd.initialize();
                } else {
                    JOptionPane.showMessageDialog(content, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        // construct page
        PageBuilder builder = new UserProfileBuilder(content);
        UserProfileDirector director = new UserProfileDirector(builder);
        director.constructPage("My Profile", listener);

        // get entries and setup entries
        entries = ((UserProfileBuilder) builder).getFormData();
        setEntryRegex();

        return "Switch to " + pageName;
    }
}
