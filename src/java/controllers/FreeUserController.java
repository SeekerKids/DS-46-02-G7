package controllers;

import models.FreeUser;
import models.PremiumUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FreeUserController", urlPatterns = {"/FreeUserController"})
public class FreeUserController extends HttpServlet {

    // Menangani request GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Menampilkan informasi pengguna
        if (action == null || "info".equals(action)) {
            // Mendapatkan user yang sedang login (di sini hanya contoh)
            FreeUser user = getFreeUserFromSession(request);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/viewFreeUser.jsp").forward(request, response);
        }
    }

    // Menangani request POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("upgrade".equals(action)) {
            // Melakukan upgrade ke Premium
            FreeUser user = getFreeUserFromSession(request);
            if (user != null) {
                user.upgrade();
                request.setAttribute("message", "Account upgraded to Premium!");
                request.getRequestDispatcher("/viewFreeUser.jsp").forward(request, response);
            }
        } else if ("cancel".equals(action)) {
            // Membatalkan langganan (hanya untuk PremiumUser)
            FreeUser user = getFreeUserFromSession(request);
            if (user != null) {
                user.cancel();  // Pada FreeUser tidak ada langganan untuk dibatalkan
                request.setAttribute("message", "Free users cannot cancel a subscription.");
                request.getRequestDispatcher("/viewFreeUser.jsp").forward(request, response);
            }
        }
    }

    // Mendapatkan FreeUser dari sesi (misalnya login)
    private FreeUser getFreeUserFromSession(HttpServletRequest request) {
        // Di sini kita anggap bahwa FreeUser disimpan di session dengan key "user"
        return (FreeUser) request.getSession().getAttribute("user");
    }

    @Override
    public String getServletInfo() {
        return "FreeUserController servlet untuk mengelola pengguna free.";
    }
}
