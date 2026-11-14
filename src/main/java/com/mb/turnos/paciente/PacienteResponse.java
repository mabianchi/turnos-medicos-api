package com.mb.turnos.paciente;

public record PacienteResponse(Long id, String nombre, String apellido, String cedula, String email) {

    public static PacienteResponse desde(Paciente paciente) {
        return new PacienteResponse(
            paciente.getId(), paciente.getNombre(), paciente.getApellido(), paciente.getCedula(), paciente.getEmail()
        );
    }
}
