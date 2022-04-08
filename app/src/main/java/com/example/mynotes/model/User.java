package com.example.mynotes.model;

public class User {
    public User(String id, String name, String mobile, String email, String password) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private String id,name,mobile,email,password;
}
