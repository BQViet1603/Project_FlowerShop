<%@page import="sample.dto.OrderDetail"%>
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
        <div class="container mt-4">
            <%
                String name = (String) session.getAttribute("name");
                if (name == null) {
            %>
            <div class="alert alert-danger" role="alert">
                You must login to view the personal page.
            </div>
            <%
                } else {
            %>
            <header>
                <%@include file="header_loginedUser.jsp" %>
            </header>
            <section>
                <h3>Welcome, <%= name %>!</h3>
                <h3><a href="mainController?action=logout" class="btn btn-danger">Logout</a></h3>
            </section>
            <section>
                <%
                    String orderid = request.getParameter("orderid");
                    if (orderid != null) {
                        int orderID = Integer.parseInt(orderid.trim());
                        ArrayList<OrderDetail> list = OrderDAO.getOrderDetail(orderID);
                        if (list != null && !list.isEmpty()) {
                            int money = 0;
                %>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Plant ID</th>
                                <th>Plant Name</th>
                                <th>Image</th>
                                <th>Price</th>
                                <th>Quantity</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (OrderDetail detail : list) {
                                    money += detail.getPrice() * detail.getQuantity();
                            %>
                            <tr>
                                <td><%= detail.getOrderID() %></td>
                                <td><%= detail.getPlantID() %></td>
                                <td><%= detail.getPlantName() %></td>
                                <td><img src='<%= detail.getImgPath() %>' class="img-thumbnail" alt="Plant Image" /></td>
                                <td>$<%= detail.getPrice() %></td>
                                <td><%= detail.getQuantity() %></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <h3>Total money: $<%= money %></h3>
                <%
                        } else {
                %>
                <p class="alert alert-warning">You don't have any orders.</p>
                <%
                        }
                    }
                %>
            </section>
            <footer>
                <%@include file="footer.jsp" %>          
            </footer>
            <% } %>
        </div>
    </body>
</html>
