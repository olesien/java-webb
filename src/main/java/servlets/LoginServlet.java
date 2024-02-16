package servlets;

import models.Courses;
import servlets.db.SchoolAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    Cookie findCookie(Cookie[] cookies, String key) {
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().matches(key)) {
                return cookie;
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = findCookie(req.getCookies(), "testName");
        System.out.println(cookie);
        System.out.println(req.getCookies());

        if (cookie != null && cookie.getValue() != "") {

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
        resp.addCookie(new Cookie("testName", "testValue"));
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
