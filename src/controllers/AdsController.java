package controllers;

import models.Ads;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "AdsController", urlPatterns = {"/AdsController"})
public class AdsController extends HttpServlet {

    // Menangani request GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Jika action adalah view iklan, tampilkan berdasarkan ID
        if ("view".equals(action)) {
            int adId = Integer.parseInt(request.getParameter("id"));
            Ads ad = new Ads();
            ad = ad.find(String.valueOf(adId));  // Mencari iklan berdasarkan ID
            if (ad != null) {
                request.setAttribute("ad", ad);
                request.getRequestDispatcher("/viewAd.jsp").forward(request, response);
            } else {
                response.getWriter().write("Iklan tidak ditemukan.");
            }
        } else {
            // Menampilkan semua iklan
            Ads ad = new Ads();
            ArrayList<Ads> adsList = ad.get();  // Mengambil semua iklan dari database
            request.setAttribute("adsList", adsList);
            request.getRequestDispatcher("/viewAds.jsp").forward(request, response);
        }
    }

    // Menangani request POST untuk tambah/update iklan
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Menambahkan iklan baru
            String content = request.getParameter("content");
            double duration = Double.parseDouble(request.getParameter("duration"));
            addAd(content, duration);
            response.sendRedirect("AdsController");
        } else if ("update".equals(action)) {
            // Mengupdate iklan berdasarkan ID
            int id = Integer.parseInt(request.getParameter("id"));
            String content = request.getParameter("content");
            double duration = Double.parseDouble(request.getParameter("duration"));
            updateAd(id, content, duration);
            response.sendRedirect("AdsController?action=view&id=" + id);
        }
    }

    // Menangani request DELETE untuk menghapus iklan
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int adId = Integer.parseInt(request.getParameter("id"));
        deleteAd(adId);
        response.getWriter().write("Iklan dengan ID " + adId + " telah dihapus.");
    }

    // Fungsi untuk menambahkan iklan
    public void addAd(String content, double duration) {
        Ads ad = new Ads();
        ad.setContent(content);
        ad.setDuration(duration);
        ad.insert();  // Menambahkan iklan ke database
    }

    // Fungsi untuk mengupdate iklan
    public void updateAd(int id, String content, double duration) {
        Ads ad = new Ads();
        ad.setId(id);
        ad.setContent(content);
        ad.setDuration(duration);
        ad.update();  // Mengupdate iklan di database
    }

    // Fungsi untuk menghapus iklan
    public void deleteAd(int id) {
        Ads ad = new Ads();
        ad.setId(id);
        ad.delete();  // Menghapus iklan dari database
    }

    @Override
    public String getServletInfo() {
        return "AdsController servlet for managing ads";
    }
}
