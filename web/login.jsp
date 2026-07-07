<%-- 
    Document   : login
    Created on : 28/05/2026, 10:35:52 a. m.
    Author     : jimma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <title>Agendar Turno - TurnApp</title>

        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" />
        <link rel="stylesheet" href="css/login.css" />


    </head>

    <body class="grey lighten-4 flex-container">
        <div class="card z-depth-2">
            <div class="card-content">
                <div class="row" style="margin-bottom: 0;">
                        <div class="col s12">
                            <a href="${pageContext.request.contextPath}/index.jsp" class="teal-text text-darken-2">
                                <i class="material-icons">arrow_back</i>
                            </a>
                        </div>
                    </div>
                <div class="center-align">
                    <img src="${pageContext.request.contextPath}/img/logo.png" class="responsive-img" />
                    <p class="grey-text">Bienvenido</p>
                    <p class="grey-text text-darken-1">Por favor ingrese sus datos</p>
                </div>

                <form action="${pageContext.request.contextPath}/ValidarAcceso" method="POST">
                    <div class="input-field">
                        <i class="material-icons prefix">person</i>
                        <input id="usuario" type="text" name="txtUsu" required/>
                        <label for="usuario">Usuario</label>
                    </div>

                    <div class="input-field">
                        <i class="material-icons prefix">lock</i>
                        <input id="password" type="password" name="txtPas" required />
                        <label for="password">Contraseña</label>
                    </div>

                    <div class="options-row">
                        <label>
                            <input type="checkbox" />
                            <span>Recordarme</span>
                        </label>
                        <a href="recuperar.jsp" class="blue-text text-darken-2">He olvidado la contraseña</a>
                    </div>

                    <div class="center-align">
                        <button type="submit" class="waves-effect waves-light btn green btn-login" style="width: 100%">
                            Ingresar
                        </button>
                    </div>
                </form>

                <%
                    if (request.getAttribute("mensaje") != null) {
                        String msg = (String) request.getAttribute("mensaje");
                        String colorClase = (msg.toLowerCase().contains("éxito") || msg.toLowerCase().contains("exito"))
                                ? "green-text text-darken-2"
                                : "red-text text-darken-2";

                        out.print("<div class='center-align " + colorClase + "' style='margin-top: 10px; font-weight: 500;'>" + msg + "</div>");
                    }
                %>

                <div class="center-align btn-google-container">
                    <a class="waves-effect btn white black-text btn-login" style="width: 100%">
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
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var selects = document.querySelectorAll("select");
                M.FormSelect.init(selects);

                var textareas = document.querySelectorAll(".materialize-textarea");
                M.CharacterCounter.init(textareas);
            });
        </script>
    </body>
</html>