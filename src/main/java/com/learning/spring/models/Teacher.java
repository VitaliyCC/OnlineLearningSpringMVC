package com.learning.spring.models;

public class Teacher {
    private static Integer countTeacher=0;
    private Integer teacherId;
    private Integer id;
    private String surname;
    private String name;
    private Integer salary;
    private String patronymic;

    public Teacher() {
    }

    public Teacher(Integer teacherId, Integer id, String surname, String name, Integer salary, String patronymic) {
        this.teacherId = teacherId;
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.salary = salary;
        this.patronymic = patronymic;
    }

    public static Integer getCountTeacher() {
        return countTeacher;
    }

    public static void setCountTeacher(Integer countTeacher) {
        Teacher.countTeacher = countTeacher;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void save(Teacher teacher) {

    }
}
