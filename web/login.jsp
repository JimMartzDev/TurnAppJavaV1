<%-- 
    Document   : login
    Created on : 28/05/2026, 10:35:52 a. m.
    Author     : jimma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Login TurnApp</title>
    <link rel="stylesheet" href="css/materialize.min.css" />
    <link rel="stylesheet" href="css/login.css" />
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
  </head>

  <body class="grey lighten-4 flex-container">
    <div class="card z-depth-2">
      <div class="card-content">
        <div class="center-align">
          <img src="img/logo.png" class="responsive-img" />
          <p class="grey-text">Bienvenido</p>
          <p class="grey-text text-darken-1">Por favor ingrese sus datos</p>
        </div>
          
          <form action="${pageContext.request.contextPath}/ValidarAcceso" method="POST">
        <div   class="input-field">
          <i class="material-icons prefix">person</i>
          <input id="usuario" type="text" name="txtUsu" required/>
          <label for="usuario">Usuario</label>
        </div>

        <div  class="input-field">
          <i class="material-icons prefix">lock</i>
          <input id="password" type="password" name="txtPas" required />
          <label for="password">Contraseña</label>
        </div>

        <div class="options-row">
          <label>
            <input type="checkbox" />
            <span>Recordarme</span>
          </label>
          <a href="recuperar.jsp" class="teal-text text-darken-2">He olvidado la contraseña</a>
        </div>

        <div class="center-align">
          <button
            type="submit" class="waves-effect waves-light btn green btn-login"
            style="width: 100%"
          >
            Ingresar
          </button>
        </div>
              </form>
          <% 
              if(request.getAttribute("mensaje")!=null){
                  out.print("<div class='center-align red-text text-darken-2' style='margin-top: 10px;'>" + request.getAttribute("mensaje") + "</div>");

              }
          %>

        <div class="center-align">
          <a
            class="waves-effect btn white black-text btn-login"
            style="width: 100%"
          >
            Iniciar sesión con Google
          </a>
        </div>

        <div class="center-align register">
          ¿No tiene una cuenta?
          <a href="usuario/registro.jsp">Registrarse</a>
        </div>
      </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  </body>
</html>
