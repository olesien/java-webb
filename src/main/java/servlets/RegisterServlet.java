package servlets;

import enums.PrivType;
import enums.UserType;
import models.UserBean;
import servlets.db.SchoolAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        if (user == null) {
            //We have a user
            resp.sendRedirect("/login");
            return;
        }
        if (user.getUserType() != UserType.teacher || user.getPrivType() != PrivType.admin) {
            //User lacks access.
            req.setAttribute("message", "Permission Denied");
            req.setAttribute("code", 403);
            RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/error.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean user = (UserBean) req.getSession().getAttribute("user");
        if (user == null) {
            //We have a user
            resp.sendRedirect("/login");
            return;
        }
        if (user.getUserType() != UserType.teacher || user.getPrivType() != PrivType.admin) {
            //User lacks access.
            req.setAttribute("message", "Permission Denied");
            req.setAttribute("code", 403);
            RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/error.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if (req.getParameter("type") == "student")
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
                resp.sendRedirect("/students?status=success");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
}
