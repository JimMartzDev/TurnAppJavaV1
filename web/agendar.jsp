<%-- 
    Document   : agendar 
    Created on : 28/05/2026, 1:58:00 p. m.
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
        <link rel="stylesheet" href="css/agendar.css" />
    </head>

    <body>
        <div class="page-center">
            <div class="card card-agenda z-depth-2">
                <div class="card-content">
                    <a href="index.jsp" class="blue-text">
                        <i class="material-icons">arrow_back</i>
                    </a>
                    <div class="center-align">
                        <img src="img/logo.png" class="logo" />
                        <h5>Agenda tu turno</h5>
                        <p class="grey-text">Completa los datos para agendar tu cita</p>
                    </div>

                    <form action="CitaController" method="POST">

                        <div class="form-section">

                         
                            <div class="input-field">
                                <select name="idProfesional" id="select-profesional" required>
                                    <option value="" disabled selected>-- Elige un profesional --</option>
                                 
                                </select>
                            </div>

                            <div class="input-field">
                                <select name="idServicio" id="select-servicio" required>
                                    <option value="" disabled selected>-- Selecciona el servicio --</option>
                                    <option value="1">Corte de Cabello</option>
                                    <option value="2">Barba y Perfilado</option>
                                </select>
                            </div>

                            <div class="input-field">
                                <input type="date" name="fechaCita" id="fecha-cita" required />
                            </div>

                            <div class="input-field">
                                <select name="horaCita" id="select-hora" required>
                                    <option value="" disabled selected>-- Selecciona la hora --</option>
                                    <option value="09:00:00">09:00 AM</option>
                                    <option value="10:00:00">10:00 AM</option>
                                    <option value="11:00:00">11:00 AM</option>
                                </select>
                            </div>

                            <div class="input-field">
                                <textarea name="notasCliente" id="notas-cliente" class="materialize-textarea"></textarea>
                                <label for="notas-cliente">Notas o requerimientos especiales (Opcional)</label>
                            </div>

                        </div>

                        <div class="center-align" style="margin-top: 30px;">
                            <button type="submit" class="waves-effect waves-light btn btn-custom black-text">
                                Agendar
                            </button>
                        </div>

                    </form> </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Activa todos los elementos select y componentes de texto de Materialize
                var selects = document.querySelectorAll("select");
                M.FormSelect.init(selects);

                var textareas = document.querySelectorAll(".materialize-textarea");
                M.CharacterCounter.init(textareas);
            });
        </script>
    </body>
</html>