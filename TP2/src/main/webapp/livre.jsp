<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>Bibliothèque</title> 
</head> 
<body> 
<form action="LivreController" method="get"> 
	<h1> Enregistrement d'un livre</h1> 
		<h2>Le livre </h2> 
		<table> 
			<tr> 
				<td> 
					Titre : 
				</td> 
					<td> 
					<input type = "text" name="titre"> 
					</td> 
			</tr> 
			<tr> 
			<td> 
					Catégorie : 
			</td> 
			<td> 
				<select name="categorie"> 
				<option value="Roman">Roman</option> 
				<option value="Policier">Policier</option> 
				<option value="Junior">Junior</option> 
				<option value="Philosophie">Philosophie</option> 
				<option value="Sciences-fiction">Sciences-fiction</option> 
		</select> 
			</td> 
				</tr> 
				<tr> 
			<td> 
				Le numéro ISBN : 
			</td> 
			<td> 
				<input type = "number" name="isbn"> 
			</td> 
				</tr> 
				</table> 
	<h2>L'auteur </h2> 
		<table style=" width : 378px;"> 
		<tr> 
			<td> 
				Nom : 
			</td> 
			<td> 
				<input type = "text" name="nom"> 
			</td> 
			</tr> 
			<tr> 
			<td> 
				Prènom : 
			</td> 
			<td> 
			<input type = "text" name="prenom"> 
			</td> 
		</tr> 
			<tr> 
			<td><input type= "submit" value ="valider"> 
		</tr> 
		</table> 
		</form> 
			</body> 
</html> 