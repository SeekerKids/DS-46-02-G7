<%@page import="classes.JDBC, java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>Nama</td>
            <td>Jurusan</td>
            <td>Angkatan</td>
            <td>IPK</td>
        </tr>
        <%
            try {
                JDBC db = new JDBC();
                
                if (db.isConnected) {
                    ResultSet rs = db.getData("SELECT * FROM mahasiswa");
                    if (rs != null) {
                        while (rs.next()) {
                            out.print("<tr>");
                            out.print("<td>" + rs.getInt("id") + "</td>");
                            out.print("<td>" + rs.getString("nama") + "</td>");
                            out.print("<td>" + rs.getString("jurusan") + "</td>");
                            out.print("<td>" + rs.getString("angkatan") + "</td>");
                            out.print("<td>" + rs.getDouble("ipk") + "</td>");
                            out.print("</tr>");
                        }
                    } else {
                        out.print("No data retrieved.<br />");
                    }
                    db.disconnect();
                } else {
                    out.print(db.message + "<br />");
                }
            } catch (Exception e) {
                out.print("Error: " + e.getMessage() + "<br />");
            }
        %>
    </table>
</html>
