package controllers;

import models.User;
import models.Playlist;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    // Menangani request untuk membuat playlist baru
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("createPlaylist".equals(action)) {
            String username = request.getParameter("username");
            String playlistName = request.getParameter("playlistName");
            String description = request.getParameter("description");
            String[] trackIds = request.getParameterValues("trackIds");

            if (username != null && playlistName != null && trackIds != null) {
                // Mencari user berdasarkan username menggunakan method find
                User user = new User().find(username); // Menggunakan find yang ada di Model
                if (user != null) {
                    // Mengubah trackIds menjadi ArrayList<Integer>
                    ArrayList<Integer> trackList = new ArrayList<>();
                    for (String trackId : trackIds) {
                        try {
                            trackList.add(Integer.parseInt(trackId)); // Menambah track ID ke list
                        } catch (NumberFormatException e) {
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid track ID format");
                            return;
                        }
                    }

                    // Membuat playlist untuk user
                    Playlist playlist = new Playlist();
                    playlist.setName(playlistName);
                    playlist.setDescription(description);
                    playlist.setUserId(user.getId());  // Asumsi ada getId() pada User
                    playlist.setTracks(trackList);
                    playlist.insert(); // Menyimpan playlist ke database

                    // Menambahkan track ke playlist
                    for (int trackId : trackList) {
                        playlist.addTrack(trackId);
                    }

                    // Redirect ke halaman user profile atau playlist
                    response.sendRedirect("userProfile.jsp?username=" + username + "&status=playlistCreated");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    // Menangani request untuk menghapus playlist
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String playlistIdStr = request.getParameter("playlistId");

        if (playlistIdStr != null) {
            try {
                int playlistId = Integer.parseInt(playlistIdStr);
                String username = request.getParameter("username");

                // Mencari user berdasarkan username menggunakan method find
                User user = new User().find(username); // Menggunakan find yang ada di Model
                if (user != null) {
                    // Menghapus playlist berdasarkan ID
                    Playlist playlist = new Playlist().find(String.valueOf(playlistId));
                    if (playlist != null && playlist.getUserId() == user.getId()) {
                        playlist.delete(); // Menghapus playlist
                        response.sendRedirect("userProfile.jsp?username=" + username + "&status=playlistDeleted");
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Playlist not found or does not belong to user");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid playlist ID format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing playlist ID");
        }
    }

    // Menangani request untuk menampilkan daftar playlist pengguna
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");

        if (username != null) {
            // Mencari user berdasarkan username menggunakan method find
            User user = new User().find(username); // Menggunakan find yang ada di Model
            if (user != null) {
                // Mengambil daftar playlist berdasarkan userId
                ArrayList<Playlist> playlists = new Playlist().get(); // Ambil semua playlist
                ArrayList<Playlist> userPlaylists = new ArrayList<>();
                for (Playlist playlist : playlists) {
                    if (playlist.getUserId() == user.getId()) {
                        userPlaylists.add(playlist);
                    }
                }

                request.setAttribute("playlists", userPlaylists);
                request.getRequestDispatcher("/userProfile.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username is missing");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet untuk menangani operasi terkait user: membuat playlist, menghapus playlist, dan menampilkan playlist";
    }
}
