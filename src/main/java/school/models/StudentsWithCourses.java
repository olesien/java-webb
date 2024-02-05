package school.models;

public class StudentsWithCourses {
    private int id;

    public String name;

    public String town;

    public String hobby;

    public String courses;

    public StudentsWithCourses(int id, String name, String town, String hobby, String courses) {
        this.id = id;
        this.name = name;
        this.town = town;
        this.hobby = hobby;
        this.courses = courses;
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

    public String getCourses() {
        return courses;
    }
}
