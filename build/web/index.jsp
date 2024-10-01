<%@page import="sample.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.dao.PlantDAO"%>
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
    <body class="container">
        <header class="d-flex justify-content-between align-items-center py-3">
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%
                String keyword = request.getParameter("txtsearch");
                String searchby = request.getParameter("searchby");
                ArrayList<Plant> list;
                String[] tmp = {"out of stock", "available"};

                if (keyword == null && searchby == null) {
                    list = PlantDAO.getPlants("", "");
                } else {
                    list = PlantDAO.getPlants(keyword, searchby);
                }

                if (list != null && !list.isEmpty()) {
                    int count = 0;
                    for (Plant p : list) {
                        if (count % 3 == 0) {
            %>
            <div class="row mb-4">
                <%
                            }
                %>
                <div class="col-md-4">
                    <div class="card">
                        <img src='<%= p.getImgpath() %>' class='card-img-top plantimg' alt='Plant Image'/>
                        <div class="card-body">
                            <h5 class="card-title"><%= p.getName() %></h5>
                            <p class="card-text">Product ID: <%= p.getId() %></p>
                            <p class="card-text">Price: <%= p.getPrice() %></p>
                            <p class="card-text">Status: <%= tmp[p.getStatus()] %></p>
                            <p class="card-text">Category: <%= p.getCatename() %></p>
                            <a href="mainController?action=addtocart&pid=<%= p.getId() %>" class="btn btn-primary">Add to cart</a>
                        </div>
                    </div>
                </div>
                <%
                            count++;
                            if (count % 3 == 0 || count == list.size()) {
                %>
            </div>
            <%
                        }
                    }
                } else {
            %>
            <p class="alert alert-warning">No products found.</p>
            <%
                }
            %>
        </section>
        <footer class="mt-5">
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
