<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouveau Compte - E-BOUTIQUE Banking</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .form-container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .client-info {
            background: #e3f2fd;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
            border-left: 4px solid #2196f3;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 10px;
            border: 2px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }
        .form-group input:focus, .form-group select:focus {
            border-color: #007bff;
            outline: none;
        }
        .conditional-field {
            display: none;
            background: #f8f9fa;
            padding: 15px;
            border-radius: 5px;
            margin-top: 10px;
        }
        .btn-group {
            text-align: center;
            margin-top: 30px;
        }
        .required { color: #dc3545; }
    </style>
    <script>
        function toggleConditionalFields() {
            const typeCompte = document.getElementById('typeCompte').value;
            const decouvertField = document.getElementById('decouvertField');
            const tauxField = document.getElementById('tauxField');

            decouvertField.style.display = 'none';
            tauxField.style.display = 'none';

            if (typeCompte === 'CC') {
                decouvertField.style.display = 'block';
            } else if (typeCompte === 'CE') {
                tauxField.style.display = 'block';
            }
        }

        window.onload = function() {
            toggleConditionalFields();
        }
    </script>
</head>
<body>
<div class="container">
    <h1>🏦 Système de Gestion Bancaire</h1>

    <div class="form-container">
        <h2>➕ Création d'un nouveau compte</h2>

        <!-- Informations du client -->
        <div class="client-info">
            <h3>👤 Client sélectionné</h3>
            <p><strong>Code:</strong> ${client.codeClient}</p>
            <p><strong>Nom:</strong> ${client.nomClient}</p>
        </div>

        <!-- Formulaire -->
        <form action="${pageContext.request.contextPath}/saveCompte" method="post">
            <input type="hidden" name="codeClient" value="${client.codeClient}" />

            <div class="form-group">
                <label for="typeCompte">🏷️ Type de compte <span class="required">*</span></label>
                <select id="typeCompte" name="typeCompte" required onchange="toggleConditionalFields()">
                    <option value="">-- Sélectionnez le type --</option>
                    <option value="CC">💰 Compte Courant</option>
                    <option value="CE">🏦 Compte Épargne</option>
                </select>
            </div>

            <div class="form-group">
                <label for="solde">💰 Solde initial <span class="required">*</span></label>
                <input type="number" id="solde" name="solde" step="0.01" min="0"
                       placeholder="0.00" required />
                <small>Montant en euros (€)</small>
            </div>

            <!-- Champ conditionnel: Découvert pour Compte Courant -->
            <div id="decouvertField" class="conditional-field">
                <label for="decouvert">💳 Découvert autorisé</label>
                <input type="number" id="decouvert" name="decouvert" step="0.01" min="0"
                       placeholder="0.00" value="0" />
                <small>Montant maximum de découvert autorisé (€)</small>
            </div>

            <!-- Champ conditionnel: Taux pour Compte Épargne -->
            <div id="tauxField" class="conditional-field">
                <label for="taux">📈 Taux d'intérêt</label>
                <input type="number" id="taux" name="taux" step="0.01" min="0" max="100"
                       placeholder="2.5" value="0" />
                <small>Taux d'intérêt annuel en pourcentage (%)</small>
            </div>

            <div class="form-group">
                <label for="codeEmploye">👨‍💼 Employé responsable <span class="required">*</span></label>
                <select id="codeEmploye" name="codeEmploye" required>
                    <option value="">-- Sélectionnez un employé --</option>
                    <c:forEach items="${listEmployes}" var="employe">
                        <option value="${employe.codeEmploye}">${employe.nomEmploye}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn" style="background-color: #28a745;">
                    ✅ Créer le compte
                </button>
                <a href="${pageContext.request.contextPath}/comptes?codeClient=${client.codeClient}"
                   class="btn" style="background-color: #6c757d;">
                    ❌ Annuler
                </a>
            </div>
        </form>
    </div>

    <!-- Gestion des erreurs -->
    <c:if test="${not empty exception}">
        <div class="alert alert-danger">
            <strong>❌ Erreur!</strong>
            <p>${exception.message}</p>
        </div>
    </c:if>

    <!-- Navigation -->
    <div style="margin-top: 30px; text-align: center;">
        <a href="${pageContext.request.contextPath}/comptes?codeClient=${client.codeClient}"
           class="btn">🔙 Retour aux comptes</a>
        <a href="${pageContext.request.contextPath}/index" class="btn">🏠 Accueil</a>
    </div>
</div>
</body>
</html>