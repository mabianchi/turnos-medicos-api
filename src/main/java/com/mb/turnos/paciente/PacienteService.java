package com.mb.turnos.paciente;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository){
        this.repository = repository;
    }

    public List<Paciente> listar(){
        return repository.findAll();
    }

    public Paciente buscarPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado " + id));
    }

    @Transactional
    public Paciente crear(Paciente paciente){
        repository.findByCedula(paciente.getCedula()).ifPresent(p -> {
            throw new IllegalStateException("Ya existe un paciente con Cedula " + paciente.getCedula());
        });
        return repository.save(paciente);
    }
}
