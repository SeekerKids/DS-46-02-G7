<%-- 
    Document   : delete
    Created on : 23 Dec 2024, 00.58.06
    Author     : delete
--%>


<%@ page import="java.sql.*, classes.JDBC" %>

<%
    // ambil parameter ID dari URL
    String id = request.getParameter("id");
    String message = "";

    if (id != null && !id.trim().isEmpty()) {
        JDBC jdbc = new JDBC();
        if (jdbc.isConnected) {
            try {
                // query ngehapus data
                String query = "DELETE FROM barang WHERE id = " + id;
                jdbc.runQuery(query);
                
                if (jdbc.message.startsWith("Error")) {
                    message = "Gagal menghapus data dengan ID " + id + ": " + jdbc.message;
                } else {
                    message = "Data dengan ID " + id + " berhasil dihapus.";
                }
            } catch (Exception e) {
                message = "Terjadi kesalahan: " + e.getMessage();
            } finally {
                jdbc.disconnect();
            }
        } else {
            message = "Koneksi ke database gagal: " + jdbc.message;
        }
    } else {
        message = "ID tidak valid.";
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hapus Barang</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Hapus Barang</h1>
        <div class="alert alert-info text-center">
            <%= message %>
        </div>
        <a href="view.jsp" class="btn btn-primary btn-block mt-3">Kembali ke Daftar Barang</a>
    </div>
</body>
</html>


