<%-- 
    Document   : logout
    Created on : 23 Dec 2024, 02.24.14
    Author     : logout
--%>

<%@ page import="javax.servlet.http.*" %>
<%
    // hapus sesi
    if (session != null) {
        session.invalidate(); // hapus semua data sesi
    }
    // balik (redirect) ke index.jsp
    response.sendRedirect("index.jsp");
%>
