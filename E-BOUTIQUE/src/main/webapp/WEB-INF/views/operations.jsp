<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Opérations du Compte - E-BOUTIQUE Banking</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .compte-info {
            background: linear-gradient(135deg, #74b9ff 0%, #0984e3 100%);
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        .operation-versement {
            background: #d4edda;
            border-left: 4px solid #28a745;
        }
        .operation-retrait {
            background: #f8d7da;
            border-left: 4px solid #dc3545;
        }
        .operation-row {
            padding: 10px;
            margin-bottom: 5px;
            border-radius: 4px;
        }
        .badge-operation {
            padding: 4px 8px;
            border-radius: 12px;
            color: white;
            font-size: 0.75em;
            font-weight: bold;
        }
        .badge-versement { background-color: #28a745; }
        .badge-retrait { background-color: #dc3545; }
        .montant-positif { color: #28a745; font-weight: bold; }
        .montant-negatif { color: #dc3545; font-weight: bold; }
    </style>
</head>
<body>
<div class="container">
    <h1>🏦 Système de Gestion Bancaire</h1>

    <!-- Informations du compte -->
    <div class="compte-info">
        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
            <div>
                <h2>💳 Compte N° ${compte.numeroCompte}</h2>
                <p><strong>👤 Client:</strong> ${compte.client.nomClient}</p>
                <p><strong>📅 Date de création:</strong>
                    <fmt:formatDate value="${compte.dateCreation}" pattern="dd/MM/yyyy"/>
                </p>
            </div>
            <div style="text-align: right;">
                <h3>💰 Solde actuel</h3>
                <div style="font-size: 1.5em; font-weight: bold;">
                    <fmt:formatNumber value="${compte.solde}" type="currency" currencySymbol="€"/>
                </div>
                <c:if test="${compte['class'].simpleName == 'CompteCourant'}">
                    <p><small>Découvert autorisé: <fmt:formatNumber value="${compte.decouvert}" type="currency" currencySymbol="€"/></small></p>
                </c:if>
            </div>
        </div>
    </div>

    <!-- Actions rapides -->
    <div style="text-align: center; margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/formOperation?numCompte=${compte.numeroCompte}"
           class="btn" style="background-color: #28a745;">💸 Nouvelle Opération</a>
    </div>

    <!-- Liste des opérations -->
    <div class="panel">
        <h3>📊 Historique des opérations (${totalOperations} opération(s))</h3>

        <c:choose>
            <c:when test="${empty listOperations}">
                <div style="text-align: center; padding: 40px; color: #666;">
                    <h4>📭 Aucune opération trouvée</h4>
                    <p>Ce compte n'a pas encore d'opérations.</p>
                    <a href="${pageContext.request.contextPath}/formOperation?numCompte=${compte.numeroCompte}"
                       class="btn" style="background-color: #28a745;">💸 Première Opération</a>
                </div>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>🆔 N° Opération</th>
                        <th>📅 Date & Heure</th>
                        <th>🏷️ Type</th>
                        <th>💰 Montant</th>
                        <th>👨‍💼 Employé</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listOperations}" var="operation">
                        <tr class="operation-row ${operation['class'].simpleName == 'Versement' ? 'operation-versement' : 'operation-retrait'}">
                            <td>${operation.numeroOperation}</td>
                            <td>
                                <fmt:formatDate value="${operation.dateOperation}" pattern="dd/MM/yyyy"/>
                                <br>
                                <small><fmt:formatDate value="${operation.dateOperation}" pattern="HH:mm:ss"/></small>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${operation['class'].simpleName == 'Versement'}">
                                        <span class="badge-operation badge-versement">⬆️ VERSEMENT</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge-operation badge-retrait">⬇️ RETRAIT</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${operation['class'].simpleName == 'Versement'}">
                                            <span class="montant-positif">
                                                +<fmt:formatNumber value="${operation.montant}" type="currency" currencySymbol="€"/>
                                            </span>
                                    </c:when>
                                    <c:otherwise>
                                            <span class="montant-negatif">
                                                -<fmt:formatNumber value="${operation.montant}" type="currency" currencySymbol="€"/>
                                            </span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${operation.employe.nomEmploye}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <!-- Pagination -->
                <c:if test="${totalPages > 1}">
                    <div class="pagination">
                        <p>Page ${currentPage + 1} sur ${totalPages}</p>

                        <c:if test="${currentPage > 0}">
                            <a href="${pageContext.request.contextPath}/operations?numCompte=${compte.numeroCompte}&page=0&size=${size}">⏮️ Première</a>
                            <a href="${pageContext.request.contextPath}/operations?numCompte=${compte.numeroCompte}&page=${currentPage - 1}&size=${size}">⬅️ Précédente</a>
                        </c:if>

                        <c:forEach begin="${currentPage > 2 ? currentPage - 2 : 0}"
                                   end="${currentPage + 2 < totalPages ? currentPage + 2 : totalPages - 1}"
                                   var="i">
                            <a href="${pageContext.request.contextPath}/operations?numCompte=${compte.numeroCompte}&page=${i}&size=${size}"
                               class="${i == currentPage ? 'active' : ''}">${i + 1}</a>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages - 1}">
                            <a href="${pageContext.request.contextPath}/operations?numCompte=${compte.numeroCompte}&page=${currentPage + 1}&size=${size}">➡️ Suivante</a>
                            <a href="${pageContext.request.contextPath}/operations?numCompte=${compte.numeroCompte}&page=${totalPages - 1}&size=${size}">⏭️ Dernière</a>
                        </c:if>
                    </div>
                </c:if>
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
        <a href="${pageContext.request.contextPath}/comptes?codeClient=${compte.client.codeClient}"
           class="btn">🔙 Retour aux comptes</a>
        <a href="${pageContext.request.contextPath}/index" class="btn">🏠 Accueil</a>
    </div>
</div>
</body>
</html>