package controllers;

import models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validasi bahwa username dan password tidak kosong
        if (username != null && password != null && !username.trim().isEmpty() && !password.trim().isEmpty()) {
            // Jika username dan password adalah admin
            if ("admin".equals(username) && "admin".equals(password)) {
                // Redirect ke halaman admin.jsp
                response.sendRedirect("admin.jsp");
                return;
            }

            // Validasi login untuk user non-admin
            User user = new User().findByUsername(username);
            if (user != null) {
                // Validasi password (disarankan untuk membandingkan hash password)
                if (user.getPassword().equals(password)) {  // Gantilah ini dengan pemeriksaan hash jika perlu
                    // Membuat session baru untuk pengguna yang berhasil login
                    HttpSession session = request.getSession(true);
                    session.setAttribute("username", user.getUsername());  // Menyimpan username di session
                    session.setAttribute("status", user.isPremium());      // Menyimpan status premium di session

                    if (user.isPremium() == true) {
                        response.sendRedirect("homepage.jsp");
                    }else {
                       response.sendRedirect("Ads.jsp");
                    }
                } else {
                    // Password salah, kirim pesan error
                    request.setAttribute("errorMessage", "Password salah! Silakan coba lagi.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                // Username tidak ditemukan, kirim pesan error
                request.setAttribute("errorMessage", "Username tidak ditemukan!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            // Username atau password kosong, kirim pesan error
            request.setAttribute("errorMessage", "Username dan password harus diisi!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet untuk menangani login user";
    }
}
