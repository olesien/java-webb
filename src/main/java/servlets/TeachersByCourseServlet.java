package servlets;

import enums.UserType;
import models.CourseBean;
import models.StudentBean;
import models.TeacherBean;
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

@WebServlet(urlPatterns = "/teachers_by_course")
public class TeachersByCourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Getting teachers by course");
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        if (user == null) {
            //We don't have a user
            resp.sendRedirect("/login");
            return;
        }
        System.out.println(req.getRequestURI());
        String course_id = req.getParameter("id");
        ArrayList<TeacherBean> teachers = null;
        try {
            teachers = SchoolAPI.getTeachersByCourseId(Integer.valueOf(course_id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user.getUserType() != UserType.teacher) {
            ArrayList<StudentBean> students = null;
            try {
                students = SchoolAPI.getStudentsByCourseId(Integer.valueOf(course_id));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //Check if user has this course
            if (students.stream().noneMatch(student -> student.getId() == user.getId())) {
                //User lacks access.
                req.setAttribute("message", "Permission Denied");
                req.setAttribute("code", 403);
                RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/error.jsp");
                dispatcher.forward(req, resp);
                return;
            }
        }

        CourseBean course = null;
        try {
            course = SchoolAPI.getCourse(Integer.valueOf(course_id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("name", "Teachers");
        req.setAttribute("teachers", teachers);
        req.setAttribute("course", course);

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/teachers.jsp");
        dispatcher.forward(req, resp);

        System.out.println("GET Request");
    }
}

