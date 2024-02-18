package models.db;

import enums.PrivType;
import models.Courses;
import models.Students;
import models.StudentsWithCourses;
import models.Teachers;

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

                Students student = new Students();
                student.setId(result.getInt("id"));
                student.setFname(result.getString("fname"));
                student.setLname(result.getString("lname"));
                student.setTown(result.getString("town"));
                student.setHobby(result.getString("hobby"));

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

                Courses course = new Courses();
                course.setId( result.getInt("id"));
                course.setName( result.getString("name"));
                course.setDescription( result.getString("description"));
                course.setYhp( result.getInt("yhp"));

                courses.add(course);
            }
        return courses;
    }

    public static ArrayList<Courses> getCoursesByStudentId(Integer student_id) throws SQLException {
        ArrayList<Courses> courses = new ArrayList<>();
            String query = "SELECT DISTINCT(c.id), c.name, c.description, c.yhp FROM courses c INNER JOIN attendance sc ON c.id = sc.course_id WHERE sc.student_id = ? GROUP BY c.id";
            PreparedStatement ps = Database.getConnection().prepareStatement(query);
            ps.setInt(1, student_id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {

                Courses course = new Courses();
                course.setId( result.getInt("id"));
                course.setName( result.getString("name"));
                course.setDescription( result.getString("description"));
                course.setYhp( result.getInt("yhp"));

                courses.add(course);
            }

        return courses;
    }

    public static ArrayList<Courses> getCoursesByTeacherId(Integer teacher_id) throws SQLException {
        ArrayList<Courses> courses = new ArrayList<>();
            String query = "SELECT DISTINCT(c.id), c.name, c.description, c.yhp FROM courses c INNER JOIN teacher_courses tc ON c.id = tc.course_id WHERE tc.teachers_id = ? GROUP BY c.id";
            PreparedStatement ps = Database.getConnection().prepareStatement(query);
            ps.setInt(1, teacher_id);
            System.out.println(ps);
            ResultSet result = ps.executeQuery();
            while (result.next()) {

                Courses course = new Courses();
                course.setId( result.getInt("id"));
                course.setName( result.getString("name"));
                course.setDescription( result.getString("description"));
                course.setYhp( result.getInt("yhp"));

                courses.add(course);
            }
        return courses;
    }

    public static ArrayList<StudentsWithCourses> getStudentsWithCourses() throws SQLException {
        ArrayList<StudentsWithCourses> studentsWithCourses = new ArrayList<>();
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
        return studentsWithCourses;
    }

    public static Students addStudent(String fname,String lname, String town, String hobby, String email, String phone, String username, String password) throws SQLException {
            String query = "INSERT INTO students (fname, lname, town, hobby, email, phone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println(query);
            PreparedStatement ps = Database.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
                    Students student = new Students();

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
    public static Teachers addTeacher(String fname, String lname, String town, String hobby, String email, String phone, String username, String password, PrivType priv) throws SQLException {
        String query = "INSERT INTO teachers (fname, lname, town, hobby, email, phone, username, password, privilage_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = Database.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
                Teachers teacher = new Teachers();

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

    public static Students getStudentByUsername(String username) throws SQLException {
            //fname, lname, town, hobby, email, phone, username, password
            String query = "SELECT id, fname, lname, town, hobby, email, phone, username, password from students WHERE username = ? LIMIT 1";
            PreparedStatement ps = Database.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Students student = new Students();
                student.setId(result.getInt("id"));
                student.setFname(result.getString("fname"));
                student.setLname(result.getString("lname"));
                student.setTown(result.getString("town"));
                student.setHobby(result.getString("hobby"));
                student.setEmail(result.getString("email"));
                student.setPhone(result.getString("phone"));
                student.setUsername(result.getString("username"));
                student.setPassword(result.getString("password"));

                return student;
            }
        return null;
    }

    public static Teachers getTeacherByUsername(String username) throws SQLException {
            //fname, lname, town, hobby, email, phone, username, password
            String query = "SELECT id, fname, lname, town, hobby, email, phone, username, password, privilage_type from teachers WHERE username = ? LIMIT 1";
            PreparedStatement ps = Database.getConnection().prepareStatement(query);
            ps.setString(1, username);
            System.out.println(ps);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Teachers teacher = new Teachers();
                teacher.setId(result.getInt("id"));
                teacher.setFname(result.getString("fname"));
                teacher.setLname(result.getString("lname"));
                teacher.setTown(result.getString("town"));
                teacher.setHobby(result.getString("hobby"));
                teacher.setEmail(result.getString("email"));
                teacher.setPhone(result.getString("phone"));
                teacher.setUsername(result.getString("username"));
                teacher.setPassword(result.getString("password"));
                teacher.setPrivType(PrivType.valueOf(result.getString("privilage_type")));

                return teacher;
            }
        return null;
    }
}
