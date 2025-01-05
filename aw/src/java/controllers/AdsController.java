package controllers;

import models.Ads;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdsController", urlPatterns = {"/AdsController"})
public class AdsController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "add" -> {
                        // Tambah iklan baru
                        String content = request.getParameter("content");
                        String durationParam = request.getParameter("duration");
                        double duration = Double.parseDouble(durationParam);

                        Ads newAd = new Ads();
                        newAd.setContent(content);
                        newAd.setDuration(duration);

                        if (!newAd.create()) {
                            request.setAttribute("errorMessage", "Gagal menambahkan iklan.");
                        }
                    }
                    case "update" -> {
                        // Perbarui iklan berdasarkan ID
                        int id = Integer.parseInt(request.getParameter("id"));
                        String content = request.getParameter("content");
                        double duration = Double.parseDouble(request.getParameter("duration"));

                        Ads adToUpdate = new Ads();
                        adToUpdate.setId(id);
                        adToUpdate.setContent(content);
                        adToUpdate.setDuration(duration);

                        if (!adToUpdate.edit()) {
                            request.setAttribute("errorMessage", "Gagal memperbarui iklan.");
                        }
                    }
                    case "delete" -> {
                        // Hapus iklan berdasarkan ID
                        int id = Integer.parseInt(request.getParameter("id"));

                        Ads adToDelete = new Ads();
                        adToDelete.setId(id);

                        if (!adToDelete.delete()) {
                            request.setAttribute("errorMessage", "Gagal menghapus iklan.");
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Input tidak valid.");
        } finally {
            // Redirect ke admin.jsp setelah operasi
            response.sendRedirect("admin.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "AdsController servlet for managing ads";
    }
}