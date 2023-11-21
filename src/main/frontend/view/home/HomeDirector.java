package main.frontend.view.home;

import main.frontend.common.ContentBuilder;

public class HomeDirector {
    private ContentBuilder builder;

    public HomeDirector(ContentBuilder builder) {
        this.builder = builder;
    }

    public void constructPage(String title) {
        builder.clearPage();
        builder.setUp();

        builder.buildTitle(title);
        builder.buildMainContent();
    }
}
