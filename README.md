# AeroMagdalena Backend v2

Backend REST para la gestion de aerolineas, aeropuertos, vuelos, clientes, pasajeros y reservas.

Este proyecto esta construido con Spring Boot y sigue una arquitectura en N capas (API, servicios, repositorios y entidades).

## Objetivo del proyecto

Construir una API backend mantenible y escalable que permita administrar el dominio principal de una aerolinea regional:

- Aerolineas
- Aeropuertos
- Vuelos
- Clientes
- Pasajeros
- Reservas
- Usuarios y autenticacion

## Stack tecnologico

- Java 17
- Spring Boot 3.3.3
- Spring Data JPA
- Spring Security + JWT
- Spring Validation
- MapStruct para mapeo de DTOs
- PostgreSQL
- Gradle
- Docker y Docker Compose

## Arquitectura actual (N capas)

La estructura del proyecto se organiza en capas claramente separadas:

- `api/`: controladores REST (entrada HTTP, validaciones)
- `services/`: interfaz e implementacion de casos de uso y logica de negocio
- `repositories/`: acceso a datos (Spring Data JPA)
- `entities/`: modelo de persistencia JPA con validaciones y auditoría
- `dto/`: contratos de entrada/salida (records) con validaciones
- `mapper/`: transformacion entre entidades y DTOs con MapStruct
- `exception/`: excepciones personalizadas y manejador global de errores
- `security/`: configuracion de seguridad y JWT
- `config/`: configuraciones globales (CORS, etc.)

## Estructura del proyecto

```text
src/main/java/com/data/tallermodelodatos/
  api/
  config/
  dto/
  entities/
  exception/
  repositories/
  security/
  services/
```

## Como ejecutar en local

### Con Gradle (local)

1. Clona el repositorio.
2. Configura la conexion a base de datos en `src/main/resources/application.properties`.
3. Ejecuta la aplicacion con Gradle.

Comandos:

```powershell
.\gradlew.bat clean build
.\gradlew.bat bootRun
```

### Con Docker

Construccion y arranque de la aplicacion y base de datos:

```powershell
docker compose up --build
```

Detener servicios:

```powershell
docker compose down
```

La aplicacion estara disponible en `http://localhost:8081`.
La base de datos PostgreSQL estara en `localhost:5432` (o `db:5432` desde dentro de Docker).

## Patrones y mejoras implementadas

### Modulos refactorizados

#### Vuelo ✅

- Entidad con validaciones, tipos correctos (`LocalDateTime`), timestamps de auditoría
- DTOs separados: `VueloDto` (salida), `VueloCreateRequest`, `VueloUpdateRequest`
- Mapper explícito con MapStruct
- Service con `@Transactional`, validaciones de negocio
- Controller REST con respuestas HTTP estándar
- Endpoints: GET, POST, PUT, DELETE, búsquedas por origen/destino

#### Cliente ✅

- Entidad mejorada con validaciones (`@Email`, `@NotBlank`, etc.), timestamps
- DTOs: `ClienteDto` (salida), `ClienteCreateRequest`, `ClienteUpdateRequest`
- Mapper con MapStruct, ignora campos sensibles
- Service limpio con búsqueda por nombre
- Controller REST funcional
- Endpoints: GET, POST, PUT, DELETE, búsqueda por nombre

### Características comunes implementadas

- ✅ Manejo de errores unificado con `GlobalExceptionHandler`
- ✅ Respuestas estandarizadas (timestamp, status, error, message, path)
- ✅ Validaciones con `@Valid` y detalles de errores de campo
- ✅ Docker Compose completo (app + PostgreSQL)
- ✅ Variables de entorno en `application.properties`
- ✅ Transacciones y lazy loading configurado

## Endpoints principales

### Vuelo

```
GET    /api/v1/vuelos                      - Listar todos
GET    /api/v1/vuelos/{id}                 - Obtener por ID
POST   /api/v1/vuelos                      - Crear
PUT    /api/v1/vuelos/{id}                 - Actualizar
DELETE /api/v1/vuelos/{id}                 - Eliminar
GET    /api/v1/vuelos/buscar/origen        - Buscar por origen
GET    /api/v1/vuelos/buscar/destino       - Buscar por destino
```

### Cliente

```
GET    /api/v1/clientes                    - Listar todos
GET    /api/v1/clientes/{id}               - Obtener por ID
POST   /api/v1/clientes                    - Crear
PUT    /api/v1/clientes/{id}               - Actualizar
DELETE /api/v1/clientes/{id}               - Eliminar
GET    /api/v1/clientes/buscar/nombre      - Buscar por nombre
```

## Estado actual

- ✅ Docker Compose completo (app + PostgreSQL)
- ✅ Dos módulos piloto refactorizados (`Vuelo`, `Cliente`)
- ✅ Patrón N capas estandarizado y replicable
- ✅ Manejo de errores global
- ✅ Compilación exitosa

## Autores

- Anuarth Rincon
- Breiner Gonzalez
