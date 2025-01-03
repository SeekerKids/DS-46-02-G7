<%-- 
    Document   : view
    Created on : 8 Dec 2024, 22.45.42
    Author     : view
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, classes.JDBC" %>
<%@ page import="javax.servlet.http.HttpSession" %> <%-- comment --%>
<%
    // cek sesi untuk autentikasi
    if (session == null || session.getAttribute("authenticated") == null || !(Boolean) session.getAttribute("authenticated")) {
        response.sendRedirect("../index.jsp"); // balik (edirect) jika belum login
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Daftar Barang</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: verdana;
            background-color: lightblue;
            margin: 0;
            padding: 0;
            border: 1px;
        }
    </style>    
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Daftar Barang</h1>
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nama Barang</th>
                    <th>Kategori</th>
                    <th>Harga</th>
                    <th>Stok</th>
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody>
                <%
                    JDBC jdbc = new JDBC();
                    ResultSet rs = jdbc.getData("SELECT * FROM barang");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("id") %></td>
                    <td><%= rs.getString("nama_barang") %></td>
                    <td><%= rs.getString("kategori") %></td>
                    <td>Rp <%= rs.getDouble("harga") %></td>
                    <td><%= rs.getInt("stok") %></td>
                    <td>
                        <a href="edit.jsp?id=<%= rs.getInt("id") %>" class="btn btn-warning btn-sm">Edit</a>
                        <a href="delete.jsp?id=<%= rs.getInt("id") %>" class="btn btn-danger btn-sm">Delete</a> <!-- ini action delete di view.jsp -->
                    
                    </td>
                </tr>
                <%
                    }
                    jdbc.disconnect();
                %>
            </tbody>
        </table>
        <button  class="btn btn-success btn-block" onclick="location.href='add.jsp'">Tambah Barang(add)</button>                     
        <a href="../logout.jsp" class="btn btn-danger">Logout</a>
    </div>
</body>
</html>
