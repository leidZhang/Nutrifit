package main.frontend.view.meal.form.add;

import main.frontend.common.ContentBuilder;

public class MealAddDirector {
    private ContentBuilder builder;

    public MealAddDirector(ContentBuilder builder) {
        this.builder = builder;
    }

    public void constructPage(String title) {
        builder.clearPage();
        builder.setUp();

        builder.buildTitle(title);
        builder.buildMainContent();
    }
}
