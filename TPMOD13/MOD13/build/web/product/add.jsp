<%-- 
    Document   : add
    Created on : 8 Dec 2024, 22.45.26
    Author     : add
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="classes.JDBC" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tambah Barang</title>
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
        <h1 class="text-center mb-4">Tambah Barang</h1>
        <form method="post">
            <div class="form-group">
                <label for="nama_barang">Nama Barang</label>
                <input type="text" class="form-control" id="nama_barang" name="nama_barang" placeholder="Masukkan nama barang" required>
            </div>
            <div class="form-group">
                <label for="harga">Kategori</label>
                <input type="text" class="form-control" id="kategori" name="kategori" placeholder="Masukkan kategori barang" step="0.01" required>
            </div>            
            <div class="form-group">
                <label for="harga">Harga</label>
                <input type="number" class="form-control" id="harga" name="harga" placeholder="Masukkan harga barang" step="0.01" required>
            </div>
            <div class="form-group">
                <label for="harga">Stok</label>
                <input type="number" class="form-control" id="stok" name="stok" placeholder="Masukkan jumlah stok barang" step="0.01" required>
            </div>
            
            <button type="submit" class="btn btn-success btn-block">Tambah Barang</button>
        </form>
        <a href="../index.jsp" class="btn btn-primary btn-block mt-3">Kembali ke Halaman Utama</a>

        <% 
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                JDBC jdbc = new JDBC();
                String nama = request.getParameter("nama_barang");
                String kategori = request.getParameter("kategori");
                String harga = request.getParameter("harga");
                String stok = request.getParameter("stok");
                jdbc.runQuery("INSERT INTO barang (nama_barang,kategori, harga,stok) VALUES ('" + nama + "', '" + kategori + "', " + harga + ", " + stok + ")");
        %>
            <div class="alert alert-success text-center mt-3"><%= jdbc.message %></div>
        <% 
                jdbc.disconnect();
            } 
        %>
    </div>
</body>
</html>
