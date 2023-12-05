package main.backend.exercise.entity;

import java.sql.Date;

public class Exercise {
    private int id;
    private Date date;
    private String type;
    private String intensity;
    private int duration;
    private int burnCalories;

    public Exercise(Date date, String type, String intensity) {
        this.date = date;
        this.type = type;
        this.intensity = intensity;
    }

    public Exercise(int id, Date date, String type, String intensity) {
        this(date, type, intensity);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getIntensity() {
        return intensity;
    }

    public int getDuration() {
        return duration;
    }

    public int getBurnCalories() {
        return burnCalories;
    }

    public String getType() {
        return type;
    }

    public void setBurnCalories(int burnCalories) {
        this.burnCalories = burnCalories;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
