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
        System.out.println(req.getParameter("name"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("received post for students");
        String name = req.getParameter("name");
        String town = req.getParameter("town");
        String hobby = req.getParameter("hobby");
        Students addedStudent = SchoolAPI.addStudent(name, town, hobby);
        if (addedStudent != null) {
            resp.sendRedirect("/students?status=success");
        } else {
            resp.sendRedirect("/students?status=fail");
        }
    }
}
