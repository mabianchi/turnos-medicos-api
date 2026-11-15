# API de Turnos Médicos


API REST para la gestión de turnos médicos. Por ahora permite registrar, modificar y eliminar pacientes.

> Proyecto de aprendizaje en evolución:
> A futuro permitirá gestionar turnos médicos para los pacientes y los médicos.

## Tecnologías

- Java 21
- Spring Boot 4.1 (Web MVC, Data JPA, Validation)
- H2 (base de datos en memoria)
- springdoc-openapi (Swagger UI)
- JUnit 5 y Mockito

## Funcionalidades

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/pacientes` | Listar todos los pacientes |
| GET | `/api/pacientes/{id}` | Buscar un paciente por su id |
| GET | `/api/pacientes/cedula/{cedula}` | Buscar un paciente por su cédula |
| POST | `/api/pacientes` | Crear un paciente |
| PUT | `/api/pacientes/{id}` | Actualizar datos de un paciente por su id |
| DELETE | `/api/pacientes/{id}` | Eliminar un paciente por su id |


Errores con códigos HTTP estándar: 
- **400 Bad Request**: los datos enviados no son válidos (por ejemplo, nombre vacío o email mal formado). La respuesta indica qué campo falló.
- **404 Not Found**: el paciente buscado no existe.
- **409 Conflict**: ya existe otro paciente con esa cédula.


## Cómo ejecutarlo

Requisitos: 
- JDK 21
- Git (Para clonar el repo)

```bash
git clone https://github.com/mabianchi/turnos-medicos-api.git
cd turnos-medicos-api
./mvnw spring-boot:run
```

Con la aplicación levantada:

- Swagger UI: http://localhost:8080/swagger-ui.html
- Consola H2: http://localhost:8080/h2-console (URL `jdbc:h2:mem:turnosdb`, usuario `sa`, sin contraseña)

## Tests

```bash
./mvnw test
```

Tests unitarios del servicio de pacientes con JUnit 5 y Mockito: 
reglas de negocio (cédula única), casos de error (paciente inexistente) 
y caminos felices. Los tests del controlador están en la hoja de ruta.

## Hoja de ruta

- [x] API REST de pacientes (CRUD + búsqueda por cédula)
- [x] Manejo de errores, validaciones y DTOs
- [x] Documentación con Swagger
- [ ] Tests del controlador (`@WebMvcTest`)
- [ ] Gestión de médicos y turnos
- [ ] Docker: imagen de la aplicación y `docker compose` con SQL Server
- [ ] Migraciones de base de datos con Flyway
- [ ] Despliegue en OpenShift
- [ ] CI/CD con GitHub Actions

## Forma de trabajo

- El proyecto sigue **GitHub Flow**: cada cambio se desarrolla en una rama 
corta (`feature/...`, `fix/...`, `docs/...`) y se integra a `main` mediante 
un Pull Request con descripción de lo que cambia y cómo se probó.
