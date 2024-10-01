package sample.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "mainController", urlPatterns = {"/mainController"})
public class mainController extends HttpServlet {

    private String url = "errorpage.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");

            if (null == action) {
                url = "index.jsp";
            } else {
                switch (action) {
                    case "", "search" ->
                        url = "index.jsp";
                    case "login" ->
                        url = "loginServlet";
                    case "register" ->
                        url = "registerServlet";
                    case "logout" ->
                        url = "logoutServlet";
                    case "changeprofile" ->
                        url = "changeProfile.jsp";
                    case "completedorders" ->
                        url = "completedOrders.jsp";
                    case "canceledorders" ->
                        url = "canceledOrders.jsp";
                    case "preocessingorders" ->
                        url = "processingOrders.jsp";
                    case "addtocart" ->
                        url = "addToCartServlet";
                    case "viewcart" ->
                        url = "viewCart.jsp";
                    case "update" ->
                        url = "updateCartServlet";
                    case "delete" ->
                        url = "deleteCartServlet";
                    case "saveOrder" ->
                        url = "saveShoppingCartServlet";
                    case "viewdetailproduct" ->
                        url = "viewDetailProduct.jsp";
                    case "searchOrderByDate" ->
                        url = "searchOrder.jsp";
                    case "manageAccounts" ->
                        url = "manageAccountsServlet";
                    case "updateStatusAccount" ->
                        url = "updateStatusAccountServlet";
                    case "searchAccount" ->
                        url = "searchAccountServlet";
                    case "managePlants" ->
                        url = "managePlantsServlet";
                    case "searchPlant" ->
                        url = "ManagePlants.jsp";
                    case "manageOrders" ->
                        url = "manageOrdersServlet";
                    case "searchOrderByEmail" ->
                        url = "searchOrderServlet";
                    case "manageCategories" ->
                        url = "manageCategoriesServlet";
                    default -> {
                    }
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
