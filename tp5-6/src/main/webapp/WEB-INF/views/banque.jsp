<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Banque</title>
    <link rel="stylesheet" type="text/css" href="style1.css">
</head>
<body>

<!-- Formulaire pour chercher un compte -->
<div>
    <form:form modelAttribute="banqueForm" method="POST" action="${pageContext.request.contextPath}/chargerCompte">
        <table>
            <tr>
                <td>Code :</td>
                <td><form:input path="code"/></td>
                <td><form:errors path="code" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="3"><input type="submit" value="OK"/></td>
            </tr>
        </table>
    </form:form>
</div>

<!-- Affichage des informations du compte -->
<c:if test="${not empty banqueForm.compte}">
    <div>
        <table class="table1">
            <tr><td>Solde :</td><td>${banqueForm.compte.solde}</td></tr>
            <tr><td>Date Création :</td><td>${banqueForm.compte.dateCreation}</td></tr>
            <tr><td>Type de Compte :</td><td>${banqueForm.typeCompte}</td></tr>

            <c:if test="${banqueForm.typeCompte == 'CompteCourant'}">
                <tr><td>Découvert :</td><td>${banqueForm.compte.decouvert}</td></tr>
            </c:if>

            <c:if test="${banqueForm.typeCompte == 'CompteEpargne'}">
                <tr><td>Taux Intérêt :</td><td>${banqueForm.compte.tauxInteret}</td></tr>
            </c:if>
        </table>
    </div>

    <div>
        <table class="table1">
            <tr><td>Nom Client :</td><td>${banqueForm.compte.client.nomClient}</td></tr>
            <tr><td>Nom Employé :</td><td>${banqueForm.compte.employe.nomEmploye}</td></tr>
        </table>
    </div>
</c:if>
<form:form modelAttribute="banqueForm" action="/saveOperation" method="post" cssClass="bank-form">
    <!-- Champ caché pour l'identifiant -->
    <form:hidden path="code" />
    
    <div class="form-group">
        <h3>Type d'opération :</h3>
        <div class="radio-group">
            <label class="radio-label">
                <form:radiobutton path="typeOperation" value="VER" onchange="this.form.submit()" />
                Versement
            </label>
            <label class="radio-label">
                <form:radiobutton path="typeOperation" value="RET" onchange="this.form.submit()" />
                Retrait
            </label>
            <label class="radio-label">
                <form:radiobutton path="typeOperation" value="VIR" onchange="this.form.submit()" />
                Virement
            </label>
        </div>
        <form:errors path="typeOperation" cssClass="error" />
    </div>

    <c:if test="${not empty banqueForm.typeOperation}">
        <div class="form-group">
            <label>Montant :</label>
            <form:input path="montant" type="number" step="0.01" cssClass="form-control" />
            <form:errors path="montant" cssClass="error" />
        </div>

        <c:if test="${banqueForm.typeOperation == 'VIR'}">
            <div class="form-group">
                <label>Compte bénéficiaire :</label>
                <form:input path="code2" cssClass="form-control" />
                <form:errors path="code2" cssClass="error" />
            </div>
        </c:if>

        <div class="form-actions">
            <button type="submit" name="action" value="save" class="btn btn-primary">
                <i class="fas fa-save"></i> Enregistrer
            </button>
        </div>
    </c:if>
</form:form>

<div>
    <table class="table1">
        <tr>
            <th>Num</th>
            <th>Type</th>
            <th>Date</th>
            <th>Montant</th>
        </tr>
        <c:forEach items="${banqueForm.operations}" var="op">
            <tr>
                <td>${op.numeroOperation}</td>
                <td>${op}</td>
                <td>${op.dateOperation}</td>
                <td>${op.montant}</td>
            </tr>
        </c:forEach>
    </table>
</div>




<!-- Affichage des erreurs -->
<c:if test="${not empty banqueForm.exception}">
    <div class="error">
        Erreur : ${banqueForm.exception}
    </div>
</c:if>

</body>
</html>