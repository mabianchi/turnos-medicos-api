package com.mb.turnos.paciente;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service){
        this.service = service;
    }

    @GetMapping
    public List<Paciente> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Paciente buscar(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @GetMapping("/cedula/{cedula}")
    public Paciente buscarPorCedula(@PathVariable String cedula){
        return service.buscarPorCedula(cedula);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente crear(@Valid @RequestBody Paciente paciente){
        return service.crear(paciente);
    }

    @PutMapping("/{id}")
    public Paciente actualizar(@PathVariable Long id, @Valid @RequestBody Paciente paciente){
        return service.actualizar(id, paciente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
