package main.frontend.view.user.profile.common;

import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.common.Content;
import main.frontend.custom.entry.NfEntry;

import javax.swing.*;
import java.sql.Date;
import java.util.Map;

public abstract class UserFormPage extends Content { // change to public abstract in D3!
    protected IUserController controller = new UserController();
    protected Map<String, NfEntry> entries;
    protected boolean editable = false;

    protected User getNewUser(Map<String, NfEntry> entries) {
        String name = entries.get("Name").getInput();
        String username = entries.get("Username").getInput();
        Date dateOfBirth = Date.valueOf(entries.get("Date of Birth").getInput());
        String sex = entries.get("Sex").getInput();
        String password = entries.get("Password").getInput();
        double weight = Double.parseDouble(entries.get("Weight (kg)").getInput());
        double height = Double.parseDouble(entries.get("Height (cm)").getInput());

        // create new user
        return new User(name, username, password, sex, dateOfBirth, height, weight);
    }

    protected void setEntryRegex(Map<String, NfEntry> entries) {
        entries.get("Name").setRegex(
                "^[\\p{L}']+(\\s[\\p{L}']+)*$",
                "Illegal Name"
        );
        entries.get("Username").setRegex(
                "^[a-zA-Z0-9]+$",
                "Username must be alphanumeric"
        );
        entries.get("Date of Birth").setRegex(
                "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",
                "data input format should be yyyy-mm-dd"
        ); // set date format
        entries.get("Sex").setRegex(
                "^(male|female)$",
                "Input should be male or female"
        );
        entries.get("Password").setRegex(
                "^.{7,}$",
                "Length of password must be greater than 6"
        );
        entries.get("Weight (kg)").setRegex(
                "^\\d+(\\.\\d+)?$",
                "Weight value must be a positive number"
        ); // delete [+-] in code refactor stage
        entries.get("Height (cm)").setRegex(
                "^\\d+(\\.\\d+)?$",
                "Height value must be positives number"
        ); // delete [+-] in code refactor stage
    }

    protected boolean verifyInput(Map<String, NfEntry> entries) {
        boolean flag = true;

        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            flag = flag & entry.getValue().verifyInput();
        }

        return flag;
    }

    @Override
    public abstract String showContent(JPanel content); // avoided refused bequest here
}
