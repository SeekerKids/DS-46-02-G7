<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Ads, models.Track, models.User"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin - Manage Database</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Admin - Manage Database</h1>
            <hr>
            <div class="accordion" id="adminAccordion">

                <!-- Manage Ads -->
                <div class="card">
                    <div class="card-header" id="headingAds">
                        <h2 class="mb-0">
                            <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseAds" aria-expanded="true" aria-controls="collapseAds">
                                Manage Ads
                            </button>
                        </h2>
                    </div>

                    <div id="collapseAds" class="collapse show" aria-labelledby="headingAds" data-parent="#adminAccordion">
                        <div class="card-body">
                            <h3>Add New Ad</h3>
                            <form action="AdsController" method="post">
                                <input type="hidden" name="action" value="add">
                                <div class="form-group">
                                    <label for="content">Content</label>
                                    <textarea class="form-control" id="content" name="content" rows="3" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="duration">Duration (seconds)</label>
                                    <input type="number" class="form-control" id="duration" name="duration" step="0.01" required>
                                </div>
                                <button type="submit" class="btn btn-success">Add Ad</button>
                            </form>
                            <hr>

                            <h3>All Ads</h3>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Content</th>
                                        <th>Duration</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% List<Ads> adsList = new Ads().get(); %>
                                    <% if (adsList != null && !adsList.isEmpty()) { %>
                                    <% for (Ads ad : adsList) {%>
                                    <tr>
                                        <td><%= ad.getId()%></td>
                                        <td><%= ad.getContent()%></td>
                                        <td><%= ad.getDuration()%></td>
                                        <td>
                                            <!-- Update Form -->
                                            <form action="AdsController" method="post" class="d-inline">
                                                <input type="hidden" name="action" value="update">
                                                <input type="hidden" name="id" value="<%= ad.getId()%>">
                                                <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                                            </form>
                                            <!-- Delete Form -->
                                            <form action="AdsController" method="post" class="d-inline">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="id" value="<%= ad.getId()%>">
                                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                            </form>
                                        </td>
                                    </tr>
                                    <% } %>
                                    <% } else { %>
                                    <tr>
                                        <td colspan="4" class="text-center">No Ads Available</td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- Manage Tracks -->
                <div class="card">
                    <div class="card-header" id="headingTracks">
                        <h2 class="mb-0">
                            <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTracks" aria-expanded="false" aria-controls="collapseTracks">
                                Manage Tracks
                            </button>
                        </h2>
                    </div>

                    <div id="collapseTracks" class="collapse" aria-labelledby="headingTracks" data-parent="#adminAccordion">
                        <div class="card-body">
                            <h3>Add New Track</h3>
                            <form action="TrackController" method="post">
                                <input type="hidden" name="action" value="add">
                                <div class="form-group">
                                    <label for="title">Title</label>
                                    <input type="text" class="form-control" id="title" name="title" required>
                                </div>
                                <div class="form-group">
                                    <label for="genre">Genre</label>
                                    <input type="text" class="form-control" id="genre" name="genre" required>
                                </div>
                                <div class="form-group">
                                    <label for="duration">Duration (seconds)</label>
                                    <input type="number" class="form-control" id="duration" name="duration" step="0.01" required>
                                </div>
                                <button type="submit" class="btn btn-success">Add Track</button>
                            </form>
                            <hr>

                            <h3>All Tracks</h3>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Title</th>
                                        <th>Genre</th>
                                        <th>Duration</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% List<Track> trackList = new Track().get(); %>
                                    <% if (trackList != null && !trackList.isEmpty()) { %>
                                    <% for (Track track : trackList) {%>
                                    <tr>
                                        <td><%= track.getId()%></td>
                                        <td><%= track.getTitle()%></td>
                                        <td><%= track.getGenre()%></td>
                                        <td><%= track.getDuration()%></td>
                                        <td>
                                            <form action="TrackController" method="post" class="d-inline">
                                                <input type="hidden" name="action" value="update">
                                                <input type="hidden" name="id" value="<%= track.getId()%>">
                                                <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                                            </form>
                                            <form action="TrackController" method="post" class="d-inline">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="id" value="<%= track.getId()%>">
                                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                            </form>
                                        </td>
                                    </tr>
                                    <% } %>
                                    <% } else { %>
                                    <tr>
                                        <td colspan="5" class="text-center">No Tracks Available</td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- Manage Users -->
                <div class="card">
                    <div class="card-header" id="headingUsers">
                        <h2 class="mb-0">
                            <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseUsers" aria-expanded="false" aria-controls="collapseUsers">
                                Manage Users
                            </button>
                        </h2>
                    </div>

                    <div id="collapseUsers" class="collapse" aria-labelledby="headingUsers" data-parent="#adminAccordion">
                        <div class="card-body">
                            <h3>Add New User</h3>
                            <form action="UserController" method="post">
                                <input type="hidden" name="action" value="add">
                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <input type="text" class="form-control" id="username" name="username" required>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" required>
                                </div>
                                <button type="submit" class="btn btn-success">Add User</button>
                            </form>
                            <hr>

                            <h3>All Users</h3>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Username</th>
                                        <th>Email</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% List<User> userList = new User().get(); %>
                                    <% if (userList != null && !userList.isEmpty()) { %>
                                    <% for (User user : userList) {%>
                                    <tr>
                                        <td><%= user.getId()%></td>
                                        <td><%= user.getUsername()%></td>
                                        <td><%= user.getEmail()%></td>
                                        <td>
                                            <form action="UserController" method="post" class="d-inline">
                                                <input type="hidden" name="action" value="update">
                                                <input type="hidden" name="id" value="<%= user.getId()%>">
                                                <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                                            </form>
                                            <form action="UserController" method="post" class="d-inline">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="id" value="<%= user.getId()%>">
                                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                            </form>
                                        </td>
                                    </tr>
                                    <% } %>
                                    <% } else { %>
                                    <tr>
                                        <td colspan="4" class="text-center">No Users Available</td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
        </div>
 
        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
