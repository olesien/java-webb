package school.controllers;

import school.models.Courses;
import school.models.db.SchoolAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/courses")
public class CoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Courses> courses = SchoolAPI.getCourses();
        req.setAttribute("name", "Courses");
        req.setAttribute("courses", courses);

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/courses.jsp");
        dispatcher.forward(req, resp);

        System.out.println("GET Request");
        System.out.println(req.getParameter("name"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("received post for courses");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String yhp = req.getParameter("yhp");
        Courses addedCourse = SchoolAPI.addCourse(name, description, Integer.valueOf(yhp));
        if (addedCourse != null) {
            resp.sendRedirect("/courses?status=success");
        } else {
            resp.sendRedirect("/courses?status=fail");
        }
    }
}
