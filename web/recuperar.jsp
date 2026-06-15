<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Recuperar Contraseña - TurnApp</title>
    <!-- Importamos fuentes e iconos -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    <!-- Framework Materialize CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" />
    
    <!-- TUS ESTILOS PROPIOS SEPARADOS (Con truco de versión para evitar caché) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/recuperar.css?v=1.0" />
  </head>

  <body class="grey lighten-4 flex-container">
    <div class="card recovery-card z-depth-2">
      <div class="card-content">
          
        <!-- Botón superior para regresar al Login -->
        <div class="row" style="margin-bottom: 0;">
            <div class="col s12">
                <a href="login.jsp" class="teal-text text-darken-2">
                    <i class="material-icons">arrow_back</i>
                </a>
            </div>
        </div>

        <!-- Encabezado de la Tarjeta -->
        <div class="center-align" style="margin-bottom: 20px;">
          <img src="img/logo.png" alt="Logo TurnApp" class="logo-recovery" />
          <h5 class="title">Recuperar Contraseña</h5>
          <p class="grey-text text-darken-1" style="margin: 0;">Ingresa tu correo electrónico para restablecer tu cuenta</p>
        </div>
          
        <!-- Formulario de validación inicial -->
        <form action="${pageContext.request.contextPath}/RecuperarController" method="POST">
            <!-- Acción oculta para el controlador -->
            <input type="hidden" name="accion" value="verificarCorreo" />

            <div class="input-field">
              <i class="material-icons prefix">email</i>
              <input id="correo" type="email" name="txtCorreo" required class="validate" />
              <label for="correo">Correo electrónico</label>
            </div>

            <!-- Zona dinámica para mensajes del servidor -->
            <% 
                if(request.getAttribute("mensaje") != null){
                    out.print("<div class='center-align red-text text-darken-2' style='margin-bottom: 15px;'>" + request.getAttribute("mensaje") + "</div>");
                }
            %>

            <div class="center-align">
              <button type="submit" class="waves-effect waves-light btn green btn-recovery">
                Validar Correo
              </button>
            </div>
        </form>

      </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  </body>
</html>