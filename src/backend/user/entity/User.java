package backend.user.entity;

import java.sql.Date;

public class User {
    private String name;
    private String username;
    private String sex;
    private Date dateOfBirth;
    private double height; // cm
    private double weight; // m
    private int age;

    public User(String name, String username, String sex, Date dateOfBirth, double height, double weight, int age) {
        this.name = name;
        this.username = username;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getSex() {
        return sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }
}
