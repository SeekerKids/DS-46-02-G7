package controllers;

import models.Playlist;
import models.Track;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PlaylistController", urlPatterns = {"/PlaylistController"})
public class PlaylistController extends HttpServlet {

    // Menangani request GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int playlistId = Integer.parseInt(request.getParameter("playlistId"));
        
        Playlist playlist = new Playlist().find(String.valueOf(playlistId));  // Menyesuaikan dengan metode find() pada model Playlist
        
        if (playlist != null) {
            // Mengambil daftar track yang ada dalam playlist
            playlist.loadTracks();
            request.setAttribute("playlist", playlist);
            request.getRequestDispatcher("/viewPlaylist.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Playlist tidak ditemukan.");
        }
    }

    // Menangani request POST untuk operasi seperti menambah atau menghapus track
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int playlistId = Integer.parseInt(request.getParameter("playlistId"));
        Playlist playlist = new Playlist().find(String.valueOf(playlistId));
        
        if (playlist != null) {
            if ("addTrack".equals(action)) {
                int trackId = Integer.parseInt(request.getParameter("trackId"));
                Track track = new Track().find(String.valueOf(trackId));  // Menemukan track berdasarkan ID
                if (track != null) {
                    playlist.addTrack(trackId);  // Menambahkan track ke playlist
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Track tidak ditemukan.");
                }
                response.sendRedirect("/PlaylistController?playlistId=" + playlistId);
            } else if ("removeTrack".equals(action)) {
                int trackId = Integer.parseInt(request.getParameter("trackId"));
                Track track = new Track().find(String.valueOf(trackId));  // Menemukan track berdasarkan ID
                if (track != null) {
                    playlist.removeTrack(trackId);  // Menghapus track dari playlist
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Track tidak ditemukan.");
                }
                response.sendRedirect("/PlaylistController?playlistId=" + playlistId);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Aksi tidak valid.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Playlist tidak ditemukan.");
        }
    }

    @Override
    public String getServletInfo() {
        return "PlaylistController servlet untuk mengelola playlist dan tracks.";
    }
}
