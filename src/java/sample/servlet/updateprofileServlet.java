package sample.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import sample.dao.AccountDAO;

@WebServlet(name = "updateprofileServlet", urlPatterns = {"/updateprofileServlet"})
public class updateprofileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("txtemail");
            String fullname = request.getParameter("txtfullname");
            String phone = request.getParameter("txtphone");
            String password = request.getParameter("txtpassword");

            if (AccountDAO.updateAccount(email, password, fullname, phone) == true) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet sendOTP</title>");
                out.println("<style> h1, p {text-align: center;}</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Update your profile succesfully!!!</h1>");
                out.println("<p>You need to login again to access your account! <a href='index.jsp'>Click here</a></p>");
                out.println("</body>");
                out.println("</html>");
            } else {
                response.sendRedirect("errorpage.html");
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
