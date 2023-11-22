package main.frontend.view.exercise.visualization;

import main.frontend.common.ContentBuilder;

public class ExerciseVisualDirector {
    private ContentBuilder builder;

    public ExerciseVisualDirector(ContentBuilder builder) {
        this.builder = (ExerciseVisualBuilder) builder;
    }

    public void constructPage(String title) {
        builder.clearPage();
        builder.setUp();

        builder.buildTitle(title);
        builder.buildMainContent();
    }
}
