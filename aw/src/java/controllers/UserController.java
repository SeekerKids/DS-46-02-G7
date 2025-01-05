package controllers;

import models.User;
import models.Premium;
import models.Free;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    // Handle POST requests for CRUD operations
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action != null) {
                switch (action) {
                    case "add" -> {
                        // Add new user
                        String username = request.getParameter("username");
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        String userType = request.getParameter("userType");

                        if (username != null && email != null && password != null && userType != null) {
                            User newUser = "premium".equalsIgnoreCase(userType) ? new Premium() : new Free();
                            newUser.setUsername(username);
                            newUser.setEmail(email);
                            newUser.setPassword(password);
                            newUser.setPremium("premium".equalsIgnoreCase(userType));

                            if (!newUser.create()) {
                                request.setAttribute("errorMessage", "Failed to add user.");
                            }
                        } else {
                            request.setAttribute("errorMessage", "Missing required fields.");
                        }
                    }
                    case "update" -> {
                        // Update user details
                        int id = Integer.parseInt(request.getParameter("id"));
                        String username = request.getParameter("username");
                        String email = request.getParameter("email");

                        User userToUpdate = new User();
                        userToUpdate.setId(id);
                        userToUpdate.setUsername(username);
                        userToUpdate.setEmail(email);

                        if (!userToUpdate.edit()) {
                            request.setAttribute("errorMessage", "Failed to update user.");
                        }
                    }
                    case "delete" -> {
                        // Delete user
                        int id = Integer.parseInt(request.getParameter("id"));

                        User userToDelete = new User();
                        userToDelete.setId(id);

                        if (!userToDelete.delete()) {
                            request.setAttribute("errorMessage", "Failed to delete user.");
                        }
                    }
                    default -> request.setAttribute("errorMessage", "Invalid action.");
                }
            } else {
                request.setAttribute("errorMessage", "Action is missing.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input.");
        } finally {
            // Redirect to admin.jsp after processing
            response.sendRedirect("admin.jsp");
        }
    }

    // Handle GET requests for user profile display
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");

        try {
            if (username != null) {
                User user = new User().findByUsername(username); // Find user by username
                if (user != null) {
                    request.setAttribute("user", user);
                } else {
                    request.setAttribute("errorMessage", "User not found.");
                }
            } else {
                request.setAttribute("errorMessage", "Username is missing.");
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error fetching user data.");
        } finally {
            // Redirect to admin.jsp regardless of the outcome
            response.sendRedirect("admin.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for handling user operations: creating user, updating profile, and deleting account";
    }
}