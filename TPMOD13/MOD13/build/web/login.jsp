<%-- 
    Document   : login
    Created on : 23 Dec 2024, 02.19.30
    Author     : login
--%>

<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.io.*" %>

<%
    // ngambil parameter dari form login coyy
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // dummy validasi kredensial (bisa diganti dengan query database)
    String validUsername = "admin";
    String validPassword = "1234";

    if (username != null && password != null) {
        if (username.equals(validUsername) && password.equals(validPassword)) {
            // login berhasil: buat sesi cuy
            session.setAttribute("authenticated", true);
            session.setAttribute("username", username);

            // balik (redirect) ke view.jsp
            response.sendRedirect("product/view.jsp");
        } else {
            // gagal login (bakal stuck di index)
            out.println("<script>alert('Username atau Password salah!');</script>");
            response.sendRedirect("index.jsp");
        }
    }
%>
