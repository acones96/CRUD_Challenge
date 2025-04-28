# CRUD Project
Aserta Challenge es un proyecto desarrollado con **Spring Boot** y **Maven** que implementa un sistema CRUD para la gestión de productos. Este proyecto utiliza **MySQL** como base de datos y expone una API REST para realizar operaciones como crear, leer, actualizar y eliminar productos.

## Características

- **Framework**: Spring Boot.
- **Base de Datos**: MySQL.
- **Persistencia**: JPA (Java Persistence API).
- **Validación**: Hibernate Validator.
- **Pruebas**: JUnit 5 y Mockito.
- **Gestión de dependencias**: Maven.

## Requisitos Previos

- **Java 21** o superior.
- **Maven** (incluido en el proyecto mediante el wrapper).
- **MySQL** instalado y configurado.

## Configuración del Proyecto

### Base de Datos

Asegúrate de tener una base de datos MySQL configurada. Por defecto, el proyecto utiliza las siguientes credenciales (configuradas en `application.properties`):

```
properties
spring.datasource.url=jdbc:mysql://localhost:3306/aserta
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Puedes modificar estas configuraciones según sea necesario.

## Construcción del Proyecto
Para construir el proyecto, utiliza el wrapper de Maven incluido:
```
mvn clean install
```

## Endpoints de la API
La API REST expone los siguientes endpoints para gestionar productos:

### Productos API de Postman
* **GET /api/productos:** Obtiene todos los productos.
* **GET /api/productos/{id}:** Obtiene un producto por su ID.
* **POST /api/productos:** Crea un nuevo producto.
* **PUT /api/productos/{id}:** Actualiza un producto existente.
* **DELETE /api/productos/{id}:** Elimina un producto por su ID.