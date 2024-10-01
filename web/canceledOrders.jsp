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
            <p><font color='red'>You must login to view the personal page</font></p>
        </div>
        <%  } else {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section class="container mt-4">
            <h3>Welcome <%= name %>!</h3>
            <a href="mainController?action=logout" class="btn btn-danger">Logout</a>
        </section>
        <section class="container mt-4"> <!-- Load all orders of the user here -->
            <%
                String email = (String) session.getAttribute("email");
                ArrayList<Order> list = OrderDAO.getOrders(email);
                String[] status = {"", "Processing", "Completed", "Canceled"};
                int orderList = 0;
                if (list != null && !list.isEmpty()) {
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
                        <%
                        for (Order ord : list) {
                            if (ord.getStatus() == 3) {
                        %>
                        <tr>
                            <td><%= ord.getOrderID() %></td>
                            <td><%= ord.getOrderDate() %></td>
                            <td><%= ord.getShipDate() %></td>
                            <td><%= status[ord.getStatus()] %></td>
                            <td>
                                <a href='orderstatusServlet?orderstatus=3&orderid=<%= ord.getOrderID() %>' class="btn btn-warning">Order Again</a>
                                <a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>" class="btn btn-info">Detail</a>
                            </td>
                        </tr>
                        <%
                                orderList++;
                            }
                        }
                        %>
                    </tbody>
                </table>
            </div>
            <%
        } else {
            %>
            <div class="container mt-4">
                <div class="alert alert-info" role="alert">
                    You don't have any orders.
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
