package servlets;

import enums.UserType;
import models.CourseBean;
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

@WebServlet(urlPatterns = "/courses")
public class CoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        UserBean user = (UserBean) req.getSession().getAttribute("user");
//        if (user == null) {
//            //We have a user
//            resp.sendRedirect("/login");
//            return;
//        }
//        if (user.getUserType() != UserType.teacher) {
//            //User lacks access.
//            req.setAttribute("message", "Permission Denied");
//            req.setAttribute("code", 403);
//            RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/error.jsp");
//            dispatcher.forward(req, resp);
//            return;
//        }

        ArrayList<CourseBean> courses = null;
        try {
            courses = SchoolAPI.getCourses();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("name", "Courses");
        req.setAttribute("courses", courses);

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/courses.jsp");
        dispatcher.forward(req, resp);
    }
}
