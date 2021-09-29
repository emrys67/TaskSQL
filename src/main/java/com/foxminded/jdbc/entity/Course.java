package com.foxminded.jdbc.entity;

public class Course {
    private Long id;
    private String name;
    private String discription;

    public Course() {
    }

    public Course(Long id, String name, String discription) {
        this.id = id;
        this.name = name;
        this.discription = discription;
    }

    public Course(String name, String discription) {
        this.name = name;
        this.discription = discription;
    }

    public Long getId() {
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discription='" + discription + '\'' +
                '}';
    }
}
