<%@ page contentType="text/html; charset=UTF-8" language="java" import="project.model.Imc" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de Bord - IMC</title>
</head>
<body>
    <h1>Bienvenue sur votre Tableau de Bord</h1>

    <%
        // Récupération de l'objet IMC depuis la session
        Imc imc = (Imc) session.getAttribute("imc");
        
        if (imc != null) {
    %>
        <p><strong>Poids : </strong><%= imc.getPoids() %> kg</p>
        <p><strong>Taille : </strong><%= imc.getTaille() %> cm</p>
        <p><strong>IMC : </strong><%= imc.calcul() %></p>
    <% 
        } else {
    %>
        <p>Aucune donnée disponible. Veuillez entrer vos informations.</p>
    <% 
        }
    %>

    <a href="NewFile.html">Retour à la page d'accueil</a>
</body>
</html>
