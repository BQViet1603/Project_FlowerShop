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
import sample.dao.OrderDAO;

@WebServlet(name = "saveShoppingCartServlet", urlPatterns = {"/saveShoppingCartServlet"})
public class saveShoppingCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String name = (String) session.getAttribute("name");
                String email = (String) session.getAttribute("email");
                HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
                if (cart != null && !cart.isEmpty()) {
                    if (name == null || name.equals("")) {
                        request.setAttribute("WARNING", "You must login to finish the shopping");
                        request.getRequestDispatcher("viewCart.jsp").forward(request, response);
                    } else {
                        boolean result = OrderDAO.insertOrder(email, cart);
                        if (result) {
                            session.setAttribute("cart", null);
                            request.setAttribute("WARNING", "Save your cart successfully");
                            request.getRequestDispatcher("viewCart.jsp").forward(request, response);
                        } else {
                            request.setAttribute("WARNING", "These products are out of stock");
                            request.getRequestDispatcher("viewCart.jsp").forward(request, response);
                        }
                    }
                } else {
                    request.setAttribute("WARNING", "Your cart is empty");
                    request.getRequestDispatcher("viewCart.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception ex) {
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
