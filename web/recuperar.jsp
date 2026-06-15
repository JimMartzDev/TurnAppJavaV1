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
           <% 
            String paso = (String) request.getAttribute("paso");
            String correoUsuario = (String) request.getAttribute("correoUsuario");
            
            if (paso != null && paso.equals("2")) { 
        %>
            <form action="${pageContext.request.contextPath}/RecuperarController" method="POST">
                <input type="hidden" name="accion" value="cambiarPassword" />
                <input type="hidden" name="txtCorreoConfirmado" value="<%= correoUsuario %>" />

                <p class="grey-text text-darken-1 center-align">Escribe y confirma tu nueva contraseña</p>

                <div class="input-field">
                  <i class="material-icons prefix">lock</i>
                  <input id="nuevaPas" type="password" name="txtNuevaPas" required class="validate" />
                  <label for="nuevaPas">Nueva Contraseña</label>
                </div>

                <div class="input-field">
                  <i class="material-icons prefix">lock_outline</i>
                  <input id="confirmarPas" type="password" name="txtConfirmarPas" required class="validate" />
                  <label for="confirmarPas">Confirmar Contraseña</label>
                </div>

                <% if(request.getAttribute("mensaje") != null){ %>
                    <div class='center-align red-text text-darken-2' style='margin-bottom: 15px;'><%= request.getAttribute("mensaje") %></div>
                <% } %>

                <div class="center-align">
                  <button type="submit" class="waves-effect waves-light btn green btn-recovery">
                    Guardar Nueva Contraseña
                  </button>
                </div>
            </form>

        <% } else { %>

            <form action="${pageContext.request.contextPath}/RecuperarController" method="POST">
                <input type="hidden" name="accion" value="verificarCorreo" />

                <div class="input-field">
                  <i class="material-icons prefix">email</i>
                  <input id="correo" type="email" name="txtCorreo" required class="validate" />
                  <label for="correo">Correo electrónico</label>
                </div>

                <% if(request.getAttribute("mensaje") != null){ %>
                    <div class='center-align red-text text-darken-2' style='margin-bottom: 15px;'><%= request.getAttribute("mensaje") %></div>
                <% } %>

                <div class="center-align">
                  <button type="submit" class="waves-effect waves-light btn green btn-recovery">
                    Validar Correo
                  </button>
                </div>
            </form>
        <% } %>
        </form>

      </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  </body>
</html>