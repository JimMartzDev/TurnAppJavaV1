<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <title>Registro de Profesional</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro.css?v=1.2" />
    </head>

    <body>
        <div class="main-container">
            <div class="card register-card z-depth-2">
                <div class="card-content">



                    <div class="row" style="margin-bottom: 0;">
                        <div class="col s12">
                            <a href="../login.jsp" class="teal-text text-darken-2">
                                <i class="material-icons">arrow_back</i>
                            </a>
                        </div>
                    </div>

                    <div class="row nav-roles">
                        <div class="col s6 center-align col-pestana">
                            <a href="../usuario/registro.jsp" class="grey-text text-darken-1 link-pestana">
                                Registro Cliente
                            </a>
                        </div>
                        <div class="col s6 center-align col-pestana pestana-activa">
                            <a href="registroProfesional.jsp" class="green-text text-darken-1 link-pestana" style="font-weight: 600;">
                                Registro Profesional
                            </a>
                        </div>
                    </div>

                    <div class="center-align" style="margin-bottom: 20px;">
                        <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo TurnApp" class="logo-registro" />
                       <h6 class="subtituloProfesional">Únete como Profesional</h6>
                        <p class="grey-text" style="margin: 0;">Registra tus datos y ofrece tus servicios</p>
                        <%
                            String respuesta = (String) request.getAttribute("respuesta");
                            if (respuesta != null) {
                        %>
                        <div class="teal-text text-darken-4 center-align" style="margin-top: 15px;">
                            <%= respuesta%>
                        </div>
                        <%
                            }
                        %>
                    </div>

                    <form action="${pageContext.request.contextPath}/ProfesionalController" method="POST">
                        <input type="hidden" name="accion" value="registrar" />
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="nombres" type="text" name="nombres" required class="validate" />
                                <label for="nombres">Nombres</label>
                            </div>

                            <div class="input-field col s12 m6">
                                <input id="apellidos" type="text" name="apellidos" required class="validate" />
                                <label for="apellidos">Apellidos</label>
                            </div>

                            <div class="input-field col s12 m6">
                                <select name="tipoDocumento" required>
                                    <option value="" disabled selected>Seleccione</option>
                                    <option value="CC">CC</option>
                                    <option value="CE">CE</option>
                                    <option value="Pasaporte">Pasaporte</option>
                                </select>
                                <label>Tipo de documento</label>
                            </div>

                            <div class="input-field col s12 m6">
                                <input id="documento" type="text" name="documento" required class="validate" />
                                <label for="documento">Número de documento</label>
                            </div>

                            <div class="input-field col s12 m6">
                                <input id="fechaNacimiento" type="date" name="fechaNacimiento" required class="validate" />
                                <label for="fechaNacimiento" class="active">Fecha de nacimiento</label>
                            </div>

                            <div class="input-field col s12 m6">
                                <input id="correo" type="email" name="correo" required class="validate" />
                                <label for="correo">Correo electrónico</label>
                            </div>

                            <div class="input-field col s12 m6">
                                <input id="contrasena" type="password" name="password" required class="validate" />
                                <label for="contrasena">Contraseña</label>
                            </div>

                            <div class="input-field col s12 m6">
                                <select name="especialidadPrincipal" required>
                                    <option value="" disabled selected>Seleccione</option>
                                    <option value="Barbería">Barbería</option>
                                    <option value="Peluquería">Estilista / Peluquería</option>
                                    <option value="Manicura">Manicura / Pedicura</option>
                                    <option value="Maquillaje">Maquillaje</option>
                                </select>
                                <label>Especialidad Principal</label>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col s12 center-align">
                                <label style="display: inline-flex; align-items: center;">
                                    <input type="checkbox" required class="filled-in" />
                                    <span class="terms-text grey-text text-darken-2">
                                        Acepto los términos de uso y política de privacidad
                                    </span>
                                </label>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 15px;">
                            <div class="col s12 m6 offset-m3 center-align">
                                <button type="submit" class="waves-effect waves-light btn btn-custom white-text">
                                    Registrarse
                                </button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var elems = document.querySelectorAll("select");
                M.FormSelect.init(elems);
            });
        </script>
    </body>
</html>