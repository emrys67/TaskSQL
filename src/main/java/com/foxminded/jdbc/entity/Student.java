package com.foxminded.jdbc.entity;

import java.util.Objects;

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

    public Student(String name, String lastname) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return name.equals(student.name) && lastname.equals(student.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}

