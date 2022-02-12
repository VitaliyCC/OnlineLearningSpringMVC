package com.learning.spring.security.model;


public class User {
    private static Integer countUsers;
    private int id;
    private String login;
    private String password;
    private Role role;

    public User() {

    }
    public User( String login, String password, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public static Integer getCountUsers() {
        return countUsers;
    }

    public static void setCountUsers(Integer countUsers) {
        User.countUsers = countUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRoleString(String role){
        if(role.equals("STUDENT")){
            this.role=Role.STUDENT;
        }
        else if (role.equals("TEACHER")){
            this.role=Role.TEACHER;
        }
        else {
            this.role=Role.ADMIN;
        }
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
