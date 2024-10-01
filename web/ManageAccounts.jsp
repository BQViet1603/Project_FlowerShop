<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />

        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.amazonaws.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>
        <div class="container mt-4">
            <form action="mainController" method="post" class="form-inline mb-4">
                <div class="form-group mr-2">
                    <input type="text" name="txtSearch" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-primary" name="action" value="searchAccount">Search</button>
            </form>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Email</th>
                        <th>Full name</th>
                        <th>Phone</th>
                        <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="acc" items="${requestScope.accountList}">
                        <tr>
                            <td><c:out value="${acc.getAccID()}"/></td>
                            <td><c:out value="${acc.getEmail()}"/></td>
                            <td><c:out value="${acc.getFullname()}"/></td>
                            <td><c:out value="${acc.getPhone()}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${acc.getRole() eq 1}">Admin</c:when>
                                    <c:otherwise>User</c:otherwise>
                                </c:choose>
                            </td>                            
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
