<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859
1"> 
<title>Insert title here</title> 
</head> 
<body> 
<div> 
<form action="Controleur" method="post"> 
Code: <input type="text" name="code" value="${etud.code}" /><br/> 
Nom: <input type="text" name="nom" value="${etud.nom}" /><br/> 
Ville:  <input type="text" name="ville" value="${etud.ville}"/><br/> 
Age:  <input type="text" name="age" value="${etud.age}"/>"<br/> 
<input type="submit" value="Ajouter"/><br/> 
</form> 
</div> 
<div> 
<table border="1"> 
<tr><th>CODE</th><th>NOM</th><th>VILLE</th><th>AGE</th></tr> 
<c:forEach items="${all}" var="e"> 
<tr> 
<td><c:out value="${e.code}"/></td> 
<td><c:out value="${e.nom}"/></td> 
<td><c:out value="${e.ville}"/></td> 
<td><c:out value="${e.age}"/></td> 
<td><a 
href="Controleur?action=supprimer&code=${e.code}">supprimer</a></td> 
<td><a 
href="Controleur?action=modifier&code=${e.code}">modifier</a></td> 
</tr> 
</c:forEach> 
</table> 
</div> 
</body> 
</html>