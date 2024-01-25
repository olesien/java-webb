package models;

public class Students {
    private int id;

    private String name;

    private String town;

    private String hobby;

    public Students(int id, String name, String town, String hobby) {
        this.id = id;
        this.name = name;
        this.town = town;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }

    public String getHobby() {
        return hobby;
    }
}
