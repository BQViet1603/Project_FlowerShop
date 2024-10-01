package sample.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import sample.dao.OrderDAO;

@WebServlet(name = "orderstatusServlet", urlPatterns = {"/orderstatusServlet"})
public class orderstatusServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String orderID = request.getParameter("orderid");
            String orderstatus = request.getParameter("orderstatus");
            if (orderstatus.equals("1")) {
                if (OrderDAO.updateOrderStatus(orderID, 3) == true) {
                    response.sendRedirect("processingOrders.jsp");
                } else {
                    response.sendRedirect("errorpage.html");
                }
            }
            if (orderstatus.equals("3")) {
                if (OrderDAO.updateOrderStatus(orderID, 1) == true) {
                    response.sendRedirect("canceledOrders.jsp");
                } else {
                    response.sendRedirect("errorpage.html");
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
