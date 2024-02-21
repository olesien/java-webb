package servlets.admin;

import enums.PrivType;
import enums.UserType;
import models.*;
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
import java.util.Objects;

@WebServlet(urlPatterns = "/stats")
public class StatsServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        if (user == null) {
            //We don't have a user
            resp.sendRedirect("/login");
            return;
        }
        if (user.getUserType() != UserType.teacher || user.getPrivType() != PrivType.superadmin) {
            //User lacks access.
            req.setAttribute("message", "Permission Denied");
            req.setAttribute("code", 403);
            RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/error.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        if (Objects.equals(req.getParameter("type"), "student_course_average")) {
            try {

                ArrayList<CourseBean> courses = SchoolAPI.getCourses();
                int total_courses = courses.size(); //Can also get this with a COUNT DB call, but I decided it was not worth it here.

                ArrayList<StudentWithCountBean> students = SchoolAPI.getStudentsWithCount(total_courses);

                // Forward the request to the JSP file
                req.setAttribute("students", students);
                req.setAttribute("total_courses", total_courses);
                RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/admin/stats/student_course_average.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else if (Objects.equals(req.getParameter("type"), "course_popularity")) {
            try {
                ArrayList<CourseWithPopularityBean> courses = SchoolAPI.getCoursesByPopularity();
                req.setAttribute("courses", courses);
                RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/admin/stats/course_popularity.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}