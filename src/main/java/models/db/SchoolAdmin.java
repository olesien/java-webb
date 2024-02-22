package models.db;

import enums.PrivType;
import models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SchoolAdmin {

    private SchoolAdmin() { }
    public static StudentBean addStudent(String fname, String lname, String town, String hobby, String email, String phone, String username, String password) throws SQLException {
            String query = "INSERT INTO students (fname, lname, town, hobby, email, phone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println(query);
            PreparedStatement ps = DatabaseAdmin.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, town);
            ps.setString(4, hobby);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, username);
            ps.setString(8, Auth.encrypt(password));
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    StudentBean student = new StudentBean();

                    student.setId((int) generatedKeys.getLong(1));
                    student.setFname(fname);
                    student.setLname(lname);
                    student.setTown(town);
                    student.setHobby(hobby);
                    student.setEmail(email);
                    student.setPhone(phone);
                    student.setUsername(username);
                    return student;
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
    }
    public static TeacherBean addTeacher(String fname, String lname, String town, String hobby, String email, String phone, String username, String password, PrivType priv) throws SQLException {
        String query = "INSERT INTO teachers (fname, lname, town, hobby, email, phone, username, password, privilage_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = DatabaseAdmin.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, fname);
        ps.setString(2, lname);
        ps.setString(3, town);
        ps.setString(4, hobby);
        ps.setString(5, email);
        ps.setString(6, phone);
        ps.setString(7, username);
        ps.setString(8, Auth.encrypt(password));
        ps.setString(9, priv.toString());
        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating teacher failed, no rows affected.");
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                TeacherBean teacher = new TeacherBean();

                teacher.setId((int) generatedKeys.getLong(1));
                teacher.setFname(fname);
                teacher.setLname(lname);
                teacher.setTown(town);
                teacher.setHobby(hobby);
                teacher.setEmail(email);
                teacher.setPhone(phone);
                teacher.setUsername(username);
                teacher.setPrivType(priv);
                return teacher;
            }
            else {
                throw new SQLException("Creating teacher failed, no ID obtained.");
            }
        }
    }

    public static CourseBean addCourse(String name, String description, Integer yhp) throws SQLException {
            String query = "INSERT INTO courses (name, description, yhp) VALUES (?, ?, ?)";
            System.out.println(query);
            PreparedStatement ps = DatabaseAdmin.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, yhp);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating course failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    CourseBean course = new CourseBean();
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
            PreparedStatement ps = DatabaseAdmin.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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

    public static void addTeacherCourseRelation(Integer teacher_id, Integer course_id) throws SQLException {
        String query = "INSERT INTO teacher_courses (teachers_id, course_id) VALUES (?, ?)";
        PreparedStatement ps = DatabaseAdmin.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, teacher_id);
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
