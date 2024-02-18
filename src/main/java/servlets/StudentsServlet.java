package servlets;

import enums.UserType;
import models.Students;
import models.UserBean;
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

@WebServlet(urlPatterns = "/students")
public class StudentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        if (user == null) {
            //We have a user
            resp.sendRedirect("/login");
            return;
        }
        if (user.getUserType() != UserType.teacher) {
            //User lacks access.
            req.setAttribute("message", "Permission Denied");
            req.setAttribute("code", 403);
            RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/error.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        ArrayList<Students> students = null;
        try {
            students = SchoolAPI.getStudents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("name", "Students");
        req.setAttribute("students", students);

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/students.jsp");
        dispatcher.forward(req, resp);

        System.out.println("GET Request");
    }
}

