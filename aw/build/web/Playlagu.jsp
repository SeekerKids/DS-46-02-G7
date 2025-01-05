<!DOCTYPE html>
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
                width: calc(50% - 20px);
                box-sizing: border-box;
                text-align: center;
            }

            .container {
                display: flex;
                flex-wrap: wrap;
                background:#1f1f1f;
                display: flex;
                align-items:center;
                justify-content:center;
                width: 50%;
                height: 600px;
                margin-top: 100px;
                margin-bottom: 100px;
            }


            .album {
                background-color: #1e1e1e;
                width: 700px;
                height: 600px;
                display: flex;
                align-items:center;
                justify-content:center;
            }

            .artist {
                margin-left: 250px;
                background-color: #1e1e1e;
                width: 400px;
                height: 500px;
                display: flex;
                align-items:center;
                justify-content:right;
            }

            .judullagu {
                display: flex;
                flex-direction: column;
                justify-content:center;
                margin-bottom: 100px;
                margin-left: 20px;
            }

            .judulartist {
                display: flex;
                flex-direction: column;
                margin-bottom: 10px;
                margin-right: 10px;
                margin-left: 10px;
            }

            .judulalbum {
                display: flex;
                margin-left: 20px;
            }

            .gambarartist {
                width: 400px;
                height: 400px;
                margin-right: 10px;
                margin-left:10px;
                display:flex;
                align-items:center;
                justify-content:center;
            }




            .soundwave {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 50px;
                gap: 5px;
                height: 100px;
            }


            .soundwave div {
                width: 50px;
                height: 100px;
                background: linear-gradient(30deg, #218ED6, #8EC0E1);
                animation: wave 1s ease-in-out infinite;
                animation-delay: calc(var(--i) * 0.2s);
            }

            @keyframes wave {
                0%, 100% {
                    height: 120px;
                }
                50% {
                    height: 200px;
                }
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
                <!-- Sidebar -->
                <div class="container">
                    <div>
                        <%
                            // Get the track_id from the URL
                            String artistName = request.getParameter("artist_name");
                            int trackId = Integer.parseInt(request.getParameter("track_id"));

                            // Initialize Track model to fetch track details
                            models.Artist artistModel = new models.Artist();
                            models.Artist artist = artistModel.findByName(artistName);

                            models.Track trackModel = new models.Track();
                            models.Track track = trackModel.findById(trackId); // Fetch track using the trackId

                            if (trackModel != null && artist != null) {
                        %>
                        <div class="judulartist">
                            <h5>Artist</h5>
                            <h3><%= artist.getName()%></h3>
                        </div>
                        <img src="<%= artist.getGambar()%>" alt="Artist Image" class="gambarartist" style="border: 10px solid #252525;">
                    </div> 

                    <!-- Menampilkan informasi artist dan track -->
                    <div class="gambaralbum">
                        <div class="judulartist">
                            <h5>Lagu</h5>
                            <h3><%= track.getTitle()%></h3>
                        </div>                 
                        <img src="<%= artist.getGambar()%>" alt="Track Image" class="gambarartist" style="border: 10px solid #252525;">
                    </div>

                    <!-- Bagian untuk like, share, dan stream -->
                    <div class="track-info">
                        <!-- Menampilkan jumlah like, share, dan stream -->
                        <div class="counters mt-3">
                            <p>Likes: <%= track.getLikeCount()%></p>
                            <p>Shares: <%= track.getShareCount()%></p>
                            <p>Streams: <%= track.getStreamCount()%></p>
                        </div>

                        <!-- Tombol aksi -->
                        <div class="actions mt-3">
                            <form action="TrackController" method="get">
                                <input type="hidden" name="trackId" value="<%= track.getId()%>">
                                <button class="btn btn-primary" type="submit" name="action" value="like">Like</button>
                                <button class="btn btn-success" type="submit" name="action" value="share">Share</button>
                                <button class="btn btn-info" type="submit" name="action" value="play">Play</button>
                            </form>
                        </div>
                    </div>
                    <%} else { %>
                    <div class="alert alert-danger" role="alert">
                        Track not found!
                    </div>
                    <% }%>
                </div>

            </div>
            <div class="soundwave">
                <div style="--i: 0"></div>
                <div style="--i: 1"></div>
                <div style="--i: 0"></div>
                <div style="--i: 1"></div>
                <div style="--i: 2"></div>
                <div style="--i: 3"></div>
                <div style="--i: 4"></div>
                <div style="--i: 0"></div>
                <div style="--i: 1"></div>
                <div style="--i: 2"></div>
                <div style="--i: 3"></div>
                <div style="--i: 4"></div>
                <div style="--i: 0"></div>
                <div style="--i: 1"></div>
                <div style="--i: 2"></div>
                <div style="--i: 3"></div>
                <div style="--i: 4"></div>
                <div style="--i: 0"></div>
                <div style="--i: 1"></div>
                <div style="--i: 0"></div>
                <div style="--i: 1"></div>
                <div style="--i: 2"></div>
                <div style="--i: 3"></div>
                <div style="--i: 4"></div>
                <div style="--i: 0"></div>
                <div style="--i: 1"></div>
                <div style="--i: 2"></div>
                <div style="--i: 3"></div>
                <div style="--i: 4"></div>
                <div style="--i: 0"></div>
                <div style="--i: 1"></div>
                <div style="--i: 2"></div>
                <div style="--i: 3"></div>
                <div style="--i: 4"></div>
            </div> 

            <!-- Music Player -->
            <div class="music-player" style="background-color: #1e1e1e; padding: 20px; border-radius: 10px; position: fixed; bottom: 0; width: 100%;">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-md-4">
                            <div class="now-playing">
                                <img src="<%= artist.getGambar()%>" alt="Track" class="track-thumbnail">
                                <div>
                                    <p class="track-title mb-0"><%= track.getTitle()%></p>
                                    <small class="track-artist"><%= artist.getName()%></small>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="player-controls">
                                <button class="tombol"><img src = "img/shuffle.png" class = "fototombol"></button>
                                <button class="tombol"><img src = "img/playback kiri.png" class = "fototombol"></button>
                                <button class="tombol"><img src = "img/Polygon 1.png" class = "fototombol"></button>
                                <button class="tombol"><img src = "img/playback kanan.png" class = "fototombol"></button>
                                <button class="tombol"><img src = "img/replay.png" class = "fototombol"></button>
                            </div>
                            <div class="progress-bar mt-2">
                                <input type="range" class="form-range" min="0" max="100" value="30" id="player-range">
                                <div class="time-stamps d-flex justify-content-between">
                                    <span class="current-time">0:00</span>
                                    <span class="total-time">3:45</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>


            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.js"></script>
    </body>
</html>
