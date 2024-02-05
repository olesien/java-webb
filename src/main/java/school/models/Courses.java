package school.models;

public class Courses {
    private int id;

    public String name;

    public String description;

    public int yhp;

    public Courses(int id, String name, String description, int yhp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.yhp = yhp;
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
}

