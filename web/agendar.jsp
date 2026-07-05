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

                // Inicialización global de selectores con restricción de ancho activa
                var selects = document.querySelectorAll("select");
                M.FormSelect.init(selects, {
                    dropdownOptions: {
                        constrainWidth: true // CORRECCIÓN LÓGICA: Fuerza al menú a medir lo mismo que el input nativo
                    }
                });

                var textareas = document.querySelectorAll(".materialize-textarea");
                M.CharacterCounter.init(textareas);

                // Captura de nodos esenciales del DOM
                const selectProfesional = document.getElementById("select-profesional");
                const inputFecha = document.getElementById("fecha-cita");
                const selectHora = document.getElementById("select-hora");

                // Método de implementación para la consulta de disponibilidad
                function actualizarHorarios() {
                    const idProf = selectProfesional.value;
                    const fecha = inputFecha.value;

                    // Validación de precondición antes de disparar la petición HTTP
                    if (idProf && fecha) {

                        // Matriz maestra de turnos del negocio (Formato 24h)
                        const horarioMaestro = [
                            "08:00:00", "09:00:00", "10:00:00", "11:00:00",
                            "14:00:00", "15:00:00", "16:00:00", "17:00:00"
                        ];

                        // Mapeo de formato visual para mejorar la experiencia de usuario (UX)
                        const formatoAMPM = {
                            "08:00:00": "08:00 AM", "09:00:00": "09:00 AM", "10:00:00": "10:00 AM", "11:00:00": "11:00 AM",
                            "14:00:00": "02:00 PM", "15:00:00": "03:00 PM", "16:00:00": "04:00 PM", "17:00:00": "05:00 PM"
                        };

                        // CORRECCIÓN DE ENRUTAMIENTO: Uso de la ruta de contexto dinámica del servidor Java
                        const urlContexto = "${pageContext.request.contextPath}/CitaController?accion=consultarDisponibilidad&idProfesional=" + idProf + "&fechaCita=" + fecha;

                        fetch(urlContexto)
                                .then(response => {
                                    if (!response.ok) {
                                        throw new Error("Falla en la respuesta del servidor de control");
                                    }
                                    return response.json();
                                })
                                .then(horasOcupadas => {
                                    // Limpieza del nodo del DOM
                                    selectHora.innerHTML = '<option value="" disabled selected>Selecciona la hora</option>';

                                    // Algoritmo de filtrado inverso (Exclusión de elementos coincidentes)
                                    const horasDisponibles = horarioMaestro.filter(hora => !horasOcupadas.includes(hora));

                                    // Inyección dinámica de nuevos nodos <option>
                                    horasDisponibles.forEach(hora => {
                                        const option = document.createElement("option");
                                        option.value = hora;
                                        option.textContent = formatoAMPM[hora] || hora;
                                        selectHora.appendChild(option);
                                    });

                                    // RE-INICIALIZACIÓN CRÍTICA: Materialize requiere reconstruir el componente visual
                                    M.FormSelect.init(selectHora, {
                                        dropdownOptions: {
                                            constrainWidth: true // Mantiene consistencia visual y evita desbordamiento
                                        }
                                    });
                                })
                                .catch(error => {
                                    console.error("Error en el flujo asíncrono de disponibilidad: ", error);
                                });
                    }
                }

                // Asignación de escuchadores de eventos para reactividad en la UI
                inputFecha.addEventListener("change", actualizarHorarios);
                selectProfesional.addEventListener("change", actualizarHorarios);
            });
        </script>
    </body>
</html>