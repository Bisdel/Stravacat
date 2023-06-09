<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">

<head>
<base href ="/stravacat/">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Page Abonnements</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="assets/css/style.css">
<script defer
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>

</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary sticky-top">
		<div>
			<ul>
				<li><a class="nav-link" href="abonnement">Listes
						de mes abonnements</a></li>
			</ul>
		</div>
	</nav>
<!-- limage -->


<div>
	<div class="container">
		<p>Veuillez remplir le formulaire ci-dessous :</p>
		<form method="POST">
			<div class="mb-3 row">
				<label for="nom" class="col-sm-2 col-form-label">Pseudo</label>

				<div class="col-sm-10">
					<input type="text" class="form-control" id="abnm_pseudo"
						name="pseudo" placeholder="Saisir le pseudo  ">
				</div>
			</div>

			<div class="mb-3 row">
				<label for="responsable" class="col-sm-2 col-form-label">Age</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="abnm_age" name="age"
						placeholder="Saisir l'âge">
				</div>
			</div>

			<div class="mb-3 row">
				<label for="responsable" class="col-sm-2 col-form-label">L'id
					de l'animal</label>
				<div class="col-sm-10">
					<input type="numeber" class="form-control" id="abnm_animal_id"
						name="L'ID de l'animal" placeholder="ID de l'animal abonné">
				</div>
			</div>

			<div class="mb-3 row">
				<div class="col d-grid">
					<input type="submit" class="btn btn-success" value="Ajouter !">
				</div>
			</div>
		</form>
	</div>
	</body>
</html>