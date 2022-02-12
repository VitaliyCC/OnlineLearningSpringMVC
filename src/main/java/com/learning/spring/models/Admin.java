package com.learning.spring.models;

public class Admin {
    private static Integer countAdmins=0;
    private Integer aminId;
    private Integer id;
    private String name;
    private String patronymic;
    private String surname;

    public Admin() {
    }

    public Admin(Integer aminId, Integer id, String name, String patronymic, String surname) {
        this.aminId = aminId;
        this.id = id;
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
    }

    public static Integer getCountAdmins() {
        return countAdmins;
    }

    public static void setCountAdmins(Integer countAdmins) {
        Admin.countAdmins = countAdmins;
    }

    public Integer getAminId() {
        return aminId;
    }

    public void setAminId(Integer aminId) {
        this.aminId = aminId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        if (patronymic == null)
            return "";
        else
            return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
