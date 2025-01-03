<%-- 
    Document   : index
    Created on : 8 Dec 2024, 21.20.24
    Author     : index
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Web Manajemen Barang</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: var;
            background-color: lightblue;
            margin: 0;
            padding: 0;
        }
        .container {
            margin-top: 30px;
        }
        header {
            background-color: black;
            color: white;
            padding: 15px 0;
        }
        header h1 {
            margin: 0;
            font-size: 25px;
            text-align: center;
        }
    </style>
</head>
<body>
    <header>
        <h1>Web Manajemen Barang</h1>
    </header>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <form action="login.jsp" method="post">
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Login</button>
                    <button  class="btn btn-success btn-block" onclick="location.href='product/view.jsp'">Lihat Barang(view)</button>                    
                </form>
            </div>
        </div>
    </div>
</body>
</html>
