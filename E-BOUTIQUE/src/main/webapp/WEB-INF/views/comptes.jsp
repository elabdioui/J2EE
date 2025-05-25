<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Comptes du Client</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>Comptes du client : ${client.nomClient}</h2>

    <div class="panel panel-primary">
        <div class="panel-heading">Liste des comptes</div>
        <div class="panel-body">
            <table class="table table-striped">
                <tr>
                    <th>Numéro</th>
                    <th>Date Création</th>
                    <th>Solde</th>
                    <th>Type</th>
                    <th>Employé</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${listComptes}" var="cp">
                    <tr>
                        <td>${cp.numeroCompte}</td>
                        <td><fmt:formatDate value="${cp.dateCreation}" pattern="dd/MM/yyyy"/></td>
                        <td><fmt:formatNumber value="${cp.solde}" type="currency"/></td>
                        <td>
                            <c:if test="${cp['class'].simpleName=='CompteCourant'}">
                                Compte Courant
                            </c:if>
                            <c:if test="${cp['class'].simpleName=='CompteEpargne'}">
                                Compte Epargne
                            </c:if>
                        </td>
                        <td>${cp.employe.nomEmploye}</td>
                        <td>
                            <a href="operations?numCompte=${cp.numeroCompte}" class="btn btn-success">Opérations</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <a href="index" class="btn btn-primary">Retour aux clients</a>

    <c:if test="${exception != null}">
        <div class="alert alert-danger">
            <strong>Erreur!</strong> ${exception.message}
        </div>
    </c:if>
</div>
</body>
</html>