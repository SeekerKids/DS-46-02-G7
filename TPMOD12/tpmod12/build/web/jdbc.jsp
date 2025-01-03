<%-- 
    Document   : jdbc
    Created on : 2 Dec 2024, 01.11.58
    Author     : Kids1
--%>
<%@page import="classes.JDBC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<% 
    JDBC db = new JDBC(); 
    if (db.isConnected) { 
       out.print(db.message + "<br />"); 
    } else { 
       out.print(db.message + "<br />"); 
    } 
   db.runQuery("insert into mahasiswa (nama) values ('PC')"); 
   out.print(db.message + "<br />"); 
   db.disconnect(); 
   out.print(db.message + "<br />");
%>
</html>
