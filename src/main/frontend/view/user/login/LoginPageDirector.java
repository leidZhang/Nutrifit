package main.frontend.view.user.login;

import main.frontend.common.PageBuilder;

import java.awt.event.ActionListener;

public class LoginPageDirector {
    private LoginPageBuilder builder;

    public LoginPageDirector(PageBuilder builder) {
        this.builder = (LoginPageBuilder) builder;
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
