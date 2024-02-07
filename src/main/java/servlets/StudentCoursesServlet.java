package servlets;

import models.Students;
import models.StudentsWithCourses;
import servlets.db.SchoolAPI;

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
        req.setAttribute("name", "Student Courses");
        req.setAttribute("students", studentsWithCourses);

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/student_courses.jsp");
        dispatcher.forward(req, resp);

        System.out.println("GET Request");
        System.out.println(req.getParameter("name"));
    }
}
