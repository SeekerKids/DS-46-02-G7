package controllers;

import models.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SignUpController", urlPatterns = {"/SignUpController"})
public class SignUpController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String email = request.getParameter("email").trim();
        String isPremium = request.getParameter("premium");

        // Validasi input: Memastikan semua field tidak kosong
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            request.setAttribute("errorMessage", "Semua field harus diisi.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Validasi format email sederhana (dapat diperbaiki dengan regex lebih kompleks)
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            request.setAttribute("errorMessage", "Format email tidak valid.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Cek apakah password cukup kuat (contoh: minimal 6 karakter)
        if (password.length() < 6) {
            request.setAttribute("errorMessage", "Password harus memiliki minimal 6 karakter.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Cek jika username sudah digunakan
        User existingUser = new User().findByUsername(username);
        if (existingUser != null) {
            request.setAttribute("errorMessage", "Username sudah ada. Silakan pilih username lain.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Jika semua validasi berhasil, simpan user baru
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);  // Pastikan password di-hash sebelum disimpan
        newUser.setEmail(email);
        newUser.setPremium("yes".equals(isPremium)); // Menangani premium status
        newUser.create();  // Simpan ke database

        // Redirect ke halaman login setelah pendaftaran berhasil
        HttpSession session = request.getSession(true);
        session.setAttribute("username", newUser.getUsername());  // Menyimpan username di session
        session.setAttribute("status", newUser.isPremium());      // Menyimpan status premium di session

        // Redirect ke halaman utama setelah login sukses
        if (newUser.isPremium() == true) {
            response.sendRedirect("homepage.jsp");
        }else {
           response.sendRedirect("Ads.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet untuk menangani signup user";
    }
}
