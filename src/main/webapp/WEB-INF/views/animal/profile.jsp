<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="fr">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Mon profil 🐱 Stravacat</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
                crossorigin="anonymous">
            <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
                crossorigin="anonymous"></script>
            <base href="/stravacat/">
            <!-- <base href="../../"> for testing using LiveServer-->
            <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.3/dist/leaflet.css"
                integrity="sha256-kLaT2GOSpHechhsozzB+flnD+zUyjE2LlfWPgU04xyI=" crossorigin="" />
            <!-- Make sure you put this AFTER Leaflet's CSS -->
            <script defer src="https://unpkg.com/leaflet@1.9.3/dist/leaflet.js"
                integrity="sha256-WBkoXOwTeyKclOHuWtc+i2uENFpDZ9YPdf5Hf+D7ewM=" crossorigin=""></script>
            <script defer src="assets\js\map.js"></script>
            <script defer src="assets\js\tooltips.js"></script>

        </head>

        <body>
            <div class="container">
                <header class="d-flex flex-wrap align-items-center py-3 mb-4 border-bottom">
                    <a href="/"
                        class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">

                        <img src="assets\images\logo_stravacat.png" alt="Stravacat" height="60">
                    </a>
                    <ul class="nav nav-pills">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="profile.html">Mon
                                profil</a>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="amis.html">Mes amis</a></li>
                        <li class="nav-item"><a class="nav-link" href="actualites.html">Actualités</a></li>
                        <li class="nav-item"><a class="nav-link" href="apropos.html">A propos</a></li>
                    </ul>
                </header>
            </div>
            <div class="container text-center">
                <c:if test="${ failed }">
                    <div class="alert alert-danger">
                        <p>${message}</p>
                        <ul>
                            <c:forEach var="err" items="${ erreurs.allErrors }">
                                <li>${ err.objectName } ${ err.defaultMessage }</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${ success }">
                    <div class="alert alert-success">
                        <p>${message}</p>
                    </div>
                </c:if>
                <div class="row m-5 align-items-center justify-content-around">
                    <div class="col">
                        <a href="modifierphotoprofil.html" data-bs-toggle="tooltip" data-bs-offset="0,70"
                            data-bs-title="Modifier mon avatar">
                            <img class="border border-primary border-5 rounded-4"
                                src="assets\images\example_profile_picture.jpg" alt="Photo du profil" height="150"
                                style="border-radius: 10%;"></a>
                    </div>
                    <div class="col">
                        <h4>Meow 👋 ! Je m'appelle ${animal.pseudo} et je suis un ${animal.espece} de ${animal.age} ans.
                        </h4>
                    </div>
                    <div class="col" style="line-height: 1;">
                        <a href="#" data-bs-toggle="modal" data-bs-target="#exampleModal"
                            style="color: grey; font-size: small; text-decoration: underline;">Modifier
                            ces<br>informations</a>
                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                            aria-hidden="true">
                            <form method="post" class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">Modifier mes informations
                                        </h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="p-4 p-md-5 border rounded-3 bg-body-tertiary">
                                            <p>Remplis les champs que tu souhaites modifier et laisse les autres vides,
                                                puis clique sur Enregistrer 😸</p>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="floatingInput" name="pseudo"
                                                    value="${ animal.pseudo }">
                                                <label for="floatingInput">Ton pseudo</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="email" class="form-control" id="floatingInput" name="email"
                                                    value="${ animal.email }">
                                                <label for="floatingInput">Adresse email</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="password" class="form-control" id="floatingPassword"
                                                    name="password">
                                                <label for="floatingPassword">Mot de passe secret</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="number" class="form-control" id="floatingInput" name="age"
                                                    value="${ animal.age }">
                                                <label for="floatingInput">Ton âge</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="floatingInput" name="espece"
                                                    value="${ animal.espece }">
                                                <label for="floatingInput">Ton animal</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="floatingInput"
                                                    name="ville.nom" value="${ animal.ville.nom }">
                                                <label for="floatingInput">Ta ville</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer justify-content-between">
                                        <button type="button" class="btn btn-danger">Supprimer mon compte</button>
                                        <button type="button" class="btn btn-secondary"
                                        data-bs-dismiss="modal">Annuler</button>
                                        <button type="submit" class="btn btn-primary" data-bs-dismiss="modal" id="liveToastBtn">Enregistrer</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row mb-5 align-items-center justify-content-around">
                    <div class="col">
                        <div class="card" style="height: 120px;">
                            <div class="card-header">
                                <strong>Rechercher une actualité</strong>
                            </div>
                            <div class="card-body">
                                <div class="input-group flex-nowrap">
                                    <span class="input-group-text" id="addon-wrapping"><svg
                                            xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            fill="currentColor" class="bi bi-search text-primary" viewBox="0 0 16 16">
                                            <path
                                                d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                                        </svg></span>
                                    <input type="text" class="form-control" placeholder="Rechercher..."
                                        aria-label="Rechercher" aria-describedby="addon-wrapping">
                                </div>
                            </div>
                        </div>
                        <div class="card" style="height: 260px;margin-top: 20px;">
                            <div class="card-header">
                                <strong>Amis en commun</strong>
                            </div>
                            <div class="card-body carousel slide" id="carouselExample">
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <div class="row mb-3 justify-content-around">
                                            <img class="border border-secondary-subtle border-3 rounded-4 px-0"
                                                data-bs-toggle="tooltip" data-bs-offset="0,10" data-bs-title="Michel"
                                                src="assets\images\profile_picture_index_1.png"
                                                style="height: 80px; width: 80px; object-fit: cover;" alt="...">
                                            <img class="border border-secondary-subtle border-3 rounded-4 px-0"
                                                data-bs-toggle="tooltip" data-bs-offset="0,10" data-bs-title="Thomas"
                                                src="assets\images\profile_picture_index_2.png"
                                                style="height: 80px; width: 80px; object-fit: cover;" alt="...">
                                            <img class="border border-secondary-subtle border-3 rounded-4 px-0"
                                                data-bs-toggle="tooltip" data-bs-offset="0,10" data-bs-title="Sarah"
                                                src="assets\images\profile_picture_index_3.png"
                                                style="height: 80px; width: 80px; object-fit: cover;" alt="...">
                                        </div>
                                        <div class="row mb-3 justify-content-around">
                                            <img class="border border-secondary-subtle border-3 rounded-4 px-0"
                                                data-bs-toggle="tooltip" data-bs-offset="0,10" data-bs-title="Grisette"
                                                src="assets\images\profile_picture_index_4.png"
                                                style="height: 80px; width: 80px; object-fit: cover;" alt="...">
                                            <img class="border border-secondary-subtle border-3 rounded-4 px-0"
                                                data-bs-toggle="tooltip" data-bs-offset="0,10" data-bs-title="Doug"
                                                src="assets\images\profile_picture_index_5.png"
                                                style="height: 80px; width: 80px; object-fit: cover;" alt="...">
                                            <img class="border border-secondary-subtle border-3 rounded-4 px-0"
                                                data-bs-toggle="tooltip" data-bs-offset="0,10" data-bs-title="Micheline"
                                                src="assets\images\profile_picture_index_6.png"
                                                style="height: 80px; width: 80px; object-fit: cover;" alt="...">
                                        </div>
                                    </div>
                                </div>
                                <div class="carousel-item">
                                    <div class="row mb-3 justify-content-around">
                                        <img class="border border-secondary-subtle border-3 rounded-4 px-0"
                                            data-bs-toggle="tooltip" data-bs-offset="0,10" data-bs-title="Tito"
                                            src="assets\images\profile_picture_index_1.png"
                                            style="height: 80px; width: 80px; object-fit: cover;" alt="...">
                                        <img class="border border-secondary-subtle border-3 rounded-4 px-0"
                                            data-bs-toggle="tooltip" data-bs-offset="0,10" data-bs-title="Samba"
                                            src="assets\images\profile_picture_index_2.png"
                                            style="height: 80px; width: 80px; object-fit: cover;" alt="...">
                                        <img class="border border-secondary-subtle border-3 rounded-4 px-0"
                                            data-bs-toggle="tooltip" data-bs-offset="0,10" data-bs-title="Minouche"
                                            src="assets\images\profile_picture_index_3.png"
                                            style="height: 80px; width: 80px; object-fit: cover;" alt="...">
                                    </div>
                                    <div class="row mb-3 justify-content-around">
                                        <img class="border border-secondary-subtle border-3 rounded-4 px-0"
                                            data-bs-toggle="tooltip" data-bs-offset="0,10" data-bs-title="Le rouquin"
                                            src="assets\images\profile_picture_index_4.png"
                                            style="height: 80px; width: 80px; object-fit: cover;" alt="...">
                                        <img class="border border-secondary-subtle border-3 rounded-4 px-0"
                                            data-bs-toggle="tooltip" data-bs-offset="0,10" data-bs-title="Charabia"
                                            src="assets\images\profile_picture_index_5.png"
                                            style="height: 80px; width: 80px; object-fit: cover;" alt="...">
                                        <img class="border border-secondary-subtle border-3 rounded-4 px-0"
                                            data-bs-toggle="tooltip" data-bs-offset="0,10" data-bs-title="Chatofort"
                                            src="assets\images\profile_picture_index_6.png"
                                            style="height: 80px; width: 80px; object-fit: cover;" alt="...">
                                    </div>
                                </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample"
                                    data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#carouselExample"
                                    data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="card" style="height: 400px;">
                            <div class="card-header">
                                <strong>Mon fil d'actu </strong><a href=""><svg xmlns="http://www.w3.org/2000/svg"
                                        width="20" height="20" fill="currentColor" class="bi bi-arrow-clockwise">
                                        <path fill-rule="evenodd"
                                            d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z" />
                                        <path
                                            d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z" />
                                    </svg></a>
                            </div>
                            <div class="card-body" style="height: 320px; overflow-y: scroll;">
                                <div class="card mb-3">
                                    <div class="card-header">
                                        <strong>13/04/2023 13:37</strong><br>Le vieux port, Marseille
                                    </div>
                                    <div class="card-body">
                                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ullam amet
                                            quae neque maxime
                                            voluptatum explicabo dignissimos beatae dicta repudiandae aspernatur
                                            ducimus, at
                                            culpa ex voluptatibus dolorum error ut exercitationem deserunt.</p>
                                    </div>
                                </div>
                                <div class="card mb-3">
                                    <div class="card-header">
                                        <strong>10/04/2023 13:37</strong><br>Ecusson, Montpellier
                                    </div>
                                    <div class="card-body">
                                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ullam amet
                                            quae neque
                                            maxime voluptatum explicabo dignissimos beatae dicta repudiandae
                                            aspernatur
                                            ducimus, at culpa ex voluptatibus dolorum error ut exercitationem
                                            deserunt.</p>
                                    </div>
                                </div>
                                <div class="card mb-3">
                                    <div class="card-header">
                                        <strong>09/09/2022 13:37</strong><br>Le marais, Paris
                                    </div>
                                    <div class="card-body">
                                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ullam amet
                                            quae
                                            neque maxime voluptatum explicabo dignissimos beatae dicta
                                            repudiandae
                                            aspernatur ducimus, at culpa ex voluptatibus dolorum error ut
                                            exercitationem
                                            deserunt.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="card m-1" style="height: 400px;">
                            <div class="card-header">
                                <strong>Mes derniers parcours</strong>
                                <a href=""><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                        fill="currentColor" class="bi bi-caret-left">
                                        <path
                                            d="M10 12.796V3.204L4.519 8 10 12.796zm-.659.753-5.48-4.796a1 1 0 0 1 0-1.506l5.48-4.796A1 1 0 0 1 11 3.204v9.592a1 1 0 0 1-1.659.753z">
                                        </path>
                                    </svg></a>
                                <a href="">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                                        class="bi bi-caret-right">
                                        <path
                                            d="M6 12.796V3.204L11.481 8 6 12.796zm.659.753 5.48-4.796a1 1 0 0 0 0-1.506L6.66 2.451C6.011 1.885 5 2.345 5 3.204v9.592a1 1 0 0 0 1.659.753z">
                                        </path>
                                    </svg>
                                </a>
                            </div>
                            <div class="card-body">
                                <div id="map" style="height: 320px;">Map</div>
                            </div>
                        </div>
                    </div>
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