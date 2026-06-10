<%-- 
    Document   : agendar
    Created on : 28/05/2026, 1:58:00 p. m.
    Author     : jimma
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
...
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

                    <div class="center-align">
                        <img
                            src="https://images.unsplash.com/photo-1521590832167-7bcbfaa6381f"
                            class="business-img"
                            />
                    </div>

                    <div class="center-align">
                        <select name="idEstablecimiento" id="select-establecimiento">
                            <option value="">-- Elige un local --</option>
                            <c:forEach items="${listaEstablecimientos}" var="est">
                                <option value="${est.id_establecimiento}" 
                                        data-direccion="${est.direccion}" 
                                        data-calificacion="${est.calificacion}" 
                                        data-imagen="${est.ruta_imagen}">
                                    ${est.nombre_establecimiento}
                                </option>
                            </c:forEach>
                        </select>


                    </div>

                    <div class="form-section">
                        <div class="input-field">
                            <select>
                                <option disabled selected>-- Elige un profesional --</option>
                                <option>Juan Pérez</option>
                                <option>Carlos Ramírez</option>
                            </select>

                        </div>

                        <div class="input-field">
                            <input type="date" />

                        </div>

                        <div class="input-field">
                            <select>
                                <option disabled selected>-- Selecciona la hora --</option>
                                <option>09:00 AM</option>
                                <option>10:00 AM</option>
                                <option>11:00 AM</option>
                            </select>

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

        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Activa los menú desplegables (selects)
                var selects = document.querySelectorAll("select");
                M.FormSelect.init(selects);
            });
        </script>
    </body>
</body>
</html>
