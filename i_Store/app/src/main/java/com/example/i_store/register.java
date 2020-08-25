package com.example.i_store;

public class register {
    String email, password, first_name, last_name,uid;

    public register(String uid,String email, String password, String first_name, String last_name) {
        this.uid=uid;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public register(){}
    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
}