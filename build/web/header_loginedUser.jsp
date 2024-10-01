<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="index.jsp">Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="mainController?action=changeprofile">Change profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="mainController?action=completedorders">Completed orders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="mainController?action=canceledorders">Canceled orders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="mainController?action=preocessingorders">Processing orders</a>
                    </li>
                </ul>
                <form action="mainController?action=searchOrderByDate" method="post" class="form-inline">
                    <label class="mr-2" for="txtfrom">From</label>
                    <input type="text" class="form-control mr-2" id="txtfrom" name="txtfrom">
                    <label class="mr-2" for="txtto">To</label>
                    <input type="text" class="form-control mr-2" id="txtto" name="txtto">
                    <button type="submit" class="btn btn-outline-success">Search</button>
                </form>
            </div>
        </nav>
    </body>
</html>
