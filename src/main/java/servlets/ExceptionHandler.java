package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ExceptionHandler")
public class ExceptionHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request,
                              HttpServletResponse response) throws IOException, ServletException {
        // Analyze the servlet exception
        Throwable throwable = (Throwable) request
                .getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request
                .getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request
                .getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        // Set response content type
        //response.setContentType("text/html");

        //PrintWriter out = response.getWriter();
        //out.write("<html><head><title>Exception/Error Details</title></head><body>");
        if(statusCode != 500){
            request.setAttribute("name", "Error - " + statusCode);
            request.setAttribute("code", statusCode);
            String msg = "Error";

            switch (statusCode) {
                case 400: {
                    msg = "Bad Request";
                    break;
                }
                case 403: {
                    msg = "Permission Denied";
                    break;
                }
                case 404: {
                    msg = "Not Found";
                    break;
                }

            }
            request.setAttribute("message", msg);

            // Forward the request to the JSP file
            RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/error.jsp");
            dispatcher.forward(request, response);
            //out.write("<h3>Error Details</h3>");
            //out.write("<strong>Status Code TEST</strong>:"+statusCode+"<br>");
            //out.write("<strong>Requested URI</strong>:"+requestUri);
        }else{
            request.setAttribute("name", "Error - " + statusCode);
            request.setAttribute("code", statusCode);
            request.setAttribute("message", throwable.getMessage());

            // Forward the request to the JSP file
            RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/error.jsp");
            dispatcher.forward(request, response);
            //out.write("<h3>Exception Details TEST</h3>");
            //out.write("<ul><li>Servlet Name:"+servletName+"</li>");
            //out.write("<li>Exception Name:"+throwable.getClass().getName()+"</li>");
            //out.write("<li>Requested URI:"+requestUri+"</li>");
            //out.write("<li>Exception Message:"+throwable.getMessage()+"</li>");
            //out.write("</ul>");
        }

        //out.write("<br><br>");
        //out.write("<a href=\"index.html\">Home Page</a>");
        //out.write("</body></html>");
    }
}
