<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fil d'actualités Stravacat</title>

    <base href="/stravacat/">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
</head>

<body>
    <div class="container">
        <nav class="navbar navbar-expand-lg bg-body-tertiary"></nav>
            <div class="container-fluid">
                <a class="navbar-brand" href="#"><strong>STRAVACAT</strong>
                    <div class="spinner-border spinner-border-sm" role="status">
                        <span class="visually-hidden">Loading...</span>
                    </div>
                    <div class="spinner-grow spinner-grow-sm" role="status">
                        <span class="visually-hidden">Loading...</span>
                    </div>
                </a>
                <a class="navbar-brand" href="#">
                    <img src="assets\images\logo_stravacat.png"  alt="Stravacat" height="75">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item"><a class="nav-link" href="parcours">Accueil</a></li>
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="actualite">Fil d'actualités</a></li>
                        <li class="nav-item"><a class="nav-link" href="animal">Mon profil</a></li>
                        <li class="nav-item"><a class="nav-link" href="abonnement">Mes amis</a></li>
                        <li class="nav-item"><a class="nav-link" href="abonne">Mes followers</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>

    <p><h4 style="text-align:center">Fil d'actualités</h4></p>

    <div class="container">
        <table class="table table-striped">
            <thead class="table-dark">
                <tr>
                    <th>Pseudo</th>
                    <th>Description</th>
                    <th>Ville</th>
                    <th></th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="actualite" items="${ actualites }">
                    <tr class="align-middle">
                        <td>${ actualite.animal.pseudo }</td>
                        <td>${ actualite.actu_description }</td>
                        <td>${ actualite.ville.nom }</td>
                        <td>
                            <a href="actualite/modifier/${ actualite.actu_id }" class="btn btn-outline-warning">Modifier</a>
                            <a href="actualite/supprimer/${ actualite.actu_id }" class="btn btn-outline-danger">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>

<div class="container">
    <footer class="d-flex flex-wrap justify-content-between align-items-center p-2 my-4 border-top">
        <p class="col-md-4 mb-0 text-body-secondary">© 2023 Stravacat, Inc</p>

        <a href="/"
            class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
            <img src="assets\images\logo_stravacat.png" alt="Stravacat" height="50">
        </a>

        <ul class="nav col-md-4 justify-content-end">
            <li class="nav-item"><a href="#" class="nav-link px-2 text-body-secondary">Accueil</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-body-secondary">Mon profil</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-body-secondary">Mes amis</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-body-secondary">Actualités</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-body-secondary">A propos</a></li>
        </ul>
    </footer>
</div>

</html>