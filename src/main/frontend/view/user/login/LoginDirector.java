package main.frontend.view.user.login;

import main.frontend.common.ContentBuilder;

import java.awt.event.ActionListener;

public class LoginDirector {
    private LoginBuilder builder;

    public LoginDirector(ContentBuilder builder) {
        this.builder = (LoginBuilder) builder;
    }

    public void constructPage(String title, ActionListener register, ActionListener login) {
        builder.setUp();
        builder.clearPage();

        builder.buildTitle(title);
        builder.buildMainContent();
        builder.buildRegisterButton(register);
        builder.buildSubmitButton(login);
    }
}
