package com.foxminded.jdbc.entity;

public class Student {
    private Long id;
    private Long group_id;
    private String name;
    private String lastname;

    public Student() {
    }

    public Student(Long id, Long group_id, String name, String lastname) {
        this.id = id;
        this.group_id = group_id;
        this.name = name;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}

