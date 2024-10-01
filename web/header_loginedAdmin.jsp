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
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="mainController?action=manageAccounts">Manage Accounts</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="mainController?action=manageOrders">Manage Orders</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="mainController?action=managePlants">Manage Plants</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="mainController?action=manageCategories">Manage Categories</a>
                            </li>
                        </ul>
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <span class="navbar-text">Welcome ${sessionScope.name}</span>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="mainController?action=logout">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    </body>
</html>
