package main.backend.exercise;

import java.sql.Date;

public class ExerciseData { // you decide leave this or not
    private final Date date;
    private final String type;
    private final String intensity;
    private final int duration;

    public ExerciseData(Date date, String type, String intensity, int duration) {
        this.date = date;
        this.type = type;
        this.intensity = intensity;
        this.duration = duration;
    }

    public Date getDate() { return date; }

    public String getType() { return type; }

    public String getIntensity() { return intensity; }

    public int getDuration() { return duration; }
}
