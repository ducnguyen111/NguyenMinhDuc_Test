package com.devandroid.test;

public class User {
   private String id;
     private String email;
   private String brithday;
   private  String pass;

    public User(String id, String email, String brithday,String pass) {
        this.id = id;
        this.email = email;
        this.brithday = brithday;
        this.pass=pass;
    }

    public User() {
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }
}
