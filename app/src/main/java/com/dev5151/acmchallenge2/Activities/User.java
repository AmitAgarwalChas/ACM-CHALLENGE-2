package com.dev5151.acmchallenge2.Activities;

public class User {
    String userId, userName, age, contact, email;

    public User() {
    }

    public User(String userId, String userName, String age, String contact, String email) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
        this.contact = contact;
        this.email = email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getAge() {
        return age;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }
}
