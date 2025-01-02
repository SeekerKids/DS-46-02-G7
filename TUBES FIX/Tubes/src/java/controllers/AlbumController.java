package controllers;

import models.Album;
import models.Track;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet(name = "AlbumController", urlPatterns = {"/AlbumController"})
public class AlbumController extends HttpServlet {

    // Menangani request GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Menampilkan semua album
        if (action == null || "list".equals(action)) {
            ArrayList<Album> albums = new Album().get();  // Mengambil semua album dari database
            request.setAttribute("albums", albums);
            request.getRequestDispatcher("/viewAlbums.jsp").forward(request, response);
        } else if ("view".equals(action)) {
            // Menampilkan album berdasarkan ID
            int albumId = Integer.parseInt(request.getParameter("id"));
            Album album = new Album().find(String.valueOf(albumId));
            if (album != null) {
                // Menampilkan track di album
                request.setAttribute("album", album);
                request.getRequestDispatcher("/viewAlbum.jsp").forward(request, response);
            } else {
                response.getWriter().write("Album tidak ditemukan.");
            }
        } else if ("addTrack".equals(action)) {
            // Menampilkan form untuk menambahkan track ke album
            int albumId = Integer.parseInt(request.getParameter("id"));
            Album album = new Album().find(String.valueOf(albumId));
            request.setAttribute("album", album);
            request.getRequestDispatcher("/addTrack.jsp").forward(request, response);
        }
    }

    // Menangani request POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Menambahkan album baru
            String title = request.getParameter("title");
            String releaseDate = request.getParameter("releaseDate");
            int artistId = Integer.parseInt(request.getParameter("artistId"));
            addAlbum(title, releaseDate, artistId);
            response.sendRedirect("AlbumController?action=list");

        } else if ("update".equals(action)) {
            // Mengupdate album berdasarkan ID
            int albumId = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String releaseDate = request.getParameter("releaseDate");
            int artistId = Integer.parseInt(request.getParameter("artistId"));
            updateAlbum(albumId, title, releaseDate, artistId);
            response.sendRedirect("AlbumController?action=view&id=" + albumId);

        } else if ("addTrack".equals(action)) {
            // Menambahkan track ke album
            int albumId = Integer.parseInt(request.getParameter("albumId"));
            int trackId = Integer.parseInt(request.getParameter("trackId"));
            addTrackToAlbum(albumId, trackId);
            response.sendRedirect("AlbumController?action=view&id=" + albumId);
        }
    }

    // Menangani request DELETE untuk menghapus album
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int albumId = Integer.parseInt(request.getParameter("id"));
        deleteAlbum(albumId);
        response.getWriter().write("Album dengan ID " + albumId + " telah dihapus.");
    }

    // Menambahkan album baru
    public void addAlbum(String title, String releaseDate, int artistId) {
        Album album = new Album();
        album.setTitle(title);
        album.setRelase(java.sql.Date.valueOf(releaseDate));  // Mengonversi String ke Date
        album.setArtistId(artistId);
        album.insert();  // Menambahkan album ke database
    }

    // Mengupdate album berdasarkan ID
    public void updateAlbum(int albumId, String title, String releaseDate, int artistId) {
        Album album = new Album();
        album.setId(albumId);
        album.setTitle(title);
        album.setRelase(java.sql.Date.valueOf(releaseDate));  // Mengonversi String ke Date
        album.setArtistId(artistId);
        album.update();  // Mengupdate album di database
    }

    // Menambahkan track ke album
    public void addTrackToAlbum(int albumId, int trackId) {
        Album album = new Album().find(String.valueOf(albumId));
        Track track = new Track().find(String.valueOf(trackId));
        if (album != null && track != null) {
            album.addTrack(track);  // Menambahkan track ke album
        }
    }

    // Menghapus album berdasarkan ID
    public void deleteAlbum(int albumId) {
        Album album = new Album();
        album.setId(albumId);
        album.delete();  // Menghapus album dari database
    }

    @Override
    public String getServletInfo() {
        return "AlbumController servlet for managing albums and tracks";
    }
}
