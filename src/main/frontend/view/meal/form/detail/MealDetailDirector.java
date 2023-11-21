package main.frontend.view.meal.form.detail;

import main.frontend.common.ContentBuilder;

public class MealDetailDirector {
    private ContentBuilder builder;

    public MealDetailDirector(ContentBuilder builder) {
        this.builder = builder;
    }

    public void constructPage(String title) {
        builder.clearPage();
        builder.setUp();

        builder.buildTitle(title);
        builder.buildMainContent();
    }
}
