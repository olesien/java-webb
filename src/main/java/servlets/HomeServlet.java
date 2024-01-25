package servlets;

import models.Students;
import servlets.db.SchoolAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Students> students = SchoolAPI.getStudents();

        students.forEach(st -> System.out.println(st.getName()));
        String name = "test";
        req.setAttribute("name", name);

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = req.getRequestDispatcher("./jsp/home.jsp");
        dispatcher.forward(req, resp);

        System.out.println("GET Request");
        System.out.println(req.getParameter("name"));
    }
}
