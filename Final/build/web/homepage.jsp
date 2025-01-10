<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HomePage MusicStream</title>
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
            #playlist-card {
                background-color: #1e1e1e;
                border-radius: 10px;
                padding: 15px;
                margin-bottom: 15px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
                display: flex;
                text-align: left;
                gap: 15px;
                flex-wrap: wrap;
                align-items: center;
                justify-content: center;
            }

            #playlist-card img:hover {
                opacity: 0.7;
                height: 85%;
                width: 85%;
            }

            .track-card img:hover {
                height: 90%;
                width: 90%;
            }

            #playlist-card img {
                height: 90%;
                width: 90%;
            }

            #playlist-card h6 {
                margin-bottom: 10px;
            }

            .playlist-card button {
                position: absolute;
                top: 85%;
                right: 0px;
                transform: translateY(0%);
                background-color: #1e1e1e;
                border: none;
                width: 40px;
                height: 40px;
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 20px;
            }

            .playlist-card h3 {
                margin-bottom: auto;
            }

            .playlist-card p {
                margin-top: 0px;
            }

            .playlist-card button i {
                color: white;
            }
            .library-section {
                background-color: #1e1e1e;
                border-radius: 10px;
                padding: 15px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.7);
                display: flex;
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
                width: calc(50% - 20px);
                box-sizing: border-box;
                text-align: center;
            }



            .contain {
                margin-left: 250px;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                width: 110%;
            }

            .artist-grid {
                display: flex;
                flex-wrap: wrap;  /* Membuat item bergerak ke baris berikutnya saat penuh */
                gap: 20px;  /* Memberikan jarak antar item */
            }

            .artist {
                width: calc(25% - 20px);  /* Tiga item per baris, mengurangi margin */
                text-align: center;
                margin-bottom: 20px;  /* Memberikan jarak antar baris */
            }

            .artist img {
                width: 100%;
                height: auto;
                border-radius: 10px;
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
    <div class="container-fluid mt-4">
        <div class="row">
            <div class="artist-grid">
                <%
                    // Memanggil model Artist untuk mengambil semua artis
                    models.Artist artistModel = new models.Artist();
                    java.util.ArrayList<models.Artist> artists = artistModel.getAll();

                    if (artists != null && !artists.isEmpty()) {
                        for (models.Artist artist : artists) {
                %>
                <div class="artist">
                    <a href="Playartist.jsp?artist_id=<%= artist.getId()%>">
                    <img src="<%= artist.getGambar()%>" alt="Artist Image">
                    </a>
                    <p><%= artist.getName()%></p>
                    <p>Biografi : <%= artist.getBio()%></p>
                </div>
                <%
                    }
                } else {
                %>
                <p style="text-align:center;">No artists found.</p>
                <%
                    }
                %>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.js"></script>
</body>
</html>