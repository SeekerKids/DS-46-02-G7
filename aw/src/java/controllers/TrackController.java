package controllers;

import models.Track;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TrackController", urlPatterns = {"/TrackController"})
public class TrackController extends HttpServlet {

    // Handle GET requests for track actions like play, like, and share
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String trackId = request.getParameter("trackId");

        if (trackId != null) {
            try {
                int id = Integer.parseInt(trackId);
                Track track = new Track().findById(id); // Find track by ID
                if (track != null) {
                    switch (action) {
                        case "play" -> track.play();
                        case "like" -> track.like();
                        case "share" -> track.share();
                        default -> {
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                            return;
                        }
                    }
                } else {
                    request.setAttribute("errorMessage", "Track not found.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid track ID.");
            }
        } else {
            request.setAttribute("errorMessage", "Track ID is missing.");
        }
        // Redirect to admin.jsp after processing
        response.sendRedirect("admin.jsp");
    }

    // Handle POST requests for CRUD operations
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action != null) {
                switch (action) {
                    case "add" -> {
                        // Add new track
                        String title = request.getParameter("title");
                        String genre = request.getParameter("genre");
                        double duration = Double.parseDouble(request.getParameter("duration"));
                        int artistId = Integer.parseInt(request.getParameter("artistId"));

                        Track newTrack = new Track();
                        newTrack.setTitle(title);
                        newTrack.setGenre(genre);
                        newTrack.setDuration(duration);
                        newTrack.setArtistId(artistId);
                        newTrack.setStreamCount(0);
                        newTrack.setLikeCount(0);
                        newTrack.setShareCount(0);

                        if (!newTrack.create()) {
                            request.setAttribute("errorMessage", "Failed to add track.");
                        }
                    }
                    case "update" -> {
                        // Update track by ID
                        int id = Integer.parseInt(request.getParameter("id"));
                        String title = request.getParameter("title");
                        String genre = request.getParameter("genre");
                        double duration = Double.parseDouble(request.getParameter("duration"));
                        int artistId = Integer.parseInt(request.getParameter("artistId"));

                        Track trackToUpdate = new Track();
                        trackToUpdate.setId(id);
                        trackToUpdate.setTitle(title);
                        trackToUpdate.setGenre(genre);
                        trackToUpdate.setDuration(duration);
                        trackToUpdate.setArtistId(artistId);

                        if (!trackToUpdate.edit()) {
                            request.setAttribute("errorMessage", "Failed to update track.");
                        }
                    }
                    case "delete" -> {
                        // Delete track by ID
                        int id = Integer.parseInt(request.getParameter("id"));

                        Track trackToDelete = new Track();
                        trackToDelete.setId(id);

                        if (!trackToDelete.delete()) {
                            request.setAttribute("errorMessage", "Failed to delete track.");
                        }
                    }
                    default -> request.setAttribute("errorMessage", "Invalid action.");
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input.");
        } finally {
            // Redirect to admin.jsp after processing
            response.sendRedirect("admin.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "TrackController servlet for managing tracks and actions";
    }
}