package main.frontend.view.user.userform;

import main.frontend.common.PageBuilder;

import java.awt.event.ActionListener;

public class UserProfileDirector {
    private UserProfileBuilder builder;

    public UserProfileDirector(PageBuilder builder) {
        this.builder = (UserProfileBuilder) builder;
    }

    public void constructPage(String title, ActionListener listener) {
        builder.setUp();
        builder.clearPage();

        builder.buildTitle(title);
        builder.buildMainContent();
        builder.buildOperations(listener);
    }
}
