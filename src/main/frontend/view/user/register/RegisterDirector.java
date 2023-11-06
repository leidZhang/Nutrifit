package main.frontend.view.user.register;

import main.frontend.common.ContentBuilder;

import java.awt.event.ActionListener;

public class RegisterDirector {
    private RegisterBuilder builder;

    public RegisterDirector(ContentBuilder builder) {
        this.builder = (RegisterBuilder) builder;
    }

    public void  constructPage(String title, ActionListener submitListener, ActionListener modifyListener) {
        builder.setUp();
        builder.clearPage();

        builder.buildTitle(title);
        builder.buildMainContent();
        builder.setHeadHorizontalSpace(150);
        builder.buildBackButton(modifyListener);
        builder.buildSubmitButton(submitListener);
        builder.setTailHorizontalSpace(400);
    }
}
