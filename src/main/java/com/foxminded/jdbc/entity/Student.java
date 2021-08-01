package com.foxminded.jdbc.entity;

public class Student {
    private Long id;
    private Long groupId;
    private String name;
    private String lastname;

    public Student() {
    }

    public Student(Long id, Long groupId, String name, String lastname) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

