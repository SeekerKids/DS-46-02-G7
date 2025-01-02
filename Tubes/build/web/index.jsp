<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
       body {
            background: linear-gradient(180deg, #4D36BF, #241959);
            color: #fff;
            display: flex;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            display: flex;
            width: 100%;
            height: 100%;
            justify-content: center;
            align-items: center;
            gap: 2rem;
        }

        .gambar {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .gambar img {
            max-width: 30%;
            height: auto;
        }

        .login-card {
            flex: 1;
            background-color: #1c1c1c;
            background-color: rgba(28, 28, 28, 0.8);
            border-radius: 10px;
            padding: 2rem;
            max-width: 600px;
            margin-right: 20px;
            height:94vh;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        
        .teks {
            margin-left: 20px;
            font-size: 100px;
            font-weight: bold;
            font-family: 'Roboto', sans-serif;
        }

        .login-card h2 {
            text-align: center;
            margin-bottom: 1rem;
            margin-top:150px;
        }

        .login-card button {
            width: 100%;
            margin-top: 1rem;
        }

        .text-center {
            color: rgba(255, 255, 255, 0.6);
            text-align: center;
        }

        input[type="password"], input[type="text"] {
            background-color: #1c1c1c;
            color: white;
            border: 1px solid rgba(255, 255, 255, 0.5);
            border-radius: 5px;
            width: 100%;
            padding: 0.5rem;
            margin-bottom: 1rem;
        }

        input[type="password"]::placeholder, input[type="text"]::placeholder {
            color: rgba(255, 255, 255, 0.6);
        }
    </style>
</head>
<body>
    <div class="gambar">
        <img src="img/Logo kucing.png" alt="alt"/>
        <p class="teks">Catify</p>
    </div>
    <div class="login-card">
        <h2>Masuk</h2>
        <p class="text-center">Masuk dengan akun anda</p>
        <form action="homepage.jsp" method="POST">
            <div class="mb-3">
                <label for="username" class="form-label"></label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Username" required>
                <label for="password" class="form-label"></label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>

</body>
</html>
