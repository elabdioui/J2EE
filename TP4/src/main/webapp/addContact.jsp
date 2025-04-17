<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head><title>Ajouter Contact</title></head>
<body>
<form action="ServletController" method="POST">
  <input type="hidden" name="do_this" value="create"/>
  <table>
    <tr><td>First Name:</td><td><input name="firstName"/></td></tr>
    <tr><td>Last Name:</td><td><input name="lastName"/></td></tr>
    <tr><td>Email:</td><td><input name="email"/></td></tr>
    <tr><td>Phone:</td><td><input name="phone"/></td></tr>
    <tr><td>Address:</td><td><input name="address"/></td></tr>
    <tr><td colspan="2"><input type="submit" value="Save"/></td></tr>
  </table>
</form>
</body>
</html>