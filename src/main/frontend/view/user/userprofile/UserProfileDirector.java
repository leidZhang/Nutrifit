package main.frontend.view.user.userprofile;

import main.frontend.common.ContentBuilder;

import java.awt.event.ActionListener;

public class UserProfileDirector {
    private UserProfileBuilder builder;

    public UserProfileDirector(ContentBuilder builder) {
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
