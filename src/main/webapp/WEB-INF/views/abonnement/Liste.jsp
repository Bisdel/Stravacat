<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Liste abonnements</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
  <link rel="stylesheet" href="/src/main/webapp/WEB-INF/views/assets/css/style.css">
  <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>

</head>

<body>
  <div class="container">
    <h1>Listes des abonnements </h1>
    <a href="ajouter-abonnements.html" class="btn btn-success">ajouter un nouveau</a>
  </div>


  <table class="table table-striped">
    <thead>
      <tr>
        <th class="col-3">Pseudo</th>
        <th class="col-3">Ãge (annÃ©e)</th>
        <th class="col-3">Animal_id</th>
        <th class="col-3">Ville_nom</th>
        <th class="col-3">Espece</th>
        <th class="col-3">Action</th>
      </tr>
    </thead>

    <tbody>
      <tr>
        <td>2</td>
        <td>1</td>
        <td>2</td>
        <td>1</td>
        <td>canidÃ©</td>
        <td>
        </td>
      </tr>
      </tr>
    </tbody>
  </table>

  </div>
  <div class="pagination-container">
    <ul class="pagination">
      <li class="page-item">
        <a class="page-link" href="#" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li class="page-item"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item"><a class="page-link" href="#">4</a></li>
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
      </li>
    </ul>
  </div>
</body>





</html>