package servlets.db;

import models.Courses;
import models.Students;
import models.StudentsWithCourses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SchoolAPI {

    private SchoolAPI() { }

    public static ArrayList<Students> getStudents() throws SQLException {
        ArrayList<Students> students = new ArrayList<>();
            Statement statement = Database.getConnection().createStatement();
            String query = "SELECT * FROM students";
            System.out.println(query);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {

                Students student = new Students(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("town"),
                        result.getString("hobby"));

                students.add(student);
            }
        return students;
    }

    public static ArrayList<Courses> getCourses() throws SQLException {
        ArrayList<Courses> courses = new ArrayList<>();
            Statement statement = Database.getConnection().createStatement();
            String query = "SELECT * FROM courses";
            System.out.println(query);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {

                Courses course = new Courses(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getInt("yhp"));

                courses.add(course);
            }
        return courses;
    }

    public static ArrayList<StudentsWithCourses> getStudentsWithCourses() throws SQLException {
        ArrayList<StudentsWithCourses> studentsWithCourses = new ArrayList<>();
            Statement statement = Database.getConnection().createStatement();
            String query = "SELECT s.id, s.name, s.town, s.hobby, IFNULL(GROUP_CONCAT(c.name SEPARATOR ', '), '') as courselist FROM students s LEFT JOIN attendance a ON s.id = a.student_id LEFT JOIN courses c ON c.id = a.course_id GROUP BY s.id";
            System.out.println(query);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {

                StudentsWithCourses studentsWithCourse = new StudentsWithCourses(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("town"),
                        result.getString("hobby"),
                        result.getString("courselist"));

                studentsWithCourses.add(studentsWithCourse);
            }
        return studentsWithCourses;
    }

    public static Students addStudent(String name, String town, String hobby) throws SQLException {
            String query = "INSERT INTO students (name, town, hobby) VALUES (?, ?, ?)";
            System.out.println(query);
            PreparedStatement ps = Database.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, town);
            ps.setString(3, hobby);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Students((int) generatedKeys.getLong(1), name, town, hobby);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
    }

    public static Courses addCourse(String name, String description, Integer yhp) throws SQLException {
            String query = "INSERT INTO courses (name, description, yhp) VALUES (?, ?, ?)";
            System.out.println(query);
            PreparedStatement ps = Database.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, yhp);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Courses((int) generatedKeys.getLong(1), name, description, yhp);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
    }
}
