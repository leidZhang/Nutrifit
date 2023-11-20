package main.frontend.view.meal.form.detail;

import main.frontend.common.ContentBuilder;

public class MealDetailDirector {
    MealDetailBuilder builder;

    public MealDetailDirector(ContentBuilder builder) {
        this.builder = (MealDetailBuilder) builder;
    }

    public void constructPage(String title) {
        builder.clearPage();
        builder.setUp();

        builder.buildTitle(title);
        builder.buildMainContent();
    }
}
