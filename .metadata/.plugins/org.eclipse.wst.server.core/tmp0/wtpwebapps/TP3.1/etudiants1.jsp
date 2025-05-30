<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Gestion des Étudiants</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .form-container { background-color: #f9f9f9; padding: 15px; border-radius: 5px; margin-bottom: 20px; }
        .error { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <h1>Gestion des Étudiants</h1>
    
    <!-- Message d'erreur si présent -->
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    
    <!-- Formulaire d'ajout/modification -->
    <div class="form-container">
        <h3>Ajouter/Modifier un étudiant</h3>
        <form action="Controleur" method="post">
            <table>
                <tr>
                    <td>Code:</td>
                    <td><input type="text" name="code" value="${etud.code}" required /></td>
                </tr>
                <tr>
                    <td>Nom:</td>
                    <td><input type="text" name="nom" value="${etud.nom}" required /></td>
                </tr>
                <tr>
                    <td>Ville:</td>
                    <!-- ✅ CORRIGÉ : Guillemet fermé correctement -->
                    <td><input type="text" name="ville" value="${etud.ville}" required /></td>
                </tr>
                <tr>
                    <td>Age:</td>
                    <!-- ✅ CORRIGÉ : Guillemet fermé correctement -->
                    <td><input type="number" name="age" value="${etud.age}" required /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Ajouter" />
                        <input type="reset" value="Effacer" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
    
    <!-- Liste des étudiants -->
    <div>
        <h3>Liste des étudiants</h3>
        <table>
            <thead>
                <tr>
                    <th>CODE</th>
                    <th>NOM</th>
                    <th>VILLE</th>
                    <th>AGE</th>
                    <th>ACTIONS</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty all}">
                        <tr>
                            <td colspan="5" style="text-align: center; color: #666;">
                                Aucun étudiant trouvé
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${all}" var="e">
                            <tr>
                                <td><c:out value="${e.code}"/></td>
                                <td><c:out value="${e.nom}"/></td>
                                <td><c:out value="${e.ville}"/></td>
                                <td><c:out value="${e.age}"/></td>
                                <td>
                                    <a href="Controleur?action=supprimer&code=${e.code}" 
                                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet étudiant ?')">
                                       Supprimer
                                    </a>
                                    |
                                    <a href="Controleur?action=modifier&code=${e.code}">
                                       Modifier
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
    
    <!-- Lien pour rafraîchir -->
    <div style="margin-top: 20px;">
        <a href="Controleur">🔄 Rafraîchir la liste</a>
    </div>
</body>
</html>