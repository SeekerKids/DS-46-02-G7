package controllers;

import models.Track;
import models.Artist;
import models.Album;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TrackController", urlPatterns = {"/TrackController"})
public class TrackController extends HttpServlet {

    // Menangani request untuk memutar lagu
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String trackId = request.getParameter("trackId");

        if (trackId != null) {
            Track track = new Track().find(trackId); // Mencari track berdasarkan ID

            if (track != null) {
                if ("play".equals(action)) {
                    track.play(); // Memutar lagu
                    response.sendRedirect("trackDetails.jsp?trackId=" + trackId + "&status=played");
                } else if ("like".equals(action)) {
                    track.like(); // Menyukai lagu
                    response.sendRedirect("trackDetails.jsp?trackId=" + trackId + "&status=liked");
                } else if ("share".equals(action)) {
                    track.share(); // Membagikan lagu
                    response.sendRedirect("trackDetails.jsp?trackId=" + trackId + "&status=shared");
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Track not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Track ID is missing");
        }
    }

    // Menangani request untuk menampilkan detail track
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String trackId = request.getParameter("trackId");
        if (trackId != null) {
            Track track = new Track().find(trackId);
            if (track != null) {
                request.setAttribute("track", track);
                request.getRequestDispatcher("/trackDetails.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Track not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Track ID is missing");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet untuk menangani operasi terkait track: play, like, dan share";
    }
}
