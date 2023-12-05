package main.frontend.common;

public class Director {
    protected ContentBuilder builder;

    public Director(ContentBuilder builder) {
        this.builder = builder;
    }

    public void constructPage(String title) {
        builder.clearPage();
        builder.setUp();

        builder.buildTitle(title);
        builder.buildMainContent();
    }
}
