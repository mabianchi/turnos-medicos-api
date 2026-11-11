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

    public Paciente buscarPorCedula(String cedula){
        return repository.findByCedula(cedula).orElseThrow(() -> new IllegalArgumentException("Paciente con cédula " + cedula + " no encontrado."));
    }

    @Transactional
    public Paciente crear(Paciente paciente){
        repository.findByCedula(paciente.getCedula()).ifPresent(p -> {
            throw new IllegalStateException("Ya existe un paciente con Cédula " + paciente.getCedula());
        });
        return repository.save(paciente);
    }

    @Transactional
    public Paciente actualizar(Long id, Paciente datos) {
        Paciente existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado " + id));

        //Si cambia la cedula, verificar que no la tenga otro paciente
        if(!existente.getCedula().equals(datos.getCedula())){
            repository.findByCedula(datos.getCedula()).ifPresent(p -> {
                throw new IllegalStateException("Ya existe un paciente con cédula " + datos.getCedula());
            });
        }

        existente.setNombre(datos.getNombre());
        existente.setApellido(datos.getApellido());
        existente.setEmail(datos.getEmail());
        existente.setCedula(datos.getCedula());

        return existente;
    }

    @Transactional
    public void eliminar(Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un paciente con id " + id));
        repository.delete(paciente);
    }
}
