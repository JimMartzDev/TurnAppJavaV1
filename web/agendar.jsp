
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

                    <c:if test="${not empty mensajeExito}">
                        <div class="green-text text-darken-2 center-align">
                            ${mensajeExito}
                        </div>
                    </c:if>
                    <c:if test="${not empty mensajeError}">
                        <div class="red-text text-darken-2 center-align">
                            ${mensajeError}
                        </div>
                    </c:if>

                    <form action="CitaController" method="POST">
                        <div class="form-section">
                            <input type="hidden" name="accion" value="registrar" />
                            <div class="input-field">
                                <select name="idProfesional" id="select-profesional" required>
                                    <option value="" disabled selected>Elige un profesional</option>
                                    <c:forEach var="profesional" items="${listaProfesionales}">
                                        <option value="${profesional.idProfesional}">${profesional.nombre} ${profesional.apellido} - (${profesional.especialidadPrincipal}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <input type="hidden" name="idServicio" value="1" />

                            <div class="input-field">
                                <input type="date" name="fechaCita" id="fecha-cita" required />
                            </div>

                            <div class="input-field">
                                <select name="horaCita" id="select-hora" required>
                                    <option value="" disabled selected>Selecciona la hora</option>
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
                    </form> 
                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

        <script>
            document.addEventListener("DOMContentLoaded", function () {


                var selects = document.querySelectorAll("select");
                M.FormSelect.init(selects, {
                    dropdownOptions: {
                        constrainWidth: true
                    }
                });

                var textareas = document.querySelectorAll(".materialize-textarea");
                M.CharacterCounter.init(textareas);

                // Captura de nodos esenciales del DOM
                const selectProfesional = document.getElementById("select-profesional");
                const inputFecha = document.getElementById("fecha-cita");
                const selectHora = document.getElementById("select-hora");
                function actualizarHorarios() {
                    const idProf = selectProfesional.value;
                    const fecha = inputFecha.value;
                    if (idProf && fecha) {
                        const horarioMaestro = [
                            "08:00:00", "09:00:00", "10:00:00", "11:00:00",
                            "14:00:00", "15:00:00", "16:00:00", "17:00:00"
                        ];
                        const formatoAMPM = {
                            "08:00:00": "08:00 AM", "09:00:00": "09:00 AM", "10:00:00": "10:00 AM", "11:00:00": "11:00 AM",
                            "14:00:00": "02:00 PM", "15:00:00": "03:00 PM", "16:00:00": "04:00 PM", "17:00:00": "05:00 PM"
                        };

                        const urlContexto = "${pageContext.request.contextPath}/CitaController?accion=consultarDisponibilidad&idProfesional=" + idProf + "&fechaCita=" + fecha;

                        fetch(urlContexto)
                                .then(response => {
                                    if (!response.ok) {
                                        throw new Error("Falla en la respuesta del servidor de control");
                                    }
                                    return response.json();
                                })
                                .then(horasOcupadas => {
                                    selectHora.innerHTML = '<option value="" disabled selected>Selecciona la hora</option>';
                                    const horasDisponibles = horarioMaestro.filter(hora => !horasOcupadas.includes(hora));
                                    horasDisponibles.forEach(hora => {
                                        const option = document.createElement("option");
                                        option.value = hora;
                                        option.textContent = formatoAMPM[hora] || hora;
                                        selectHora.appendChild(option);
                                    });
                                    M.FormSelect.init(selectHora, {
                                        dropdownOptions: {
                                            constrainWidth: true
                                        }
                                    });
                                })
                                .catch(error => {
                                    console.error("Error en el flujo asíncrono de disponibilidad: ", error);
                                });
                    }
                }
                inputFecha.addEventListener("change", actualizarHorarios);
                selectProfesional.addEventListener("change", actualizarHorarios);
            });
        </script>
    </body>
</html>