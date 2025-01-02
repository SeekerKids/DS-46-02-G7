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
        
        
        
    </style>
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark" style= "position: sticky; top:0px">
    <div class="container-fluid">
        <div class="profile-section">
            <img src="img/profile.png" alt="Profile">
            <div>
                <p class="mb-0">Selamat Pagi</p>
                <h6>@Lorem Ipsum</h6>
            </div>
        </div>
        <div class="mx-auto">
            <form class="d-flex">
                <input class="search-bar me-2" type="search" placeholder="Cari Data" aria-label="Search">
                <button class="btn btn-outline-light" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>





<!-- Main Content -->
<div class="container-fluid mt-4">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-3">
            <h4>Your Library</h4>
            <div class="library-section">
                
                <div id="playlist-card" class="d-flex flex-wrap justify-content-center gap-3">
                    <div class="lol">
                    <img src="img/fotoplaylist.png" alt="Track" class="img-fluid">
                    
                    <h3>Playlist-#1</h3>
                    <p>1. Nama Lagu<br>2. Lorem Ipsum<br>3. Lorem Ipsum</p>
                    </div>
                    
                    <div class="lol">
                    <img src="img/fotoplaylist.png" alt="Track" class="img-fluid">
                    
                    <h3>Playlist-#2</h3>
                    <p>1. Nama Lagu<br>2. Lorem Ipsum<br>3. Lorem Ipsum</p>
                    </div>
                    <div class="lol">
                    <img src="img/fotoplaylist.png" alt="Track" class="img-fluid">
                    
                    <h3>Playlist-#3</h3>
                    <p>1. Nama Lagu<br>2. Lorem Ipsum<br>3. Lorem Ipsum</p>
                    </div>
                    
                    <div class="lol">
                    <img src="img/fotoplaylist.png" alt="Track" class="img-fluid">
                    
                    <h3>Playlist-#4</h3>
                    <p>1. Nama Lagu<br>2. Lorem Ipsum<br>3. Lorem Ipsum</p>
                    </div>

                    <div class="lol">
                    <img src="img/fotoplaylist.png" alt="Track" class="img-fluid">
                    
                    <h3>Playlist-#5</h3>
                    <p>1. Nama Lagu<br>2. Lorem Ipsum<br>3. Lorem Ipsum</p>
                    </div>

                    <div class="lol">
                    <img src="img/fotoplaylist.png" alt="Track" class="img-fluid">
                    
                    <h3>Playlist-#6</h3>
                    <p>1. Nama Lagu<br>2. Lorem Ipsum<br>3. Lorem Ipsum</p>
                    </div>                    
                </div>
            </div>
        </div>

        <!-- Main Section -->
<div class="col-md-9">
            <h4>Recently Played</h4>
            <div class="row">
                <div class="col-md-3">
                    <div class="track-card">
                        <img src="img/Serina.png" alt="Track" class="img-fluid">
                        <p class="mt-2">Nama Lagu<br>#Pembuat Lagu</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="track-card">
                        <img src="img/Serina.png" alt="Track" class="img-fluid">
                        <p class="mt-2">Nama Lagu<br>#Pembuat Lagu</p>
                    </div>
                </div>
                
                <div class="col-md-3">
                    <div class="track-card">
                        <img src="img/Serina.png" alt="Track" class="img-fluid">
                        <p class="mt-2">Nama Lagu<br>#Pembuat Lagu</p>
                    </div>
                </div>
                
                <div class="col-md-3">
                    <div class="track-card">
                        <img src="img/Serina.png" alt="Track" class="img-fluid">
                        <p class="mt-2">Nama Lagu<br>#Pembuat Lagu</p>
                    </div>
                </div>
            </div>
            <h4 class="mt-4">Recommended</h4>
            <div class="row">
                <div class="col-md-3">
                    <div class="track-card">
                        <img src="img/Serina.png" alt="Track" class="img-fluid">
                        <p class="mt-2">Nama Lagu<br>#Pembuat Lagu</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="track-card">
                        <img src="img/Serina.png" alt="Track" class="img-fluid">
                        <p class="mt-2">Nama Lagu<br>#Pembuat Lagu</p>
                    </div>
                </div>
                
                <div class="col-md-3">
                    <div class="track-card">
                        <img src="img/Serina.png" alt="Track" class="img-fluid">
                        <p class="mt-2">Nama Lagu<br>#Pembuat Lagu</p>
                    </div>
                </div>
                
                <div class="col-md-3">
                    <div class="track-card">
                        <img src="img/Serina.png" alt="Track" class="img-fluid">
                        <p class="mt-2">Nama Lagu<br>#Pembuat Lagu</p>
                    </div>
                </div>
            </div>     
        </div>        
    </div>
</div>

<!-- Music Player -->
<div class="music-player" style="background-color: #1e1e1e; color: white; padding: 20px; border-radius: 10px;">
    <div class="container-fluid">
        <div class="row align-items-center">
            <div class="col-md-4">
                <div class="now-playing">
                    <img src="img/Serina.png" alt="Track" class="track-thumbnail">
                    <div>
                        <p class="track-title mb-0">Nama Lagu</p>
                        <small class="track-artist">#Pembuat Lagu</small>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="player-controls">
                    <button class="tombol"><img src = "img/shuffle.png" class = "fototombol"></button>
                    <button class="tombol"><img src = "img/playback kiri.png" class = "fototombol"></button>
                    <button class="tombol"><img src = "img/Polygon 1.png" class = "fototombol"></button>
                    <button class="tombol"><img src = "img/playback kanan.png" class = "fototombol"></i></button>
                    <button class="tombol"><img src = "img/replay.png" class = "fototombol"></i></button>
                </div>
                <div class="progress-bar mt-2">
                    <input type="range" class="form-range" min="0" max="100" value="30" id="player-range">
                    <div class="time-stamps d-flex justify-content-between">
                        <span class="current-time">0:30</span>
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