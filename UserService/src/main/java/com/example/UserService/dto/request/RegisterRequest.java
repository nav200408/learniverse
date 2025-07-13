package com.example.UserService.dto.request;

public class RegisterRequest {
    private String fullName;
    private String address;
    private int age;
    private String username;
    private String password;
    private String email;

    public RegisterRequest() {
    }

    public RegisterRequest(String fullName, String address, int age, String username, String password, String email) {
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.username = username;
        this.password = password;
        this.email = email;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
