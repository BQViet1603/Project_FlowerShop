<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <jsp:useBean id="plantObj" class="sample.dto.Plant" scope="request"/>
        
        <!-- Table with headers and alt attributes added to images -->
        <table>
            <thead>
                <tr>
                    <th rowspan="8">Image</th>
                    <th>ID</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Category ID</th>
                    <th>Category Name</th>
                </tr>
            </thead>
            <tbody>
                <tr><td rowspan="8"><img src="${plantObj.imgpath}" alt="Image of ${plantObj.name}"></td></tr>
                <tr><td>${plantObj.id}</td></tr>
                <tr><td>${plantObj.name}</td></tr>
                <tr><td>${plantObj.price}</td></tr>
                <tr><td>${plantObj.description}</td></tr>
                <tr><td>${plantObj.status}</td></tr>
                <tr><td>${plantObj.cateid}</td></tr>
                <tr><td>${plantObj.catename}</td></tr>
            </tbody>
        </table>

        <!-- Another table with headers and alt attributes added to images -->
        <table>
            <thead>
                <tr>
                    <th rowspan="8">Image</th>
                    <th>ID</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Category ID</th>
                    <th>Category Name</th>
                </tr>
            </thead>
            <tbody>
                <tr><td rowspan="8"><img src="<jsp:getProperty id='plantObj' property='imgpath'></jsp:getProperty>" alt="Image of <jsp:getProperty id='plantObj' property='name'></jsp:getProperty>"></td></tr>
                <tr><td><jsp:getProperty id="plantObj" property="id"></jsp:getProperty></td></tr>
                <tr><td><jsp:getProperty id="plantObj" property="name"></jsp:getProperty></td></tr>
                <tr><td><jsp:getProperty id="plantObj" property="price"></jsp:getProperty></td></tr>
                <tr><td><jsp:getProperty id="plantObj" property="description"></jsp:getProperty></td></tr>
                <tr><td><jsp:getProperty id="plantObj" property="status"></jsp:getProperty></td></tr>
                <tr><td><jsp:getProperty id="plantObj" property="cateid"></jsp:getProperty></td></tr>
                <tr><td><jsp:getProperty id="plantObj" property="catename"></jsp:getProperty></td></tr>
            </tbody>
        </table>
    </body>
</html>
