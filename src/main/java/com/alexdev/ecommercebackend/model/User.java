package com.alexdev.ecommercebackend.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String user;
    private String password;
    private String direction;
    private String phone;
    private String rol;

    // constructors ...

    public User(int id, String name, String email, String user, String password, String direction, String phone, String rol) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.user = user;
        this.password = password;
        this.direction = direction;
        this.phone = phone;
        this.rol = rol;
    }

    public User() {
    }


    // getters and setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    // to String ..

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", direction='" + direction + '\'' +
                ", phone='" + phone + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
