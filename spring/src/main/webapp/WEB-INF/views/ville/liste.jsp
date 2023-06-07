<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="/stravacat/">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Liste des villes</title>
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
		<h1>Listes des villes</h1>
		<div class="background-container">
	<div class="text-overlay">
		<alt ="Ma photo" class="image-with-border">
	</div>
</div>
		<a href="ville/ajouter" class="btn btn-success">ajouter une
			nouvelle ville</a>
	</div>
	<c:if test="${ param.erreursup != null }">
		<div class="alert alert-danger">La suppression n'a pas
			fonctionné.</div>
	</c:if>
	

</div>
<table class="table table-striped">
		<thead>
			<tr>
				<th class="col-3">Id</th>
				<th class="col-3">Nom</th>
				<th class="col-3">Ambiance</th>
				<th class="col-3">Action</th>
			</tr>
		</thead>
	<tbody>
				<c:forEach var="ville" items="${ villes }">
				<tr>
					<td>${ ville.id }</td>
					<td>${ ville.nom }</td>
					<td>${ ville.ambiance }</td>
					<td><a href="ville/modifier/${ ville.id }"
						class="btn btn-outline-warning">Modifier</a> <a
						href="ville/supprimer/${ ville.id }"
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