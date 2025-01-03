<%-- 
    Document   : tambahmahasiswa
    Created on : 2 Dec 2024, 01.47.16
    Author     : Kids1
--%>

<%@page import="classes.JDBC"%>
<!DOCTYPE html>
<html>
    <form method="post" action="MahasiswaServlets">
        Nama Mahasiswa: <input type="text" name="nama" /><br />
        Jurusan Mahasiswa: <input type="text" name="jurusan" /><br />
        Angkatan Mahasiswa: <input type="text" name="angkatan" /><br />
        IPK Mahasiswa: <input type="text" name="ipk" /><br /> 
        <input type="submit" value="Tambah" />
   </form>
</html>
