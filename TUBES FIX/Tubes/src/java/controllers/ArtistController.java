package controllers;

import models.Artist;
import models.Album;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ArtistController", urlPatterns = {"/ArtistController"})
public class ArtistController extends HttpServlet {

    // Menangani request GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Menampilkan semua artist
        if (action == null || "list".equals(action)) {
            ArrayList<Artist> artists = new Artist().get();  // Mengambil semua artist dari database
            request.setAttribute("artists", artists);
            request.getRequestDispatcher("/viewArtists.jsp").forward(request, response);

        } else if ("view".equals(action)) {
            // Menampilkan artist berdasarkan ID
            int artistId = Integer.parseInt(request.getParameter("id"));
            Artist artist = new Artist().find(String.valueOf(artistId));
            if (artist != null) {
                // Menampilkan album dari artist
                request.setAttribute("artist", artist);
                request.getRequestDispatcher("/viewArtist.jsp").forward(request, response);
            } else {
                response.getWriter().write("Artist tidak ditemukan.");
            }

        } else if ("addAlbum".equals(action)) {
            // Menampilkan form untuk menambahkan album ke artist
            int artistId = Integer.parseInt(request.getParameter("id"));
            Artist artist = new Artist().find(String.valueOf(artistId));
            request.setAttribute("artist", artist);
            request.getRequestDispatcher("/addAlbum.jsp").forward(request, response);
        }
    }

    // Menangani request POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Menambahkan artist baru
            String name = request.getParameter("name");
            String bio = request.getParameter("bio");
            addArtist(name, bio);
            response.sendRedirect("ArtistController?action=list");

        } else if ("update".equals(action)) {
            // Mengupdate artist berdasarkan ID
            int artistId = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String bio = request.getParameter("bio");
            updateArtist(artistId, name, bio);
            response.sendRedirect("ArtistController?action=view&id=" + artistId);

        } else if ("addAlbum".equals(action)) {
            // Menambahkan album ke artist
            int artistId = Integer.parseInt(request.getParameter("artistId"));
            int albumId = Integer.parseInt(request.getParameter("albumId"));
            addAlbumToArtist(artistId, albumId);
            response.sendRedirect("ArtistController?action=view&id=" + artistId);
        }
    }

    // Menangani request DELETE untuk menghapus artist
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int artistId = Integer.parseInt(request.getParameter("id"));
        deleteArtist(artistId);
        response.getWriter().write("Artist dengan ID " + artistId + " telah dihapus.");
    }

    // Menambahkan artist baru
    public void addArtist(String name, String bio) {
        Artist artist = new Artist();
        artist.setName(name);
        artist.setBio(bio);
        artist.insert();  // Menambahkan artist ke database
    }

    // Mengupdate artist berdasarkan ID
    public void updateArtist(int artistId, String name, String bio) {
        Artist artist = new Artist();
        artist.setId(artistId);
        artist.setName(name);
        artist.setBio(bio);
        artist.update();  // Mengupdate artist di database
    }

    // Menambahkan album ke artist
    public void addAlbumToArtist(int artistId, int albumId) {
        Artist artist = new Artist().find(String.valueOf(artistId));
        Album album = new Album().find(String.valueOf(albumId));
        if (artist != null && album != null) {
            artist.addAlbum(album);  // Menambahkan album ke artist
        }
    }

    // Menghapus artist berdasarkan ID
    public void deleteArtist(int artistId) {
        Artist artist = new Artist();
        artist.setId(artistId);
        artist.delete();  // Menghapus artist dari database
    }

    @Override
    public String getServletInfo() {
        return "ArtistController servlet for managing artists and albums";
    }
}
