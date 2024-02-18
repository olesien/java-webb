package servlets;

import enums.PrivType;
import enums.UserType;
import models.Students;
import models.Teachers;
import models.UserBean;
import models.db.Auth;
import models.db.SchoolAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

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
        String type = req.getParameter("type");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (Objects.equals(req.getParameter("type"), "student")) {
            System.out.println("Student login attempt");
            Students student = null;
            try {
                student = SchoolAPI.getStudentByUsername(username);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //Check if student matches password

            if (student == null) {
                System.out.println("No username found");
                return;
            }
            if (Auth.matches(password, student.getPassword())) {
                System.out.println("Login success");
                UserBean user = new UserBean();
                user.setId(student.getId());
                user.setUsername(student.getUsername());
                user.setPrivType(PrivType.user);
                user.setUserType(UserType.student);
                user.setId(student.getId());
                user.setFname(student.getFname());
                user.setLname(student.getLname());
                user.setEmail(student.getEmail());
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/login");
            } else {
                System.out.println("Login failed");
            }

        } else if (Objects.equals(req.getParameter("type"), "teacher")) {
            System.out.println("Teacher login attempt");

            Teachers teacher = null;
            try {
                teacher = SchoolAPI.getTeacherByUsername(username);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //Check if teacher matches password

            if (teacher == null) {
                System.out.println("No username found");
                return;
            }
            if (Auth.matches(password, teacher.getPassword())) {
                System.out.println("Login success");
                UserBean user = new UserBean();
                user.setId(teacher.getId());
                user.setUsername(teacher.getUsername());
                user.setPrivType(teacher.getPrivType());
                user.setUserType(UserType.teacher);
                user.setId(teacher.getId());
                user.setFname(teacher.getFname());
                user.setLname(teacher.getLname());
                user.setEmail(teacher.getEmail());
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/login");
            } else {
                System.out.println("Login failed");
            }
        } else {
            System.out.println("Unknown Attempt");
        }

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
