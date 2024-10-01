package sample.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "updateCartServlet", urlPatterns = {"/updateCartServlet"})
public class updateCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String pid = request.getParameter("pid");
            String newquantity = request.getParameter("quantity");
            HttpSession session = request.getSession(true);
            if (session != null) {
                HashMap<String, Integer> cart = (HashMap) session.getAttribute("cart");
                if (cart != null) {
                    boolean found = cart.containsKey(pid);
                    if (found) {
                        System.out.println(newquantity);
                        cart.put(pid, Integer.valueOf(newquantity));
                        session.setAttribute("cart", cart);
                        response.sendRedirect("viewCart.jsp");
                    }
                }
            }
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
