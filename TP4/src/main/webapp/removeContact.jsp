<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Supprimer Contact</title></head>
<body>
<form action="ServletController" method="POST">
  <input type="hidden" name="do_this" value="delete"/>
  <label>ID Contact: <input name="contact_id"/></label>
  <input type="submit" value="Delete"/>
</form>
</body>
</html>