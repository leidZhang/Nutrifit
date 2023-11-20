package main.frontend.view.exercise.form;

import main.frontend.common.ContentBuilder;

import java.awt.event.ActionListener;

public class ExerciseFormDirector {
    private ExerciseFormBuilder builder;


    public ExerciseFormDirector(ContentBuilder builder) {
        this.builder = (ExerciseFormBuilder) builder;
    }

    public void constructPage(String title, ActionListener prevListener, ActionListener nextListener, ActionListener submitListener, ActionListener deleteListener) {
        builder.clearPage(); // call this function in every build process
        builder.setUp();

        builder.buildTitle(title);
        builder.buildMainContent();
        // builder.addLogTableButtons();
        builder.setTableButton(prevListener, nextListener, submitListener);
        builder.setDeleteMenuItem(deleteListener);
    }
}