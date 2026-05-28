<%-- 
    Document   : agendar
    Created on : 28/05/2026, 1:58:00 p. m.
    Author     : jimma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Agendar Turno - TurnApp</title>

    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="css/materialize.min.css" />
    <link rel="stylesheet" href="css/agendar.css" />
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
  </head>

  <body>
    <div class="page-center">
      <div class="card card-agenda z-depth-2">
        <div class="card-content">
          <a href="principal.html" class="blue-text">
            <i class="material-icons">arrow_back</i>
          </a>
          <div class="center-align">
            <img src="img/logo.png" class="logo" />
            <h5>Agenda tu turno</h5>
            <p class="grey-text">Completa los datos para agendar tu cita</p>
          </div>

          <div class="center-align">
            <img
              src="https://images.unsplash.com/photo-1521590832167-7bcbfaa6381f"
              class="business-img"
            />
          </div>

          <div class="center-align">
            <h6 style="font-weight: 600">Barbería El Triunfo</h6>
            <span class="grey-text">Calle 11 #12-51</span>
            <div class="rating">
              <i class="material-icons">star</i>
              <i class="material-icons">star</i>
              <i class="material-icons">star</i>
              <i class="material-icons">star_half</i>
              <i class="material-icons grey-text">star_border</i>
            </div>
          </div>

          <div class="form-section">
            <div class="input-field">
              <select>
                <option disabled selected>Elige un profesional</option>
                <option>Juan Pérez</option>
                <option>Carlos Ramírez</option>
              </select>
              <label>Profesional</label>
            </div>

            <div class="input-field">
              <input type="date" />
              <label>Fecha</label>
            </div>

            <div class="input-field">
              <select>
                <option disabled selected>Selecciona la hora</option>
                <option>09:00 AM</option>
                <option>10:00 AM</option>
                <option>11:00 AM</option>
              </select>
              <label>Hora</label>
            </div>
          </div>

          <div class="center-align">
            <a class="waves-effect waves-light btn btn-custom black-text">
              Agendar
            </a>
          </div>
        </div>
      </div>
    </div>

    <script src="js/materialize.min.js"></script>
    <script>
      document.addEventListener("DOMContentLoaded", function () {
        var selects = document.querySelectorAll("select");
        M.FormSelect.init(selects);
      });
    </script>
  </body>
</html>
