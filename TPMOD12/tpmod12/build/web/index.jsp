<%-- 
    Document   : index
    Created on : 1 Dec 2024, 23.25.35
    Author     : Kids1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <form method="get" action="mahasiswaServlet">  
        NIM:  <input type="text" name="nim" /> <br />  
        Nama: <input type="text" name="nama" /> <br />  
        <input type="submit" value="Kirim" />  
    </form>  
    <%  
        out.print(request.getAttribute("nim")+"<br />");  
        out.print(request.getAttribute("nama")+"<br />");  
    %>
</html>
