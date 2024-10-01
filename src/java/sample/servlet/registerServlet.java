package sample.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import sample.dao.AccountDAO;

@WebServlet(name = "registerServlet", urlPatterns = {"/registerServlet"})
public class registerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("txtemail");
            String fullname = request.getParameter("txtfullname");
            String password = request.getParameter("txtpassword");
            String phone = request.getParameter("txtphone");
            if (phone.matches("[a-zA-Z]")) {
                request.setAttribute("txtemail", email);
                request.setAttribute("txtfullname", fullname);
                request.setAttribute("txtphone", phone);
                request.setAttribute("ERROR", "the phone is invalid");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            } else {
                int status = 1;
                int role = 0;
                if (AccountDAO.insertAccount(email, password, fullname, phone, status, role) != 0) {
                    request.setAttribute("email_newAccount", email);
                    RequestDispatcher rd = request.getRequestDispatcher("sendOTP");
                    rd.forward(request, response);
                } else {
                    response.sendRedirect("errorpage.html");
                }
            }
        } catch (Exception e) {
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
