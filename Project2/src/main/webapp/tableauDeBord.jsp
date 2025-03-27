<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de Bord - IMC</title>
</head>
<body>
    <h1>Bienvenue sur votre Tableau de Bord</h1>
    
    <%
        Cookie[] cookies = request.getCookies();
String poids = null, taille = null, imc = null;

if (cookies != null) {
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("poids")) {
            poids = cookie.getValue();
        } else if (cookie.getName().equals("taille")) {
            taille = cookie.getValue();
        } else if (cookie.getName().equals("imc")) {
            imc = cookie.getValue();
        }
    }
}

        
        if (poids != null && taille != null && imc != null) {
    %>
        <p><strong>Poids : </strong><%= poids %> kg</p>
        <p><strong>Taille : </strong><%= taille %> cm</p>
        <p><strong>IMC : </strong><%= imc %></p>
    <% 
        } else {
    %>
        <p>Aucune donnée disponible. Veuillez entrer vos informations.</p>
    <% 
        }
    %>

    <a href="index.html">Retour à la page d'accueil</a>
</body>
</html>
