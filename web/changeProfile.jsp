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
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%
                String email = (String) session.getAttribute("email"); 
            %>
            <form action="updateprofileServlet?txtemail=<%= email%>" method="post">
                <h1>Update your profile</h1>
                <table>
                    <tr><td>Your account: </td><td><p name="txtemail"><% out.println(email); %></p></td></tr>
                    <tr><td>New full name</td><td><input type="text" name="txtfullname" required=""></td></tr>
                    <tr><td>New phone</td><td><input type="text" name="txtphone"></td></tr>
                    <tr><td>New password</td><td><input type="text" name="txtpassword" required=""></td></tr>
                    <tr><td colspan="2"><input type="submit" value="update" name="action"></td></tr>
                </table>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</body>
</html>
