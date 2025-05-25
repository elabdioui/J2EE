<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Clients - E-BOUTIQUE Banking</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .search-panel {
            background: #f8f9fa;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #dee2e6;
        }
        .stats {
            background: #e3f2fd;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
            border-left: 4px solid #2196f3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>🏦 Système de Gestion Bancaire</h1>
    <h2>📋 Liste des Clients</h2>

    <!-- Panel de recherche -->
    <div class="search-panel">
        <h3>🔍 Rechercher des clients</h3>
        <form action="${pageContext.request.contextPath}/index" method="get">
            <div style="display: flex; gap: 10px; align-items: end;">
                <div style="flex: 1;">
                    <label for="chercher">Mot clé (nom du client):</label>
                    <input type="text" id="chercher" name="chercher" value="${motCle}"
                           placeholder="Entrez le nom du client..." />
                </div>
                <div>
                    <label for="size">Éléments par page:</label>
                    <select id="size" name="size">
                        <option value="3" ${size == 3 ? 'selected' : ''}>3</option>
                        <option value="5" ${size == 5 ? 'selected' : ''}>5</option>
                        <option value="10" ${size == 10 ? 'selected' : ''}>10</option>
                    </select>
                </div>
                <div>
                    <button type="submit" class="btn">🔍 Rechercher</button>
                </div>
            </div>
        </form>
    </div>

    <!-- Statistiques -->
    <div class="stats">
        <p><strong>📊 Statistiques:</strong>
            ${totalClients} client(s) trouvé(s)
            <c:if test="${not empty motCle}"> pour "${motCle}"</c:if>
        </p>
    </div>

    <!-- Liste des clients -->
    <div class="panel">
        <table>
            <thead>
            <tr>
                <th>🆔 Code Client</th>
                <th>👤 Nom du Client</th>
                <th>🏦 Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${empty listClients}">
                    <tr>
                        <td colspan="3" style="text-align: center; color: #666;">
                            <em>Aucun client trouvé</em>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${listClients}" var="client">
                        <tr>
                            <td>${client.codeClient}</td>
                            <td>${client.nomClient}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/comptes?codeClient=${client.codeClient}"
                                   class="btn">💳 Voir Comptes</a>
                                <a href="${pageContext.request.contextPath}/formCompte?codeClient=${client.codeClient}"
                                   class="btn" style="background-color: #28a745;">➕ Nouveau Compte</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

        <!-- Pagination -->
        <c:if test="${totalPages > 1}">
            <div class="pagination">
                <p>Page ${currentPage + 1} sur ${totalPages}</p>

                <!-- Première page -->
                <c:if test="${currentPage > 0}">
                    <a href="${pageContext.request.contextPath}/index?page=0&chercher=${motCle}&size=${size}">⏮️ Première</a>
                    <a href="${pageContext.request.contextPath}/index?page=${currentPage - 1}&chercher=${motCle}&size=${size}">⬅️ Précédente</a>
                </c:if>

                <!-- Pages autour de la page courante -->
                <c:forEach begin="${currentPage > 2 ? currentPage - 2 : 0}"
                           end="${currentPage + 2 < totalPages ? currentPage + 2 : totalPages - 1}"
                           var="i">
                    <a href="${pageContext.request.contextPath}/index?page=${i}&chercher=${motCle}&size=${size}"
                       class="${i == currentPage ? 'active' : ''}">${i + 1}</a>
                </c:forEach>

                <!-- Dernière page -->
                <c:if test="${currentPage < totalPages - 1}">
                    <a href="${pageContext.request.contextPath}/index?page=${currentPage + 1}&chercher=${motCle}&size=${size}">➡️ Suivante</a>
                    <a href="${pageContext.request.contextPath}/index?page=${totalPages - 1}&chercher=${motCle}&size=${size}">⏭️ Dernière</a>
                </c:if>
            </div>
        </c:if>
    </div>

    <!-- Gestion des erreurs -->
    <c:if test="${not empty exception}">
        <div class="alert alert-danger">
            <strong>❌ Erreur!</strong>
            <p>${exception.message}</p>
            <details>
                <summary>Détails techniques</summary>
                <pre>${exception}</pre>
            </details>
        </div>
    </c:if>

    <!-- Navigation -->
    <div style="margin-top: 30px; text-align: center;">
        <p>
            <strong>🧭 Navigation:</strong>
            <a href="${pageContext.request.contextPath}/index" class="btn">🏠 Accueil</a>
            <a href="${pageContext.request.contextPath}/employes" class="btn">👥 Employés</a>
            <a href="${pageContext.request.contextPath}/groupes" class="btn">🏢 Groupes</a>
        </p>
    </div>
</div>
</body>
</html>