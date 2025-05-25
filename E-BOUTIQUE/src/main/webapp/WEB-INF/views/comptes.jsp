<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Comptes du Client - E-BOUTIQUE Banking</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .client-info {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            text-align: center;
        }
        .compte-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            margin-bottom: 15px;
            overflow: hidden;
        }
        .compte-header {
            background: #f8f9fa;
            padding: 15px;
            border-bottom: 1px solid #ddd;
        }
        .compte-body {
            padding: 15px;
        }
        .solde-positif { color: #28a745; font-weight: bold; }
        .solde-negatif { color: #dc3545; font-weight: bold; }
        .badge {
            padding: 4px 8px;
            border-radius: 4px;
            color: white;
            font-size: 0.8em;
            font-weight: bold;
        }
        .badge-courant { background-color: #007bff; }
        .badge-epargne { background-color: #28a745; }
    </style>
</head>
<body>
<div class="container">
    <h1>🏦 Système de Gestion Bancaire</h1>

    <!-- Informations du client -->
    <div class="client-info">
        <h2>👤 Comptes de ${client.nomClient}</h2>
        <p><strong>Code Client:</strong> ${client.codeClient}</p>
    </div>

    <!-- Liste des comptes -->
    <div class="panel">
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
            <h3>💳 Comptes bancaires (${listComptes.size()} compte(s))</h3>
            <a href="${pageContext.request.contextPath}/formCompte?codeClient=${client.codeClient}"
               class="btn" style="background-color: #28a745;">➕ Nouveau Compte</a>
        </div>

        <c:choose>
            <c:when test="${empty listComptes}">
                <div style="text-align: center; padding: 40px; color: #666;">
                    <h3>📭 Aucun compte trouvé</h3>
                    <p>Ce client n'a pas encore de compte bancaire.</p>
                    <a href="${pageContext.request.contextPath}/formCompte?codeClient=${client.codeClient}"
                       class="btn" style="background-color: #28a745;">➕ Créer le premier compte</a>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${listComptes}" var="compte">
                    <div class="compte-card">
                        <div class="compte-header">
                            <div style="display: flex; justify-content: space-between; align-items: center;">
                                <div>
                                    <h4>💳 Compte N° ${compte.numeroCompte}</h4>
                                    <c:choose>
                                        <c:when test="${compte['class'].simpleName == 'CompteCourant'}">
                                            <span class="badge badge-courant">💰 Compte Courant</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-epargne">🏦 Compte Épargne</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div style="text-align: right;">
                                    <div class="${compte.solde >= 0 ? 'solde-positif' : 'solde-negatif'}">
                                        <fmt:formatNumber value="${compte.solde}" type="currency" currencySymbol="€"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="compte-body">
                            <div style="display: grid; grid-template-columns: 1fr 1fr 1fr; gap: 20px;">
                                <div>
                                    <strong>📅 Date de création:</strong><br>
                                    <fmt:formatDate value="${compte.dateCreation}" pattern="dd/MM/yyyy"/>
                                </div>
                                <div>
                                    <strong>👨‍💼 Employé responsable:</strong><br>
                                        ${compte.employe.nomEmploye}
                                </div>
                                <div>
                                    <c:if test="${compte['class'].simpleName == 'CompteCourant'}">
                                        <strong>💳 Découvert autorisé:</strong><br>
                                        <fmt:formatNumber value="${compte.decouvert}" type="currency" currencySymbol="€"/>
                                    </c:if>
                                    <c:if test="${compte['class'].simpleName == 'CompteEpargne'}">
                                        <strong>📈 Taux d'intérêt:</strong><br>
                                        <fmt:formatNumber value="${compte.taux}" type="percent"/>
                                    </c:if>
                                </div>
                            </div>

                            <div style="margin-top: 15px; text-align: right;">
                                <a href="${pageContext.request.contextPath}/operations?numCompte=${compte.numeroCompte}"
                                   class="btn">📊 Voir Opérations</a>
                                <a href="${pageContext.request.contextPath}/formOperation?numCompte=${compte.numeroCompte}"
                                   class="btn" style="background-color: #ffc107; color: #212529;">💸 Nouvelle Opération</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Gestion des erreurs -->
    <c:if test="${not empty exception}">
        <div class="alert alert-danger">
            <strong>❌ Erreur!</strong>
            <p>${exception.message}</p>
        </div>
    </c:if>

    <!-- Navigation -->
    <div style="margin-top: 30px;">
        <a href="${pageContext.request.contextPath}/index" class="btn">🔙 Retour aux clients</a>
        <a href="${pageContext.request.contextPath}/index" class="btn">🏠 Accueil</a>
    </div>
</div>
</body>
</html>