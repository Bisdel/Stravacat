<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Accueil STRAVACAT</title>
  <base href="/stravacat/">
  
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
  <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="assets/css/fond.css">

    
</head>

<body>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">STRAVACAT
        <div class="spinner-border spinner-border-sm" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
        <div class="spinner-grow spinner-grow-sm" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
      </a>
      <a class="navbar-brand" href="#">
        <img src="assets\images\logo_stravacat.png" height="70" width="126">

      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="\anoussak\actualites.html">Actualités</a>
          </li>


          <li class="nav-item">
            <a class="nav-link" href="\leo\profile.html">Profil</a>
          </li>
          <li>
            <a class="nav-link" href="\missipssa\abonnements\abonnements.html">Espace abonnements</a>
          </li>
          <li>
            <a class="nav-link" href="\missipssa\abonnes\abonnes.html">Espace abonnés</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="container">
    <h3>
      <center>Enregistrez. Partagez. Retrouvez vos animaux !</center>
    </h3>
  </div>
  <div class="container">
    <h5>
      <center>Espace de connexion</center>
    </h5>
  </div>
  <div class="container">
    <h6>
      <center>Sur Stravacat, partagez vos moments avec vos animaux, vos ballades, vos retrouvailles, et vos plus belles
        réussites !</center>
    </h6>
  </div>
  <div>
    <center>
      <div>
        <a href="">S'inscrire avec Facebook</a>

      </div>
      <div>
        <a href="">S'inscire avec son compte Google</a>
      </div>
      <div>
        <a href="">S'inscire avec son adresse mail</a>
      </div>
    </center>
  </div>
  <div class="accordion" id="accordionExample">
    <div class="accordion-item">
      <h2 class="accordion-header">
        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne"
          aria-expanded="true" aria-controls="collapseOne">
          Pourquoi partagez votre expérience avec nous ?
        </button>
      </h2>
      <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
        <div class="accordion-body">
          <strong>BLA BLA BLA</strong>
          BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA
          BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA
          BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA
        </div>
      </div>
    </div>
    <div class="accordion-item">
      <h2 class="accordion-header">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
          data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          Retour d'expérience
        </button>
      </h2>
      <div id="collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
        <div class="accordion-body">
          <center>
            <div class="card" style="width: 18rem;">
              <img src="assets\images\profile_picture_index_4.png" class="card-img-top" alt="ChatDuTiéquard">
              <div class="card-body">
                <h5 class="card-title">ChatDuTiéquard</h5>
                <p class="card-text">
                  J'adore dormir c'est très important, car ça me permet d'être en forme pour chasser toutes les nuits !
                  Je peux enfin partager toutes mes promenades nocturnes.
                </p>
              </div>
              <ul class="list-group list-group-flush">
                <li class="list-group-item"><strong>172km</strong> parcourus</li>
                <li class="list-group-item"><strong>213</strong> personnes découvertes</li>
                <li class="list-group-item"><strong>56</strong> publications</li>
              </ul>
              <div class="card-body">
                <a href="#" class="card-link">Voir son profil</a>
                <a href="#" class="card-link">Demande d'amis</a>
              </div>
            </div>
          </center>
        </div>
      </div>
    </div>
    <div class="accordion-item">
      <h2 class="accordion-header">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo"
          aria-expanded="false" aria-controls="collapseTwo">
          Nos créateurs
        </button>
      </h2>
      <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
        <div class="accordion-body">
          <div id="carouselExampleCaptions" class="carousel slide">
            <div class="carousel-indicators">
              <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
              <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
              <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
              <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3" aria-label="Slide 4"></button>
            </div>
            <div class="carousel-inner">
              <div class="carousel-item active">
                <img src="assets\images\profile_picture_index_7.png" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                  <h5><font color="black">First slide label</font> </h5>
                  <p><font color ="black">Some representative placeholder content for the first slide.</font></p>
                </div>
              </div>
              <div class="carousel-item">
                <img src="assets\images\profile_picture_index_12.png" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                  <h5>Second slide label</h5>
                  <p>Some representative placeholder content for the second slide.</p>
                </div>
              </div>
              <div class="carousel-item">
                <img src="assets\images\profile_picture_index_8.png" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                  <h5>Third slide label</h5> 
                  <p>Some representative placeholder content for the third slide.</p>
                </div>
              </div>
              <div class="carousel-item">
                <img src="assets\images\profile_picture_index_5.png" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                  <h5>Fourth slide label</h5>
                  <p>Some representative placeholder content for the fourth slide.</p>
                </div>
              </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="background">
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
    </div>

  </div>

</body>










<div class="container">
  <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    <p class="col-md-4 mb-0 text-body-secondary">© 2023 Stravacat, Inc</p>

    <a href="/"
      class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
      <img src="assets\images\logo_stravacat.png" alt="Stravacat" height="60">
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