package com.example.UserService.dto.request;

public class UserProfileRequest {
    private int age;
    private String fullName;
    private String address;

    public UserProfileRequest() {
    }

    public UserProfileRequest(int age, String fullName, String address) {
        this.age = age;
        this.fullName = fullName;
        this.address = address;
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
