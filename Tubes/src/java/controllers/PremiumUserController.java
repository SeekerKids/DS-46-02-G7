package controllers;

import models.PremiumUser;
import models.FreeUser;
import models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PremiumUserController", urlPatterns = {"/PremiumUserController"})
public class PremiumUserController extends HttpServlet {

    // Menangani request GET untuk menampilkan informasi premium user
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("username");

        // Menggunakan find untuk mencari user berdasarkan username
        User user = new User().find(username); 

        if (user != null) {
            if (action != null && action.equals("cancel") && user instanceof PremiumUser) {
                // Proses pembatalan untuk premium user
                PremiumUser premiumUser = (PremiumUser) user;
                premiumUser.cancel(); // Membatalkan langganan dan mengubah menjadi FreeUser
                premiumUser.update(); // Menyimpan perubahan status ke database
                response.sendRedirect("userProfile.jsp?username=" + username + "&status=cancelled");
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

        // Mencari user berdasarkan username
        User user = new User().find(username); 

        if (user != null) {
            // Jika user adalah FreeUser, lakukan upgrade ke Premium
            if (user instanceof FreeUser) {
                FreeUser freeUser = (FreeUser) user;
                freeUser.upgrade(); // Upgrade ke premium
                freeUser.update(); // Menyimpan perubahan ke database
                response.sendRedirect("userProfile.jsp?username=" + username + "&status=upgraded");
            } else {
                // User sudah premium
                response.sendRedirect("userProfile.jsp?username=" + username + "&status=alreadyPremium");
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
