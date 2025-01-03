<%-- 
    Document   : edit
    Created on : 8 Dec 2024, 22.45.19
    Author     : edir
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="classes.JDBC, java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Barang</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            font-family: verdana;
            background-color: lightblue;
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Edit Barang</h1>
        <%
            
            String id = request.getParameter("id");
            JDBC jdbc = new JDBC();
            if (jdbc.isConnected) {
                ResultSet rs = jdbc.getData("SELECT * FROM barang WHERE id = " + id);
                if (rs.next()) {
        %>
        <form method="post">
            <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
            <div class="form-group">
                <label for="nama_barang">Nama Barang</label>
                <input type="text" class="form-control" id="nama_barang" name="nama_barang" 
                       value="<%= rs.getString("nama_barang") %>" required>
            </div>
            <div class="form-group">
                <label for="kategori">Kategori</label>
                <input type="text" class="form-control" id="kategori" name="kategori" 
                       value="<%= rs.getString("kategori") %>" required>
            </div>
            <div class="form-group">
                <label for="harga">Harga</label>
                <input type="number" class="form-control" id="harga" name="harga" 
                       value="<%= rs.getDouble("harga") %>" step="0.01" required>
            </div>
            <div class="form-group">
                <label for="stok">Stok</label>
                <input type="number" class="form-control" id="stok" name="stok" 
                       value="<%= rs.getInt("stok") %>" required>
            </div>
            <button type="submit" class="btn btn-warning btn-block">Update Barang</button>
        </form>
        <a href="../index.jsp" class="btn btn-primary btn-block mt-3">Kembali</a>
        <%
                } else {
        %>
        <div class='alert alert-danger'>Data tidak ditemukan.</div>
        <%
                }
                jdbc.disconnect();
            }
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String nama_barang = request.getParameter("nama_barang");
                String kategori = request.getParameter("kategori");
                String harga = request.getParameter("harga");
                String stok = request.getParameter("stok");
                jdbc = new JDBC();
                if (jdbc.isConnected) {
                    jdbc.runQuery(
                        "UPDATE barang SET nama_barang='" + nama_barang + 
                        "', kategori='" + kategori + 
                        "', harga=" + harga + 
                        ", stok=" + stok + 
                        " WHERE id=" + request.getParameter("id")
                    );
                    response.sendRedirect("../index.jsp");
                    jdbc.disconnect();
                }
            }
        %>
    </div>
</body>
</html>