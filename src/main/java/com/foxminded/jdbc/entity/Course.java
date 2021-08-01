package com.foxminded.jdbc.entity;

public class Course {
    private long id;
    private String name;
    private String discription;

    public Course() {

    }

    public Course(long id, String name, String discription) {
        this.id = id;
        this.name = name;
        this.discription = discription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
