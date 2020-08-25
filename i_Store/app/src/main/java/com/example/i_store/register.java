package com.example.i_store;

public class register {
    String email, password, first_name, last_name,uid;
    String num,rate,rated_by;



    public register(String email, String password, String first_name, String last_name, String uid, String num,String rate,String rated_by)
    {
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.uid = uid;
        this.num = num;
        this.rate=rate;
        this.rated_by=rated_by;
    }

    public String getRated_by() {
        return rated_by;
    }

    public String getNum() {
        return num;
    }

    public register(){}

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getRate() {
        return rate;
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