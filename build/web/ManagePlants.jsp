<%@page import="sample.dao.PlantDAO"%>
<%@page import="sample.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
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
        <c:import url="header_loginedAdmin.jsp"/>
        <div class="container mt-4">
            <form action="mainController" method="post" class="formsearch mb-4">
                <input type="text" name="txtsearch" class="form-control mb-2" value="<%= (request.getParameter("txtsearch")==null)?"": request.getParameter("txtsearch")%>" placeholder="Search">
                <select name="searchby" class="form-control mb-2">
                    <option value="byname">By Name</option>
                    <option value="bycate">By Category</option>
                </select>
                <input type="submit" value="searchPlant" name="action" class="btn btn-primary">
            </form>
            <div class="row">
                <%
                    String keyword = request.getParameter("txtsearch");
                    String searchby = request.getParameter("searchby");
                    ArrayList<Plant> list;
                    String [] tmp = {"out of stock","available"};
                    if (keyword==null && searchby==null)
                        list = PlantDAO.getPlants("", "");
                    else
                        list = PlantDAO.getPlants(keyword, searchby);
                    if (list != null && !list.isEmpty()) {
                        for(Plant p : list) { %>
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <img src='<%= p.getImgpath()%>' class='card-img-top' alt='<%= p.getName() %>'/>
                        <div class="card-body">
                            <h5 class="card-title">Product ID: <%= p.getId() %></h5>
                            <p class="card-text">Product name: <%= p.getName() %></p>
                            <p class="card-text">Price: <%= p.getPrice() %></p>
                            <p class="card-text">Status: <%= tmp[p.getStatus()] %></p>
                            <p class="card-text">Category: <%= p.getCatename() %></p>
                        </div>
                    </div>
                </div>
                <%      }
                    }
                %>
            </div>
        </div>
    </body>
</html>
