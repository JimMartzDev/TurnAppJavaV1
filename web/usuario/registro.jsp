<%-- 
    Document   : registro
    Created on : 28/05/2026, 1:56:57 p. m.
    Author     : jimma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Registro</title>

    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap"
      rel="stylesheet"
    />

    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="css/registro.css" />
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
  </head>

  <body>
    <div class="main-container">

          <div class="card register-card z-depth-2">
            <div class="card-content">
              <a href="login.jsp" class="blue-text">
                <i class="material-icons">arrow_back</i>
              </a>

              <div class="center-align">
                <img src="img/logo.png" alt="Logo TurnApp" />
                <p class="title">Registro</p>
                <p class="grey-text">Crea tu cuenta en menos de un minuto</p>
              </div>

              <div class="row">
                <div class="input-field col s12 m6">
                  <input id="nombres" type="text" required />
                  <label for="nombres">Nombres</label>
                </div>

                <div class="input-field col s12 m6">
                  <input id="apellidos" type="text"  required/>
                  <label for="apellidos">Apellidos</label>
                </div>

                <div class="input-field col s12 m6">
                  <select>
                    <option value="" disabled selected>Seleccione</option>
                    <option value="1">CC</option>
                    <option value="2">CE</option>
                    <option value="3">Pasaporte</option>
                  </select>
                  <label>Tipo de documento</label>
                </div>

                <div class="input-field col s12 m6">
                  <input id="documento" type="text" required/>
                  <label for="documento">Número de documento</label>
                </div>

                <div class="input-field col s12 m6">
                    <input type="date" required/>
                  <label>Fecha de nacimiento </label>
                </div>

                <div class="input-field col s12 m6">
                    <input id="correo" type="email"  required />
                  <label for="correo">Correo electrónico</label>
                </div>
                  
                  <div class="input-field col s12 m6">
                  <input id="contrasena" type="password"  name="password" required />
                  <label for="contrasena">Contraseña</label>
                </div>
                  
                  <div class="input-field col s12 m6">
                      <input id="contrasenaCom" type="password" name="password" required />
                  <label for="contrasena">Confirmar contraseña</label>
                </div>
              </div>

              <p>
                <label>
                  <input type="checkbox"  />
                  <span
                    >Acepto los términos de uso y política de privacidad</span
                  >
                </label>
              </p>

              <br />

              <div class="center-align">
                <a
                  class="waves-effect waves-light btn btn-custom black-text"
                  style="width: 100%"
                >
                  Registrarse
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

    <script>
      document.addEventListener("DOMContentLoaded", function () {
        var elems = document.querySelectorAll("select");
        M.FormSelect.init(elems);

        var dateElems = document.querySelectorAll(".datepicker");
        M.Datepicker.init(dateElems);
      });
    </script>
  </body>
</html>
