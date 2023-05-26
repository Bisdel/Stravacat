<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<base href="/stravacat/">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Liste abonnements</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet" href="assets/css/style.css">
<script defer
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>
</head>

<body>
	<div class="container">
		<h1>Listes des abonnements</h1>
		<a href="abonnement/ajouter" class="btn btn-success">ajouter un
			nouveau</a>
	</div>
	<c:if test="${ param.erreursup != null }">
		<div class="alert alert-danger">La suppression n'a pas
			fonctionné.</div>
	</c:if>
	<div class="background-container">
	<div class="text-overlay">
		<alt ="Ma photo" class="image-with-border">
	</div>
</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th class="col-3">Pseudo</th>
				<th class="col-3">Age (annee)</th>
				<th class="col-3">Animal_id</th>
				<th class="col-3">Ville_nom</th>
				<th class="col-3">Espece</th>
				<th class="col-3">Action</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="abonnement" items="${ abonnements }">
				<tr>
					<td>${ abonnement.id }</td>
					<td>${ abonnement.pseudo }</td>
					<td>${ abonnement.age }</td>
					<td>${ abonnement.animal_id }</td>
					<td>${ abonnement.ville_id }</td>
					<td>${ abonnement.espece }</td>
					<td><a href="abonne/modifier/${ abonnement.id }"
						class="btn btn-outline-warning">Modifier</a> <a
						href="abonne/supprimer/${ abonnement.id }"
						class="btn btn-outline-danger">Supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<div>
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#">4</a></li>
			<a class="page-link" href="#" aria-label="Next"> <span
				aria-hidden="true">&raquo;</span>
			</a>
			</li>
		</ul>
	</div>
</body>





</html>