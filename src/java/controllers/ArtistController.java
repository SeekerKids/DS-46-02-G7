package controllers;

import models.Artist;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ArtistController", urlPatterns = {"/ArtistController"})
public class ArtistController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "add" -> {
                        // Tambah artist baru
                        String name = request.getParameter("name");
                        String bio = request.getParameter("bio");
                        String gambar = request.getParameter("gambar");

                        Artist newArtist = new Artist();
                        newArtist.setName(name);
                        newArtist.setBio(bio);
                        newArtist.setGambar(gambar);

                        if (!newArtist.create()) {
                            request.setAttribute("errorMessage", "Gagal menambahkan artist.");
                        }
                    }
                    case "update" -> {
                        // Perbarui artist berdasarkan ID
                        int id = Integer.parseInt(request.getParameter("id"));
                        String name = request.getParameter("name");
                        String bio = request.getParameter("bio");
                        String gambar = request.getParameter("gambar");

                        Artist artistToUpdate = new Artist();
                        artistToUpdate.setId(id);
                        artistToUpdate.setName(name);
                        artistToUpdate.setBio(bio);
                        artistToUpdate.setGambar(gambar);

                        if (!artistToUpdate.edit()) {
                            request.setAttribute("errorMessage", "Gagal memperbarui artist.");
                        }
                    }
                    case "delete" -> {
                        // Hapus artist berdasarkan ID
                        int id = Integer.parseInt(request.getParameter("id"));

                        Artist artistToDelete = new Artist();
                        artistToDelete.setId(id);

                        if (!artistToDelete.delete()) {
                            request.setAttribute("errorMessage", "Gagal menghapus artist.");
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Input tidak valid.");
        } finally {
            // Redirect ke admin.jsp setelah operasi selesai
            response.sendRedirect("admin.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "ArtistController servlet for managing artists";
    }
}