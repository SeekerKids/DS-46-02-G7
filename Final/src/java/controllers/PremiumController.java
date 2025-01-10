package controllers;

import models.Premium;
import models.Free;
import models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PremiumUserController", urlPatterns = {"/PremiumUserController"})
public class PremiumController extends HttpServlet {

    // Menangani request GET untuk menampilkan informasi premium user
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("username");

        // Validasi jika username tidak kosong
        if (username == null || username.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username is missing");
            return;
        }

        // Mencari user berdasarkan username
        User user = new User().find(username);
        if (user != null) {
            // Proses pembatalan untuk premium user
            if ("cancel".equals(action) && user instanceof Premium) {
                Premium premiumUser = (Premium) user;
                String resultMessage = premiumUser.cancel(); // Pembatalan langganan
                premiumUser.edit(); // Update perubahan status ke database
                response.sendRedirect("userProfile.jsp?username=" + username + "&status=cancelled&message=" + resultMessage);
            } else {
                // Tampilkan halaman profil user
                request.setAttribute("user", user);
                request.getRequestDispatcher("/userProfile.jsp").forward(request, response);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }

    // Menangani request POST untuk upgrade user ke premium
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");

        // Validasi jika username tidak kosong
        if (username == null || username.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username is missing");
            return;
        }

        // Mencari user berdasarkan username
        User user = new User().find(username);
        if (user != null) {
            // Jika user adalah Free, lakukan upgrade ke Premium
            if (user instanceof Free freeUser) {
                String resultMessage = freeUser.upgrade(); // Upgrade ke premium
                freeUser.edit(); // Menyimpan perubahan ke database
                response.sendRedirect("userProfile.jsp?username=" + username + "&status=upgraded&message=" + resultMessage);
            } else {
                // User sudah premium
                response.sendRedirect("userProfile.jsp?username=" + username + "&status=alreadyPremium&message=User%20already%20has%20premium%20status");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet untuk menangani tindakan premium user: upgrade dan cancel";
    }
}