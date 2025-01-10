<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h2 class="text-center">Daftar Akun Baru</h2>
            <form action="SignUpController" method="post"> 
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" id="username" name="username" class="form-control" placeholder="Username" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" id="email" name="email" class="form-control" placeholder="Email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
                </div>
                <div class="mb-3">
                    <label for="premium" class="form-label">Premium?</label>
                    <select id="premium" name="premium" class="form-control">
                        <option value="no">Tidak</option>
                        <option value="yes">Ya</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Daftar</button>
            </form>
            <p class="text-center">Sudah punya akun? <a href="index.jsp">Login di sini</a></p>
        </div>
    </body>
</html>
