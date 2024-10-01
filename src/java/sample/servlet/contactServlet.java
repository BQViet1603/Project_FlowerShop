package sample.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sample.dao.AccountDAO;
import sample.dto.Account;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "contactServlet", urlPatterns = {"/contactServlet"})
public class contactServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<footer>");
            Account adminAcc = AccountDAO.getAccount("admin@gmail.com", "admin");
            if (adminAcc != null) {
                out.println("<p align='center'>Contact us: " + adminAcc.getEmail() + "</p>");
            }
            out.println("</footer>");
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
        return "Servlet that provides contact information";
    }
}
