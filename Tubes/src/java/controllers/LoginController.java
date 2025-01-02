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

    // Menangani request login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            // Mencari user berdasarkan username
            User user = new User().find(username);
            if (user != null) {
                // Validasi password
                if (user.getPassword().equals(password)) { // Asumsi ada getPassword() di User
                    // Login berhasil, buat session untuk user
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    response.sendRedirect("userProfile.jsp?username=" + username);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid password");
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username and password are required");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet untuk menangani login user";
    }
}
