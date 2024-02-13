package servlets;

import models.Students;
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

@WebServlet(urlPatterns = "/students")
public class StudentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Students> students = SchoolAPI.getStudents();
        req.setAttribute("name", "Students");
        req.setAttribute("students", students);

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/students.jsp");
        dispatcher.forward(req, resp);

        System.out.println("GET Request");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("received post for students");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String town = req.getParameter("town");
        String hobby = req.getParameter("hobby");
        try {
            SchoolAPI.addStudent(fname + " " + lname, town, hobby);
            resp.sendRedirect("/students?status=success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

