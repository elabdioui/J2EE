<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouvelle Opération - E-BOUTIQUE Banking</title>
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
        .compte-info {
            background: linear-gradient(135deg, #74b9ff 0%, #0984e3 100%);
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
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
        .operation-type {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
            margin-bottom: 20px;
        }
        .operation-option {
            padding: 20px;
            border: 2px solid #ddd;
            border-radius: 10px;
            text-align: center;
            cursor: pointer;
            transition: all 0.3s;
        }
        .operation-option:hover {
            border-color: #007bff;
            background-color: #f8f9fa;
        }
        .operation-option.selected {
            border-color: #007bff;
            background-color: #e3f2fd;
        }
        .operation-option input[type="radio"] {
            display: none;
        }
        .btn-group {
            text-align: center;
            margin-top: 30px;
        }
        .required { color: #dc3545; }
        .warning {
            background: #fff3cd;
            border: 1px solid #ffeaa7;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
    </style>
    <script>
        function selectOperationType(type) {
            // Déselectionner toutes les options
            document.querySelectorAll('.operation-option').forEach(option => {
                option.classList.remove('selected');
            });

            // Sélectionner l'option cliquée
            document.getElementById(type + 'Option').classList.add('selected');
            document.getElementById('typeOperation').value = type;

            // Mettre à jour l'affichage
            updateOperationDisplay(type);
        }

        function updateOperationDisplay(type) {
            const warningDiv = document.getElementById('warningMessage');
            const submitBtn = document.getElementById('submitBtn');

            if (type === 'VERS') {
                warningDiv.innerHTML = '<strong>💰 Versement:</strong> Cette opération va ajouter le montant au solde du compte.';
                warningDiv.style.backgroundColor = '#d4edda';
                warningDiv.style.borderColor = '#c3e6cb';
                submitBtn.textContent = '💰 Effectuer le versement';
                submitBtn.style.backgroundColor = '#28a745';
            } else if (type === 'RET') {
                warningDiv.innerHTML = '<strong>💸 Retrait:</strong> Cette opération va déduire le montant du solde du compte. Vérifiez que le solde est suffisant.';
                warningDiv.style.backgroundColor = '#f8d7da';
                warningDiv.style.borderColor = '#f5c6cb';
                submitBtn.textContent = '💸 Effectuer le retrait';
                submitBtn.style.backgroundColor = '#dc3545';
            }
        }

        function validateForm() {
            const typeOperation = document.getElementById('typeOperation').value;
            if (!typeOperation) {
                alert('Veuillez sélectionner un type d\'opération');
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="container">
    <h1>🏦 Système de Gestion Bancaire</h1>

    <div class="form-container">
        <h2>💸 Nouvelle opération bancaire</h2>

        <!-- Informations du compte -->
        <div class="compte-info">
            <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px;">
                <div>
                    <h3>💳 Compte N° ${compte.numeroCompte}</h3>
                    <p><strong>👤 Client:</strong> ${compte.client.nomClient}</p>
                    <p><strong>📅 Date création:</strong>
                        <fmt:formatDate value="${compte.dateCreation}" pattern="dd/MM/yyyy"/>
                    </p>
                </div>
                <div style="text-align: right;">
                    <h4>💰 Solde actuel</h4>
                    <div style="font-size: 1.3em; font-weight: bold;">
                        <fmt:formatNumber value="${compte.solde}" type="currency" currencySymbol="€"/>
                    </div>
                    <c:if test="${compte['class'].simpleName == 'CompteCourant'}">
                        <p><small>Découvert: <fmt:formatNumber value="${compte.decouvert}" type="currency" currencySymbol="€"/></small></p>
                    </c:if>
                </div>
            </div>
        </div>

        <!-- Formulaire -->
        <form action="${pageContext.request.contextPath}/saveOperation" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="numCompte" value="${compte.numeroCompte}" />
            <input type="hidden" id="typeOperation" name="typeOperation" value="" />

            <!-- Sélection du type d'opération -->
            <div class="form-group">
                <label>🏷️ Type d'opération <span class="required">*</span></label>
                <div class="operation-type">
                    <div id="VERSOption" class="operation-option" onclick="selectOperationType('VERS')">
                        <div style="font-size: 2em; margin-bottom: 10px;">💰</div>
                        <h4>VERSEMENT</h4>
                        <p>Ajouter de l'argent au compte</p>
                        <input type="radio" name="typeOperationRadio" value="VERS" />
                    </div>
                    <div id="RETOption" class="operation-option" onclick="selectOperationType('RET')">
                        <div style="font-size: 2em; margin-bottom: 10px;">💸</div>
                        <h4>RETRAIT</h4>
                        <p>Retirer de l'argent du compte</p>
                        <input type="radio" name="typeOperationRadio" value="RET" />
                    </div>
                </div>
            </div>

            <!-- Message d'avertissement -->
            <div id="warningMessage" class="warning" style="display: none;">
                Sélectionnez d'abord un type d'opération.
            </div>

            <div class="form-group">
                <label for="montant">💰 Montant <span class="required">*</span></label>
                <input type="number" id="montant" name="montant" step="0.01" min="0.01"
                       placeholder="0.00" required />
                <small>Montant en euros (€)</small>
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
                <button type="submit" id="submitBtn" class="btn" style="display: none;">
                    ✅ Effectuer l'opération
                </button>
                <a href="${pageContext.request.contextPath}/operations?numCompte=${compte.numeroCompte}"
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
        <a href="${pageContext.request.contextPath}/operations?numCompte=${compte.numeroCompte}"
           class="btn">🔙 Retour aux opérations</a>
        <a href="${pageContext.request.contextPath}/index" class="btn">🏠 Accueil</a>
    </div>
</div>

<script>
    // Afficher le message d'avertissement au chargement
    document.getElementById('warningMessage').style.display = 'block';

    // Fonction pour afficher/masquer le bouton de soumission
    function updateSubmitButton() {
        const typeOperation = document.getElementById('typeOperation').value;
        const submitBtn = document.getElementById('submitBtn');
        const warningDiv = document.getElementById('warningMessage');

        if (typeOperation) {
            submitBtn.style.display = 'inline-block';
            warningDiv.style.display = 'block';
        } else {
            submitBtn.style.display = 'none';
            warningDiv.style.display = 'block';
            warningDiv.innerHTML = 'Sélectionnez d\'abord un type d\'opération.';
            warningDiv.style.backgroundColor = '#fff3cd';
            warningDiv.style.borderColor = '#ffeaa7';
        }
    }
</script>
</body>
</html>