package com.mb.turnos.paciente;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Pacientes", description = "Alta, baja, modificación y consulta de pacientes")
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service){
        this.service = service;
    }

    @Operation(summary = "Listar todos los pacientes")
    @GetMapping
    public List<PacienteResponse> listar(){
        List<PacienteResponse> pacientes = new ArrayList<>();
        for(Paciente paciente: service.listar()){
            pacientes.add(PacienteResponse.desde(paciente));
        }
        return pacientes;
    }

    @Operation(summary = "Buscar un paciente por id")
    @GetMapping("/{id}")
    public PacienteResponse buscar(@PathVariable Long id){
        Paciente paciente = service.buscarPorId(id);
        return PacienteResponse.desde(paciente);
    }

    @Operation(summary = "Buscar un paciente por su cédula")
    @GetMapping("/cedula/{cedula}")
    public PacienteResponse buscarPorCedula(@PathVariable String cedula){
        Paciente paciente = service.buscarPorCedula(cedula);
        return PacienteResponse.desde(paciente);
    }

    @Operation(summary = "Crear un paciente")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponse crear(@Valid @RequestBody PacienteRequest request){
        Paciente creado = service.crear(request.aEntidad());
        return PacienteResponse.desde(creado);
    }

    @Operation(summary = "Actualizar datos de un paciente por su id")
    @PutMapping("/{id}")
    public PacienteResponse actualizar(@PathVariable Long id, @Valid @RequestBody PacienteRequest request){
        Paciente actualizado = service.actualizar(id, request.aEntidad());
        return PacienteResponse.desde(actualizado);
    }

    @Operation(summary = "Eliminar un paciente por su id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
