<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Database Connection Status</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Database Connection Status</h1>
        <div class="mt-4">
            <%
                String url = "jdbc:mysql://localhost:3306/tubes";
                String user = "root";
                String password = "";
                String message;
                boolean isConnected = false;

                try {
                    // Load the JDBC driver
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, user, password);
                    isConnected = true;
                    message = "Database Connected Successfully!";
                    con.close(); // Close the connection after testing
                } catch (ClassNotFoundException e) {
                    message = "Driver not found: " + e.getMessage();
                } catch (SQLException e) {
                    message = "Database connection failed: " + e.getMessage();
                }
            %>
            <%
                if (isConnected) {
            %>
                <div class="alert alert-success text-center" role="alert">
                    <%= message %>
                </div>
            <%
                } else {
            %>
                <div class="alert alert-danger text-center" role="alert">
                    <%= message %>
                </div>
            <%
                }
            %>
            <div class="text-center mt-4">
                <a href="index.jsp" class="btn btn-primary">Go to Home</a>
            </div>
        </div>
    </div>
</body>
</html>
