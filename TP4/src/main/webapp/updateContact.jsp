<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head><title>Rechercher Contact</title></head>
<body>
<form action="ServletController" method="GET">
  <input type="hidden" name="do_this" value="search"/>
  <label>Nom ou prénom: <input name="term"/></label>
  <input type="submit" value="Search"/>
</form>
</body>
</html>