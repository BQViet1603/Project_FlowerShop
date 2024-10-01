<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
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
            <form action="mainController" method="post" class="form-inline mb-4">
                <div class="form-group mr-2">
                    <input type="text" name="txtSearch" class="form-control" placeholder="Search Order by Email">
                </div>
                <button type="submit" class="btn btn-primary" name="action" value="searchOrderByEmail">Search</button>
            </form>
            <h1></h1>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Ship Date</th>
                        <th>Status</th>
                        <th>Acc ID</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="acc" items="${requestScope.ordersList}">
                        <tr>
                            <td><c:out value="${acc.getOrderID()}"/></td>
                            <td><c:out value="${acc.getOrderDate()}"/></td>
                            <td><c:out value="${acc.getShipDate()}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${acc.getStatus() eq 1}">Processing</c:when>
                                    <c:when test="${acc.getStatus() eq 2}">Completed</c:when>
                                    <c:when test="${acc.getStatus() eq 3}">Canceled</c:when>
                                </c:choose>
                            </td>
                            <td><c:out value="${acc.getAccID()}"/></td>
                            <td><a href="orderDetail.jsp?orderid=${acc.getOrderID()}" class="btn btn-info btn-sm">Detail</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
