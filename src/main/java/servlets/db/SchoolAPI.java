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

    public static ArrayList<Students> getStudents() {
        ArrayList<Students> students = new ArrayList<>();
        try {
            Statement statement = Database.getConnection().createStatement();
            String query = "SELECT * FROM students";
            System.out.println(query);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {

                Students student = new Students();
                student.setId(result.getInt("id"));
                student.setFname(result.getString("fname"));
                student.setLname(result.getString("lname"));
                student.setTown(result.getString("town"));
                student.setHobby(result.getString("hobby"));

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

                Courses course = new Courses();
                course.setId( result.getInt("id"));
                course.setName( result.getString("name"));
                course.setDescription( result.getString("description"));
                course.setYhp( result.getInt("yhp"));

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
            String query = "SELECT s.id, s.fname, s.lname, s.town, s.hobby, IFNULL(GROUP_CONCAT(c.name SEPARATOR ', '), '') as courselist FROM students s LEFT JOIN attendance a ON s.id = a.student_id LEFT JOIN courses c ON c.id = a.course_id GROUP BY s.id";
            System.out.println(query);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {

                StudentsWithCourses studentsWithCourse = new StudentsWithCourses();
                studentsWithCourse.setId(result.getInt("id"));
                studentsWithCourse.setFname( result.getString("fname"));
                studentsWithCourse.setLname( result.getString("lname"));
                studentsWithCourse.setTown(result.getString("town"));
                studentsWithCourse.setHobby(result.getString("hobby"));
                studentsWithCourse.setCourses(result.getString("courselist"));

                studentsWithCourses.add(studentsWithCourse);
            }
        } catch(SQLException ex) {
            Database.PrintSQLException(ex);
        }
        return studentsWithCourses;
    }

    public static Students addStudent(String fname,String lname, String town, String hobby) throws SQLException {
            String query = "INSERT INTO students (fname, lname, town, hobby) VALUES (?, ?, ?, ?)";
            System.out.println(query);
            PreparedStatement ps = Database.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, town);
            ps.setString(4, hobby);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Students student = new Students();

                    student.setId((int) generatedKeys.getLong(1));
                    student.setFname(fname);
                    student.setLname(lname);
                    student.setTown(town);
                    student.setHobby(hobby);
                    return student;
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
                throw new SQLException("Creating course failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Courses course = new Courses();
                    course.setId((int) generatedKeys.getLong(1));
                    course.setName(name);
                    course.setDescription(description);
                    course.setYhp(yhp);
                    return course;
                }
                else {
                    throw new SQLException("Creating course failed, no ID obtained.");
                }
            }

    }
    public static void addStudentCourseRelation(Integer student_id, Integer course_id) throws SQLException {
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
                   return;
                }
                else {
                    throw new SQLException("Creating relation failed, no ID obtained.");
                }
            }
    }
}
