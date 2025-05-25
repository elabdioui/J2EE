<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Clients</title>
    <link rel="stylesheet" href="<c:url value='/src/main/resources/static/css/style.css'/>">
</head>
<body>
<div class="container">
    <h2>Gestion des Clients</h2>

    <div class="panel panel-primary">
        <div class="panel-heading">Rechercher les clients</div>
        <div class="panel-body">
            <form action="index" method="get">
                <label>Mot Clé:</label>
                <input type="text" name="chercher" value="${motCle}"/>
                <button type="submit" class="btn btn-primary">Chercher</button>
            </form>
        </div>
    </div>

    <div class="panel panel-primary">
        <div class="panel-heading">Liste des clients</div>
        <div class="panel-body">
            <table class="table table-striped">
                <tr>
                    <th>Code</th>
                    <th>Nom</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${listClients}" var="c">
                    <tr>
                        <td>${c.codeClient}</td>
                        <td>${c.nomClient}</td>
                        <td>
                            <a href="comptes?codeClient=${c.codeClient}" class="btn btn-success">Comptes</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:forEach items="${pages}" var="p" varStatus="s">
                            <li class="${s.index==pageCourante?'active':''}">
                                <a href="index?page=${s.index}&chercher=${motCle}&size=${size}">${s.index}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

    <c:if test="${exception != null}">
        <div class="alert alert-danger">
            <strong>Erreur!</strong> ${exception.message}
        </div>
    </c:if>
</div>
</body>
</html>