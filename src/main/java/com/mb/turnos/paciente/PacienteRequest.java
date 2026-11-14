package com.mb.turnos.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PacienteRequest (
    @NotBlank(message = "El nombre es obligatorio") String nombre,
    @NotBlank(message = "El apellido es obligatorio") String apellido,
    @NotBlank(message = "La cédula es obligatoria") String cedula,
    @Email(message = "El email debe tener un formato válido") String email
){
    public Paciente aEntidad() {
        Paciente paciente = new Paciente();
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setCedula(cedula);
        paciente.setEmail(email);
        return paciente;
    }

}
