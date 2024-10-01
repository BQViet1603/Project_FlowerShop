<%@page import="sample.dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.dao.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />

        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <% 
            String name = (String) session.getAttribute("name");
            if (name == null) {
        %>
        <div class="container mt-4">
            <div class="alert alert-danger" role="alert">
                You must login to view personal page
            </div>
        </div>
        <%  
            } else {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section class="container mt-4">
            <h3>Welcome <%= name %> come back </h3>
            <a href="mainController?action=logout" class="btn btn-primary">Logout</a>
        </section>
        <section class="container mt-4">
            <% 
                String email = (String) session.getAttribute("email");
                ArrayList<Order> list = OrderDAO.getOrders(email);
                String[] status = {"", "Processing", "Completed", "Canceled"};
                int orderList = 0;
                if (list != null && !list.isEmpty()) {
                    for (Order ord : list) {
                        if (ord.getStatus() == 1) { 
            %>
            <div class="container mt-4">
                <table class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Order ID</th>
                            <th scope="col">Order Date</th>
                            <th scope="col">Ship Date</th>
                            <th scope="col">Order Status</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><%= ord.getOrderID() %></td>
                            <td><%= ord.getOrderDate() %></td>
                            <td><%= ord.getShipDate() %></td>
                            <td><%= status[ord.getStatus()] %></td>
                            <td>
                                <% if (ord.getStatus() == 1) { %>
                                <a href='orderstatusServlet?orderstatus=1&orderid=<%= ord.getOrderID() %>' class="btn btn-danger">Cancel Order</a>
                                <% } %>
                                <a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>" class="btn btn-info">Detail</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <%  
                            orderList++;
                        }
                    }
                }
                if (orderList == 0) {
            %>    
            <div class="container mt-4">
                <div class="alert alert-info" role="alert">
                    You don't have any order
                </div>
            </div>
            <%     
                }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>          
        </footer>
        <% } %>
    </body>
</html>
