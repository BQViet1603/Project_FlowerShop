package sample.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "sendOTP", urlPatterns = {"/sendOTP"})
public class sendOTP extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sendOTP</title>");
            out.println("<link rel='stylesheet' href='mycss.css' type='text/css' />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sendOTP at " + request.getContextPath() + "</h1>");
            String email = (String) request.getAttribute("email_newAccount");
            out.println("<p> please, check your email:" + email + ", a confirm code is sent to you.</p>");
            RequestDispatcher rd = request.getRequestDispatcher("contactServlet");
            rd.include(request, response);
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
