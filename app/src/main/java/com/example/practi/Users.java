package com.example.practi;

public class Users {
    private String id,fullname,username,email,tipe;

    public Users() {
    }

    public Users(String id, String fullname, String username, String email, String tipe) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.tipe = tipe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
}
