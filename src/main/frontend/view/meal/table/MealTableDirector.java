package main.frontend.view.meal.table;

import main.frontend.common.ContentBuilder;

public class MealTableDirector {
    private ContentBuilder builder;

    public MealTableDirector(ContentBuilder builder) {
        this.builder = (MealTableBuilder) builder;
    }

    public void constructPage(String title) {
        builder.clearPage();
        builder.setUp();

        builder.buildTitle(title);
        builder.buildMainContent();
    }
}
