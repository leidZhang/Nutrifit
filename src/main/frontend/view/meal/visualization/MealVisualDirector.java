package main.frontend.view.meal.visualization;

import main.frontend.common.ContentBuilder;

public class MealVisualDirector {
    MealVisualBuilder builder;

    public MealVisualDirector(ContentBuilder builder) {
        this.builder = (MealVisualBuilder) builder;
    }

    public void constructPage(String title) {
        builder.clearPage();
        builder.setUp();

        builder.buildTitle(title);
        builder.buildMainContent();
    }
}
