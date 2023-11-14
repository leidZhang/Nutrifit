package main.backend.user.entity;

import java.sql.Date;

public class User {
    private int id;
    private String name;
    private String username;
    private String sex;
    private Date dateOfBirth;
    private double height; // cm
    private double weight; // kg
    private int age;
    private String password;

    public User(String name, String username, String sex, Date dateOfBirth, double height, double weight) {
        this.name = name;
        this.username = username;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
    }

    public User(String name, String username, String password, String sex, Date dateOfBirth, double height, double weight) {
        this(name, username, sex, dateOfBirth, height, weight);
        this.password = password;
    }

    public User(int id, String name, String username, String password, String sex, Date dateOfBirth, double height, double weight) {
        this(name, username, password, sex, dateOfBirth, height, weight);
        this.id = id;
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

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }
}
