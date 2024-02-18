package servlets;

import models.Courses;
import models.Students;
import models.StudentsWithCourses;
import models.db.SchoolAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/student_courses")
public class StudentCoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<StudentsWithCourses> studentsWithCourses = null;
        try {
            studentsWithCourses = SchoolAPI.getStudentsWithCourses();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Students> students = null;
        try {
            students = SchoolAPI.getStudents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Courses> courses = null;
        try {
            courses = SchoolAPI.getCourses();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("name", "Student Courses");
        req.setAttribute("students", students);
        req.setAttribute("courses", courses);
        req.setAttribute("students_with_courses", studentsWithCourses);

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/student_courses.jsp");
        dispatcher.forward(req, resp);

        System.out.println("GET Request");
        System.out.println(req.getParameter("name"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("received post for course+student relation");
        Integer student_id = Integer.valueOf(req.getParameter("student"));
        Integer course_id = Integer.valueOf(req.getParameter("course"));
        try {
            SchoolAPI.addStudentCourseRelation(student_id, course_id);
            resp.sendRedirect("/student_courses?status=success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
