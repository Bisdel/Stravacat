<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Liste abonnÃ©s</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="/src/main/webapp/WEB-INF/views/assets/css/style.css">
<script defer
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
	crossorigin="anonymous"></script>
</head>



<body>
	<div class="container">
		<h1>Listes des Abonnes</h1>
		<a href="abonne/ajouter" class="btn btn-success">ajouter un
			nouveau</a>
	</div>
	<c:if test="${ param.erreursup != null }">
		<div class="alert alert-danger">La suppression n'a pas
			fonctionné.</div>
	</c:if>


	<table class="table table-striped">
		<thead>
			<tr>
			    <th class="col-3">Id</th>
				<th class="col-3">Pseudo</th>
				<th class="col-3">age (annee)</th>
				<th class="col-3">Animal_id</th>
				<th class="col-3">Ville_nom</th>
				<th class="col-3">Espece</th>
				<th class="col-3">Action</th>
			</tr>
		</thead>

		<tbody>
		<c:forEach var="abonne" items = "${ abonnes }">
			<tr>
			    <td>${ abonne.id }</td>
				<td>${ abonne.pseudo }</td>
				<td>${ abonne.age }</td>
				<td>${ abonne.animal_id }</td>
				<td>${ abonne.ville_id }</td>
				<td>${ abonne.espece } </td>
				<td>
				<a href="abonne/modifier/${ abonne.id }"class="btn btn-outline-warning">Modifier</a>
				<a href="abonne/supprimer/${ fournisseur.id }" class="btn btn-outline-danger">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	</div>
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


</body>

</html>