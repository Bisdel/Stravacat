<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="fr">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Inscription 🐱 Stravacat</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
                crossorigin="anonymous">
            <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
                crossorigin="anonymous"></script>
            <base href="/Stravacat/">
        </head>

        <body>
            <div class="container">
                <header class="d-flex flex-wrap align-items-center py-3 mb-4 border-bottom">
                    <a href="/"
                        class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
                        <img src="assets\images\logo_stravacat.png" alt="Stravacat" height="60">
                    </a>
                    <ul class="nav nav-pills">
                        <li class="nav-item"><a class="nav-link" href="animal/inscription">S'inscrire</a></li>
                        <li class="nav-item"><a class="nav-link" href="connexion">Se connecter</a></li>
                        <li class="nav-item"><a class="nav-link" href="apropos">A propos</a></li>
                    </ul>
                </header>
            </div>
            <div class="container col-xl-10 col-xxl-8 px-4 py-5">
                <div class="row align-items-center g-lg-5 py-5">
                    <div class="col-lg-7 text-center text-lg-start">
                        <h1 class="display-3 fw-bold lh-1 text-body-emphasis mb-3">Tu veux rencontrer les animaux chauds de ton quartier ? 🐱</h1>
                        <p class="col-lg-10 mt-3 fs-4">Rejoins-nous vite !</p>
                    </div>
                    <div class="col-md-10 mx-auto col-lg-5">
                        <form method="post" class="p-4 p-md-5 border rounded-3 bg-body-tertiary">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingInput"
                                    placeholder="Grisette">
                                <label for="floatingInput">Ton pseudo</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="email" class="form-control" id="floatingInput"
                                    placeholder="grisette.petichat@example.com">
                                <label for="floatingInput">Adresse email</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="password" class="form-control" id="floatingPassword"
                                    placeholder="Mot de passe secret">
                                <label for="floatingPassword">Mot de passe</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="number" class="form-control" id="floatingInput" placeholder="J'ai...">
                                <label for="floatingInput">Ton âge</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingInput"
                                    placeholder="Je suis un...">
                                <label for="floatingInput">Quel animal est-tu ?</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingInput"
                                    placeholder="J'habite...">
                                <label for="floatingInput">Quelle ville habites-tu ?</label>
                            </div>

                            <div class="checkbox mb-3">
                                <label>
                                    <input type="checkbox" value="remember-me"> Se souvenir de moi
                                </label>
                            </div>
                            <button class="w-100 btn btn-lg btn-primary" type="submit">J'arrive !</button>
                            <hr class="my-4">
                            <small class="text-body-secondary">En cliquant sur J'arrive, j'accepte les termes et conditions d'utilisation de Stravacat©.</small>
                        </form>
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
                    <li class="nav-item"><a class="nav-link" href="animal/inscription">S'inscrire</a></li>
                    <li class="nav-item"><a class="nav-link" href="connexion">Se connecter</a></li>
                    <li class="nav-item"><a class="nav-link" href="apropos">A propos</a></li>
                </ul>
            </footer>
        </div>

        </html>