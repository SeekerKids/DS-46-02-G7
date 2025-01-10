<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Data Ads</title>
    <style>
        .ad-container {
            text-align: center;
            margin-top: 50px;
        }
        img {
            max-width: 100%;
            height: auto;
        }
    </style>
    <script>
        // Redirect after 3 seconds
        setTimeout(function() {
            window.location.href = "homepage.jsp";
        }, 3000);
    </script>
</head>
<body>
    <div class="ad-container">
        <%
            // Memanggil model Ads untuk mengambil semua ads
            models.Ads adsModel = new models.Ads();
            java.util.ArrayList<models.Ads> Adss = adsModel.getAll();

            if (Adss != null && !Adss.isEmpty()) {
                // Pilih satu elemen secara acak
                java.util.Random random = new java.util.Random();
                int randomIndex = random.nextInt(Adss.size());
                models.Ads randomAd = Adss.get(randomIndex);
        %>
        <img src="<%= randomAd.getContent()%>" alt="Ad Content">
        <%
        } else {
        %>
        <p>No ads available.</p>
        <%
            }
        %>

    </div>
</body>
</html>
