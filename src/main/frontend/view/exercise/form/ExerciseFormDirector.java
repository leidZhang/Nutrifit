package main.frontend.view.exercise.form;

import main.frontend.common.ContentBuilder;

public class ExerciseFormDirector {
    private ExerciseFormBuilder builder;

    public ExerciseFormDirector(ContentBuilder builder) {
        this.builder = (ExerciseFormBuilder) builder;
    }

    public void constructPage(String title) {
        builder.clearPage(); // call this function in every build process
        builder.setUp();

        builder.buildTitle(title);
        builder.buildMainContent();
    }
}