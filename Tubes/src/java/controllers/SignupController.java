package controllers;

import models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignupController", urlPatterns = {"/SignupController"})
public class SignupController extends HttpServlet {

    // Menangani request signup
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (username != null && password != null && email != null) {
            // Cek apakah username sudah ada
            User existingUser = new User().find(username);
            if (existingUser == null) {
                // Membuat user baru
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setEmail(email);  // Asumsi ada setEmail() di User
                newUser.insert();  // Menyimpan user ke database

                // Redirect ke halaman login atau profil setelah pendaftaran berhasil
                response.sendRedirect("login.jsp?status=signupSuccess");
            } else {
                response.sendError(HttpServletResponse.SC_CONFLICT, "Username already exists");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet untuk menangani signup user";
    }
}
