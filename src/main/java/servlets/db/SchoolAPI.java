package servlets.db;

import models.Courses;
import models.Students;
import models.StudentsWithCourses;

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
}
