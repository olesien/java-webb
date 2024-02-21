package servlets.admin;

import enums.PrivType;
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
import java.util.Objects;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private void getStudentCourseRelation (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<CourseBean> courses = null;
        try {
            courses = SchoolAPI.getCourses();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<StudentBean> students = null;
        try {
            students = SchoolAPI.getStudents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("courses", courses);
        req.setAttribute("students", students);
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/admin/register/studentcourserelation.jsp");
        dispatcher.forward(req, resp);
    }

    private void getTeacherCourseRelation (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<CourseBean> courses = null;
        try {
            courses = SchoolAPI.getCourses();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<TeacherBean> teachers = null;
        try {
            teachers = SchoolAPI.getTeachers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("courses", courses);
        req.setAttribute("teachers", teachers);
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/admin/register/teachercourserelation.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        if (user == null) {
            //We don't have a user
            resp.sendRedirect("/login");
            return;
        }
        if (user.getUserType() != UserType.teacher || user.getPrivType() == PrivType.user) {
            //User lacks access.
            req.setAttribute("message", "Permission Denied");
            req.setAttribute("code", 403);
            RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/error.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        if (Objects.equals(req.getParameter("type"), "student")) {
            // Forward the request to the JSP file
            RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/admin/register/student.jsp");
            dispatcher.forward(req, resp);
        } else if (Objects.equals(req.getParameter("type"), "teacher")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/admin/register/teacher.jsp");
            dispatcher.forward(req, resp);
        } else if (Objects.equals(req.getParameter("type"), "course")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/admin/register/course.jsp");
            dispatcher.forward(req, resp);
        } else if (Objects.equals(req.getParameter("type"), "studentcourserelation")) {
            getStudentCourseRelation(req, resp);
        }else if (Objects.equals(req.getParameter("type"), "teachercourserelation")) {
            getTeacherCourseRelation(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        if (user == null) {
            //We don't have a user
            resp.sendRedirect("/login");
            return;
        }
        if (user.getUserType() != UserType.teacher || user.getPrivType() == PrivType.user) {
            //User lacks access.
            req.setAttribute("message", "Permission Denied");
            req.setAttribute("code", 403);
            RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/error.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if (Objects.equals(req.getParameter("type"), "student")) {
            System.out.println("received post for students");
            String fname = req.getParameter("fname");
            String lname = req.getParameter("lname");
            String town = req.getParameter("town");
            String hobby = req.getParameter("hobby");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            try {
                SchoolAPI.addStudent(fname,lname, town, hobby, email, phone, username, password);
                req.setAttribute("success_message", "Student successfully added!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/admin/register/student.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                req.setAttribute("error_message", e.getMessage());
                RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/admin/register/student.jsp");
                dispatcher.forward(req, resp);
            }

        }
        else if (Objects.equals(req.getParameter("type"), "teacher")) {
            System.out.println("received post for teachers");
            String fname = req.getParameter("teacher_fname");
            String lname = req.getParameter("teacher_lname");
            String town = req.getParameter("teacher_town");
            String hobby = req.getParameter("teacher_hobby");
            String email = req.getParameter("teacher_email");
            String phone = req.getParameter("teacher_phone");
            String username = req.getParameter("teacher_username");
            String password = req.getParameter("teacher_password");
            PrivType priv = PrivType.valueOf(req.getParameter("teacher_priv"));
            if (user.getPrivType() != PrivType.superadmin && priv == PrivType.superadmin) {
                //Reset to admin
                priv = PrivType.admin;
            }
            try {
                SchoolAPI.addTeacher(fname, lname, town, hobby, email, phone, username, password, priv);
                req.setAttribute("success_message", "Teacher successfully added!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/admin/register/teacher.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                req.setAttribute("error_message", e.getMessage());
                RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/admin/register/teacher.jsp");
                dispatcher.forward(req, resp);
            }
        }
        else if (Objects.equals(req.getParameter("type"), "course")) {
            System.out.println("received post for course");
            String name = req.getParameter("course_name");
            String description = req.getParameter("course_description");
            String yhp = req.getParameter("course_yhp");
            try {
                SchoolAPI.addCourse(name, description, Integer.valueOf(yhp));
                resp.sendRedirect("/students?status=success");
            } catch (SQLException e) {
                req.setAttribute("error_message", e.getMessage());
                RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/admin/register/course.jsp");
                dispatcher.forward(req, resp);
            }
        } else if (Objects.equals(req.getParameter("type"), "studentcourserelation")) {
            System.out.println("received post for course+student relation");
            Integer student_id = Integer.valueOf(req.getParameter("courserelation_student"));
            Integer course_id = Integer.valueOf(req.getParameter("courserelation_course"));
            try {
                SchoolAPI.addStudentCourseRelation(student_id, course_id);
                req.setAttribute("success_message", "Student Course Relation successfully added!");
                getStudentCourseRelation(req, resp);
            } catch (SQLException e) {
                req.setAttribute("error_message", e.getMessage());
                getStudentCourseRelation(req, resp);
            }
        }  else if (Objects.equals(req.getParameter("type"), "teachercourserelation")) {
            System.out.println("received post for course+teacher relation");
            Integer teacher_id = Integer.valueOf(req.getParameter("courserelation_teacher"));
            Integer course_id = Integer.valueOf(req.getParameter("courserelation_course"));
            try {
                SchoolAPI.addTeacherCourseRelation(teacher_id, course_id);
                req.setAttribute("success_message", "Teacher Course Relation successfully added!");
                getTeacherCourseRelation(req, resp);
            } catch (SQLException e) {
                req.setAttribute("error_message", e.getMessage());
                getTeacherCourseRelation(req, resp);
            }
        }
    }
}
