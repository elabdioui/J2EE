<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Opérations du Compte</title>
    <link rel="stylesheet" href="<c:url value='/src/main/resources/static/css/style.css'/>">
</head>
<body>
<div class="container">
    <h2>Opérations du compte : ${compte.numeroCompte}</h2>

    <div class="panel panel-info">
        <div class="panel-heading">Informations du compte</div>
        <div class="panel-body">
            <p><strong>Numéro :</strong> ${compte.numeroCompte}</p>
            <p><strong>Solde :</strong> <fmt:formatNumber value="${compte.solde}" type="currency"/></p>
            <p><strong>Date de création :</strong> <fmt:formatDate value="${compte.dateCreation}" pattern="dd/MM/yyyy"/></p>
            <p><strong>Client :</strong> ${compte.client.nomClient}</p>
        </div>
    </div>

    <div class="panel panel-primary">
        <div class="panel-heading">Liste des opérations</div>
        <div class="panel-body">
            <table class="table table-striped">
                <tr>
                    <th>Numéro</th>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Montant</th>
                    <th>Employé</th>
                </tr>
                <c:forEach items="${listOperations}" var="op">
                    <tr>
                        <td>${op.numeroOperation}</td>
                        <td><fmt:formatDate value="${op.dateOperation}" pattern="dd/MM/yyyy HH:mm"/></td>
                        <td>
                            <c:if test="${op['class'].simpleName=='Versement'}">
                                <span class="label label-success">VERSEMENT</span>
                            </c:if>
                            <c:if test="${op['class'].simpleName=='Retrait'}">
                                <span class="label label-danger">RETRAIT</span>
                            </c:if>
                        </td>
                        <td><fmt:formatNumber value="${op.montant}" type="currency"/></td>
                        <td>${op.employe.nomEmploye}</td>
                    </tr>
                </c:forEach>
            </table>

            <div>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:forEach items="${pages}" var="p" varStatus="s">
                            <li class="${s.index==pageCourante?'active':''}">
                                <a href="operations?numCompte=${compte.numeroCompte}&page=${s.index}&size=${size}">${s.index}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

    <a href="comptes?codeClient=${compte.client.codeClient}" class="btn btn-primary">Retour aux comptes</a>

    <c:if test="${exception != null}">
        <div class="alert alert-danger">
            <strong>Erreur!</strong> ${exception.message}
        </div>
    </c:if>
</div>
</body>
</html>