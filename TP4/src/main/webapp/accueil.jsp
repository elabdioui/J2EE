<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Accueil</title></head>
<body>
<h1>Liste des contacts :</h1>
<table border="1">
  <tr>
    <th>Contact Id</th><th>First Name</th><th>Last Name</th>
    <th>Email</th><th>Phone</th><th>Address</th>
  </tr>
  <c:forEach var="contact" items="${requestScope.listContacts}">
    <tr>
      <td>${contact.idContact}</td>
      <td>${contact.firstname}</td>
		<td>${contact.lastname}</td>
      <td>${contact.email}</td>
      <td>${contact.phone}</td>
      <td>${contact.address}</td>
    </tr>
  </c:forEach>
</table>
<p>
  <a href="ServletController?do_this=create">Créer un nouveau contact</a> |
  <a href="ServletController?do_this=delete">Supprimer un contact</a> |
  <a href="ServletController?do_this=update">Modifier un contact</a> |
  <a href="ServletController?do_this=search">Rechercher un contact</a>
</p>
</body>
</html>