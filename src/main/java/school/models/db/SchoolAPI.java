package school.models.db;

import school.models.Courses;
import school.models.Students;
import school.models.StudentsWithCourses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SchoolAPI {

    private SchoolAPI() { }

    public static ArrayList<Students> getStudents() {
        ArrayList<Students> students = new ArrayList<>();
        try {
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
        } catch(SQLException ex) {
            Database.PrintSQLException(ex);
        }
        return students;
    }

    public static ArrayList<Courses> getCourses() {
        ArrayList<Courses> courses = new ArrayList<>();
        try {
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
        } catch(SQLException ex) {
            Database.PrintSQLException(ex);
        }
        return courses;
    }

    public static ArrayList<StudentsWithCourses> getStudentsWithCourses() {
        ArrayList<StudentsWithCourses> studentsWithCourses = new ArrayList<>();
        try {
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
        } catch(SQLException ex) {
            Database.PrintSQLException(ex);
        }
        return studentsWithCourses;
    }

    public static Students addStudent(String name, String town, String hobby) {
        try {
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
        } catch(SQLException ex) {
            Database.PrintSQLException(ex);
        }
        return null;
    }

    public static Courses addCourse(String name, String description, Integer yhp) {
        try {
            String query = "INSERT INTO courses (name, description, yhp) VALUES (?, ?, ?)";
            System.out.println(query);
            PreparedStatement ps = Database.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, yhp);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating course failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Courses((int) generatedKeys.getLong(1), name, description, yhp);
                }
                else {
                    throw new SQLException("Creating course failed, no ID obtained.");
                }
            }
        } catch(SQLException ex) {
            Database.PrintSQLException(ex);
        }
        return null;
    }
    public static boolean addStudentCourseRelation(Integer student_id, Integer course_id) {
        try {
            String query = "INSERT INTO attendance (student_id, course_id) VALUES (?, ?)";
            System.out.println(query);
            PreparedStatement ps = Database.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, student_id);
            ps.setInt(2, course_id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating relation failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    //return new StudentsWithCourses((int) generatedKeys.getLong(1), name, description, yhp);
                    return true;
                }
                else {
                    throw new SQLException("Creating relation failed, no ID obtained.");
                }
            }
        } catch(SQLException ex) {
            Database.PrintSQLException(ex);
        }
        return false;
    }
}
