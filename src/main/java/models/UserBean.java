package models;

import enums.PrivType;
import enums.UserType;

import java.io.Serializable;

public class UserBean implements Serializable {
    private UserType userType;
    private PrivType privType;

    private int id;

    private String username;

    private String fname;

    private String lname;

    private String email;

    public UserBean() {

    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public PrivType getPrivType() {
        return privType;
    }

    public void setPrivType(PrivType privType) {
        this.privType = privType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
