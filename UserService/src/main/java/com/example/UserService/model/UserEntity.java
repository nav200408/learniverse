package com.example.UserService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String address;
    private int age;
    private String userName;
    private String password;
    private String role;
    private boolean isAccountNonLock;
    private String email;

    public UserEntity() {
    }

    public UserEntity(int id, String fullName, String address, int age, String userName, String password, String role, boolean isAccountNonLock, String email) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.isAccountNonLock = isAccountNonLock;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccountNonLock() {
        return isAccountNonLock;
    }

    public void setAccountNonLock(boolean accountNonLock) {
        isAccountNonLock = accountNonLock;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
