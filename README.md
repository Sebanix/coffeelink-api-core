# CoffeeLink - API Core

Este es el servicio de backend (API-Core) para la plataforma CoffeeLink.
Se encarga de la lógica de negocio pura y la gestión de la base de datos (PostgreSQL).

## Arquitectura
Proyecto implementado con Spring Boot siguiendo una arquitectura de 3 capas:
* **Controller:** Maneja las peticiones HTTP.
* **Service:** Contiene la lógica de negocio (validaciones, etc.).
* **Repository:** Maneja la comunicación con la base de datos.

## Tecnologías
* Java
* Spring Boot
* PostgreSQL