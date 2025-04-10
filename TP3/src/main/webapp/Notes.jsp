<%@ page language="java" %> 
<%@ page import="form.beans.*" %> 
<%@ page import="java.util.*" %> 
<%@ page import="mod.sco.*" %> 
<% 
NoteForm nf; 
if(session.getAttribute("nf")==null){ 
 nf=new NoteForm(); 
} 
else{ 
 nf=(NoteForm)session.getAttribute("nf"); 
 } 
Iterator it=nf.getLesNotes().iterator(); 
float somme=0; 
int compteur=0; 
%> 
<html> 
<head> 
<title>Gestion des notes</title> 
</head> 
<body bgcolor="#FFFFFF"> 
<form method='post' action='ControleurServelet'> 
Num Inscription :<input type='text' name='numIns'> 
<input type='submit' value='OK' name='action'> 
</form> 
<table border='1' width='80%'> 
<tr> 
<td>Matière</td><td>Note</td> 
</tr> 
<% 
while(it.hasNext()){ 
Note n=(Note)it.next(); 
somme=somme+n.getNote(); 
++compteur; 
%> 
<tr> 
<td><%=n.getMatiere()%></td> 
<td><%=n.getNote()%></td> 
</tr> 
<% } %> 
<tr> 
<td>Moyenne</td> 
<td><%=somme/compteur%></td> 
</tr> 
</table> 
</body> 
</html>