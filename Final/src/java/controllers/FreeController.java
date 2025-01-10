package controllers;

import java.io.IOException;
import models.Free;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FreeUserController", urlPatterns = {"/FreeUserController"})
public class FreeController extends HttpServlet {

    // Menangani request GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Menampilkan informasi pengguna
        if (action == null || "info".equals(action)) {
            // Mendapatkan user yang sedang login (di sini hanya contoh)
            Free user = getFreeUserFromSession(request);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/viewFreeUser.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");  // Redirect ke halaman login jika tidak ada user
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action tidak valid");
        }
    }

    // Menangani request POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        Free user = getFreeUserFromSession(request);

        if (user != null) {
            switch (action) {
                case "upgrade" -> {
                    // Melakukan upgrade ke Premium
                    String upgradeMessage = user.upgrade();  // Proses upgrade
                    request.setAttribute("message", upgradeMessage);
                    request.getRequestDispatcher("/viewFreeUser.jsp").forward(request, response);
                }

                case "cancel" -> {
                    // Membatalkan langganan (hanya untuk Premium, untuk Free akan mengembalikan pesan error)
                    String cancelMessage = user.cancel();
                    request.setAttribute("message", cancelMessage);
                    request.getRequestDispatcher("/viewFreeUser.jsp").forward(request, response);
                }

                default ->
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action tidak valid");
            }
        } else {
            response.sendRedirect("login.jsp");  // Redirect ke halaman login jika tidak ada user
        }
    }

    // Mendapatkan Free dari sesi (misalnya login)
    private Free getFreeUserFromSession(HttpServletRequest request) {
        // Di sini kita anggap bahwa Free disimpan di session dengan key "user"
        return (Free) request.getSession().getAttribute("user");
    }

    @Override
    public String getServletInfo() {
        return "FreeUserController servlet untuk mengelola pengguna free.";
    }
}
