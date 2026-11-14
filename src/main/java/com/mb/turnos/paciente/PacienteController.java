package com.mb.turnos.paciente;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service){
        this.service = service;
    }

    @GetMapping
    public List<PacienteResponse> listar(){
        List<PacienteResponse> pacientes = new ArrayList<>();
        for(Paciente paciente: service.listar()){
            pacientes.add(PacienteResponse.desde(paciente));
        }
        return pacientes;
    }

    @GetMapping("/{id}")
    public PacienteResponse buscar(@PathVariable Long id){
        Paciente paciente = service.buscarPorId(id);
        return PacienteResponse.desde(paciente);
    }

    @GetMapping("/cedula/{cedula}")
    public PacienteResponse buscarPorCedula(@PathVariable String cedula){
        Paciente paciente = service.buscarPorCedula(cedula);
        return PacienteResponse.desde(paciente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponse crear(@Valid @RequestBody PacienteRequest request){
        Paciente creado = service.crear(request.aEntidad());
        return PacienteResponse.desde(creado);
    }

    @PutMapping("/{id}")
    public PacienteResponse actualizar(@PathVariable Long id, @Valid @RequestBody PacienteRequest request){
        Paciente actualizado = service.actualizar(id, request.aEntidad());
        return PacienteResponse.desde(actualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
