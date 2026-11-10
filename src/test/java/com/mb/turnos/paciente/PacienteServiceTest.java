package com.mb.turnos.paciente;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    PacienteRepository repository; // repositorio falso: no toca la base

    @InjectMocks
    PacienteService service; // servicio real, con el repositorio falso inyectado

    @Test
    void eliminar_lanzaErrorSiElPacienteNoExiste(){
        // Preparer: simular que la base no encuentra el id 99
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Ejecutar y comprobar: tiene que lanzar la excepcion
        assertThrows(IllegalArgumentException.class, () -> service.eliminar(99L));

        // Y nunca debe intentar borrar nada}
        verify(repository, never()).delete(any());
    }

    @Test
    void crear_lanzaErrorSiLaCedulaYaExiste(){
        Paciente existente = new Paciente();
        existente.setCedula("12345678");
        when(repository.findByCedula("12345678")).thenReturn(Optional.of(existente));

        Paciente nuevo = new Paciente();
        nuevo.setCedula("12345678");

        assertThrows(IllegalStateException.class, () -> service.crear(nuevo));
        verify(repository, never()).save(any());
    }

    @Test
    void actualizar_lanzaErrorSiElPacienteNoExiste(){
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Paciente paciente = new Paciente();
        paciente.setCedula("12345678");
        paciente.setNombre("PacienteNombre");
        paciente.setApellido("PacienteApellido");

        assertThrows(IllegalArgumentException.class, () -> service.actualizar(99L, paciente));
        verify(repository, never()).findByCedula(any());
    }

    @Test
    void actualizar_modificaLosDatosSiLaCedulaNoCambia() {
        // PREPARAR
        Paciente existente = new Paciente();
        existente.setNombre("Ana");
        existente.setCedula("111");
        when(repository.findById(1L)).thenReturn(Optional.of(existente));

        Paciente datos = new Paciente();
        datos.setNombre("Ana María");
        datos.setCedula("111");              // misma cédula → no entra al if

        // EJECUTAR
        Paciente resultado = service.actualizar(1L, datos);

        // COMPROBAR
        assertEquals("Ana María", resultado.getNombre());
    }
}
