package main.frontend.view.exercise;

import main.frontend.common.ContentBuilder;

public class ExerciseDirector {
    private ExerciseBuilder builder;


    public ExerciseDirector(ContentBuilder builder) {
        this.builder = (ExerciseBuilder) builder;
    }

    public void constructPage() {
        builder.setUp();
        builder.buildMainContent();
    }
}