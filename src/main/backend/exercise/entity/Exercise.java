package main.backend.exercise.entity;

import java.sql.Date;

public class Exercise {
    private int id;
    private Date date;
    private String type;
    private String intensity;
    private int duration;
    private int burnCalories;

    public Exercise(Date date, String type, String intensity, int duration, int burnCalories) {
        this.date = date;
        this.type = type;
        this.intensity = intensity;
        this.duration = duration;
        this.burnCalories = burnCalories;
    }

    public Exercise(int id, Date date, String type, String intensity, int duration, int burnCalories) {
        this(date, type, intensity, duration, burnCalories);
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

}
