package com.example.UserService.dto.response;

public class UserDTOResponse {
    private int id;
    private String fullName;
    private String address;
    private int age;
    private String userName;
    private String role;

    public UserDTOResponse(int id, String fullName, String address, int age, String userName, String role) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.userName = userName;
        this.role = role;
    }

    public UserDTOResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
