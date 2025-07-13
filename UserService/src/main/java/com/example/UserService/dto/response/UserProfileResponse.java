package com.example.UserService.dto.response;

public class UserProfileResponse {
    private String username;
    private int age;
    private String fullName;
    private String address;
    private String email;


    public UserProfileResponse(String username, int age, String fullName, String address, String email) {
        this.username = username;
        this.age = age;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserProfileResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
