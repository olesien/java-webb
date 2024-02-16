package servlets;

import models.Courses;
import models.UserBean;
import servlets.db.SchoolAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");


        if (user != null) {

            //We have a user
            resp.sendRedirect("/home");
            return;
        }

        //ArrayList<Courses> courses = SchoolAPI.getCourses();
        //req.setAttribute("name", "Courses");
        //req.setAttribute("courses", courses);

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = new UserBean();
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/login");
        //String name = req.getParameter("name");
        //String description = req.getParameter("description");
        //String yhp = req.getParameter("yhp");
        //try {
        //    SchoolAPI.addCourse(name, description, Integer.valueOf(yhp));
        //    resp.sendRedirect("/courses?status=success");
        //} catch (SQLException e) {
        //    throw new RuntimeException(e);
        //}

    }
}
