package sample.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "addToCartServlet", urlPatterns = {"/addToCartServlet"})
public class addToCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String pid = request.getParameter("pid");
        HttpSession session = request.getSession(true);

        if (session != null && pid != null && !pid.trim().isEmpty()) {
            @SuppressWarnings("unchecked")
            HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<>();
            }
            cart.merge(pid, 1, Integer::sum);
            session.setAttribute("cart", cart);
        }
        response.sendRedirect("index.jsp");
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
        return "Servlet that adds items to the cart";
    }
}
