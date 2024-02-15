package models;

import java.io.Serializable;

public class Courses implements Serializable {
    private int id;

    public String name;

    public String description;

    public int yhp;

    public Courses() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getYhp() {
        return yhp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setYhp(int yhp) {
        this.yhp = yhp;
    }
}

