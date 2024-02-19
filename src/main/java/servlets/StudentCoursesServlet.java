package servlets;

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

@WebServlet(urlPatterns = "/student_courses")
public class StudentCoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        if (user == null || user.getId() == 0) {
            //We don't have a user
            resp.sendRedirect("/login");
            return;
        }
        String course_id = req.getParameter("id");
        //We will get the courses by the student id
        ArrayList<CourseBean> courses = null;
        try {
            courses = SchoolAPI.getCoursesByStudentId(Integer.valueOf(course_id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("name", "CourseBean");
        req.setAttribute("courses", courses);

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/courses.jsp");
        dispatcher.forward(req, resp);
    }
}
