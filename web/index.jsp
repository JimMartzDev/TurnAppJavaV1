<%-- 
    Document   : index
    Created on : 28/05/2026, 11:17:19 a. m.
    Author     : jimma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Inicio - TurnApp</title>

    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap"
      rel="stylesheet"
    />

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"
    />
    <link rel="stylesheet" href="css/principal.css" />
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
  </head>

  <body>
    <nav class="main-nav">
      <div class="nav-container">
        <a class="logo-link">
          <img src="img/logo.png" class="logo" />
        </a>
        <a data-target="mobile-demo" class="sidenav-trigger">
          <i class="material-icons">menu</i>
        </a>
        <div class="nav-icons hide-on-med-and-down">
          <a href="#"><i class="material-icons">favorite</i>Favoritos</a>
          <a href="#"><i class="material-icons">event</i>Mis turnos</a>
          <a href="login.jsp"><i class="material-icons">logout</i>Ingresar</a>
        </div>
      </div>
    </nav>
    <ul class="sidenav" id="mobile-demo">
      <li>
        <a><i class="material-icons">favorite</i>Favoritos</a>
      </li>
      <li>
        <a><i class="material-icons">event</i>Mis turnos</a>
      </li>
      <li>
        <a><i class="material-icons">logout</i>Cerrar sesión</a>
      </li>
    </ul>
    <div class="container hero center-align">
      <h4>Agenda tu turno fácil y rápido</h4>
      <p>Encuentra tu servicio favorito y reserva en segundos.</p>

      <h6 class="grey-text text-darken-1">CATEGORÍAS</h6>
    </div>
    <div class="page-center">
      <div class="container">
        <div class="row custom-row">
          <div class="col s12 m6 l3">
            <div class="card card-category">
              <div class="card-image">
                <img src="img/barberia.jpeg" />
              </div>
              <div class="card-content">
                <span class="card-title">Barberías</span>
                <p>Cortes, perfilado y arreglo de barba.</p>
              </div>
              <div class="card-action center-align">
                <a
                  href="agendar.html"
                  class="btn btn-custom waves-effect waves-light black-text"
                  >Reservar</a
                >
              </div>
            </div>
          </div>
          <div class="col s12 m6 l3">
            <div class="card card-category">
              <div class="card-image">
                <img src="img/Salones.jpeg" />
              </div>
              <div class="card-content">
                <span class="card-title">Salones</span>
                <p>Peinados, color y estilismo profesional.</p>
              </div>
              <div class="card-action center-align">
                <a
                  href="agendar.html"
                  class="btn btn-custom waves-effect waves-light black-text"
                  >Reservar</a
                >
              </div>
            </div>
          </div>
          <div class="col s12 m6 l3">
            <div class="card card-category">
              <div class="card-image">
                <img src="img/Manicure.jpeg" />
              </div>
              <div class="card-content">
                <span class="card-title">Manicure</span>
                <p>Cuidado profesional de manos y uñas.</p>
              </div>
              <div class="card-action center-align">
                <a
                  href="agendar.html"
                  class="btn btn-custom waves-effect waves-light black-text"
                  >Reservar</a
                >
              </div>
            </div>
          </div>
          <div class="col s12 m6 l3">
            <div class="card card-category">
              <div class="card-image">
                <img src="img/Masaje.jpeg" />
              </div>
              <div class="card-content">
                <span class="card-title">Masajes</span>
                <p>Relajación y bienestar para tu cuerpo.</p>
              </div>
              <div class="card-action center-align">
                <a
                  href="agendar.html"
                  class="btn btn-custom waves-effect waves-light black-text"
                  >Reservar</a
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  <script>
    document.addEventListener("DOMContentLoaded", function () {
      var elems = document.querySelectorAll(".sidenav");
      M.Sidenav.init(elems);
    });
  </script>
</html>
