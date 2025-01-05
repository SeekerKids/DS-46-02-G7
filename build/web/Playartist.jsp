<!DOCTYPE html>
<%@ page import="java.sql.*" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>MusicStream</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #121212;
                color: #ffffff;
            }
            .card {
                background-color: #1e1e1e;
                border: none;
            }
            .navbar {
                background-color: #1f1f1f;
            }
            .search-bar {
                background-color: #000000;
                border: none;
                color: #fff;
                padding: 10px;
                width: 300px;
            }
            .search-bar:focus {
                outline: none;
                box-shadow: 0 0 5px rgba(255, 255, 255, 0.5);
            }
            .profile-section {
                display: flex;
                align-items: center;
                gap: 10px;
            }
            .profile-section img {
                border-radius: 50%;
                width: 40px;
                height: 40px;
            }
            .track-card {
                background-color: #252525;
                border-radius: 10px;
                text-align: center;
                padding: 10px;
            }
            .track-card img {
                border-radius: 10px;
            }

            #music Player yang Dibawah

            .now-playing {
                display: flex;
                align-items: center;
            }

            .track-thumbnail {
                width: 50px;
                height: 50px;
                border-radius: 5px;
                margin-right: 15px;
            }

            .track-title {
                font-size: 16px;
                font-weight: bold;
            }

            .track-artist {
                font-size: 14px;
                color: #aaa;
            }

            .tombol {
                background: none;
                border: none;
                padding: 0;
                margin: 0;
                cursor: pointer;
                width: 30px;
                height: 30px;
                margin-left: 70px;
                margin-bottom: 20px;
            }


            .progress-bar {
                width: 100%;
            }

            #player-range {
                width: 100%;
            }

            .time-stamps {
                font-size: 12px;
                color: #aaa;
            }

            .btn-primary:hover {
                background-color: #1ed760;
            }

            .fototombol {
                width: 35px;
                height: 30px;
                object-fit: contain;
            }

            .music-player {
                position: sticky;
                bottom: 0px;
            }

            .lol {
                margin-top: 30px;
                width: 100%;
                box-sizing: border-box;
                text-align: center;
                display: flex;
                flex-direction: row;
                margin-bottom: 20px;
            }

            .lol img {
                width: 50%;
                height: 70%;
            }

            .juduls {
                display: flex;
                flex-direction: column;
                margin-left: 20px;
                width:-800px;
            }

            .container {
                width: 30%;
                display: flex;
                align-items: center;
                justify-content: center;
                flex-direction: column;
                margin-bottom: 30px;
            }

            .judullagu {
                margin-right: 30px;
                display: flex;
                align-items: center;
                justify-content: left;
                flex-direction: column;

            }

            .gambaralbum {
                width: 90%;
                height: 90%;
            }

            .library-section {
                background-color: #494949;
                border-radius: 10px;
                padding: 15px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.7);
                display: flex;
            }

            .bigcontainer {
                display: flex;
                flex-direction: row;
            }


        </style>
    </head>
    <body>  



        <!-- Navbar -->    
        <nav class="navbar navbar-expand-lg navbar-dark" style="position: sticky; top:0px">
            <div class="container-fluid">
                <div class="profile-section">
                    <img src="img/profile.png" alt="Profile">
                    <div>
                        <p class="mb-0">Selamat Pagi</p>
                        <h6>@<%= session.getAttribute("username")%></h6>
                    </div>
                </div>
                <div class="mx-auto">
                    <form class="d-flex">
                        <input class="search-bar me-2" type="search" placeholder="Cari Artist" aria-label="Search">
                        <button class="btn btn-outline-light" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>


        <!-- Main Content -->
        <div class="container">
            <%
                // Memanggil model Artist untuk mengambil data artis berdasarkan ID yang diberikan
                int artistId = Integer.parseInt(request.getParameter("artist_id"));
                models.Artist artistModel = new models.Artist();
                models.Artist artist = artistModel.findById(artistId);

                if (artist != null) {
            %>
            <!-- Menampilkan foto dan nama artist -->
            <div class="album">
                <img src="<%= artist.getGambar()%>" alt="Track" class="gambaralbum" style="border: 10px solid #252525;">
            </div>
            <div class="judullagu">
                <h1><%= artist.getName()%></h1>
                <h5>Artist</h5>
            </div>

            <!-- Menampilkan list lagu-lagu yang dimiliki artist -->
            <div>
                <%
                    // Memanggil track yang dimiliki oleh artis ini
                    ResultSet rs = artist.getTracks(); // Mendapatkan ResultSet
                    if (rs != null) {
                        while (rs.next()) {
                %>
                <div class="lol">
                    <img src="<%= artist.getGambar()%>" alt="Track" class="img-fluid" onclick="window.location.href = 'Playlagu.jsp?artist_name=<%= artist.getName()%>&track_id=<%= rs.getInt("id")%>';">
                    <div class="juduls">
                        <h3><%= rs.getString("title")%></h3>
                        <h5><%= artist.getName()%></h5>
                    </div>
                </div>
                <%
                    }
                } else {
                %>
                <p>No tracks found for this artist.</p>
                <%
                    }
                %>
            </div>
            <%
            } else {
            %>
            <p>Artist not found.</p>
            <%
                }
            %>
        </div>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.js"></script>
    </body>
</html>