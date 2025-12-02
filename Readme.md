# Gestión de Empleados con Spring Boot  
**Evidencia GA7-220501096-AA3-EV01** – Codificación de módulos Stand-alone, web y móvil con Frameworks de Java

Este proyecto implementa una **API REST** para gestionar empleados (CRUD: Crear, Leer, Actualizar, Eliminar) utilizando **Spring Boot 3.3.5**, **JPA**, **MySQL** y **Maven**, cumpliendo con los lineamientos del componente formativo **“Frameworks para construcción de aplicaciones con JAVA”**.

## 📌 Características

- ✅ **CRUD completo** de empleados
- ✅ Validación de correo único
- ✅ Arquitectura en capas (Controlador, Servicio, Repositorio, Modelo)
- ✅ Conexión segura a base de datos (credenciales en `application.properties`)
- ✅ Compatible con Java 17+
- ✅ Listo para probar con Postman o cualquier cliente HTTP

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión |
|-----------|--------|
| ☕Java | 17 |
| 🍃Spring Boot | 3.3.5 |
| 🍃Spring Web | `spring-boot-starter-web` |
|🍃 Spring Data JPA | `spring-boot-starter-data-jpa` |
|🐬 MySQL | 8.0+ |
| Maven | 3.9+ |
| Tomcat embebido | 10.1 |

## 🗃️ Requisitos previos

1. **Java 17** instalado
2. **MySQL 8.0+** en ejecución
3. Base de datos llamada `empresa`
4. Tabla `empleados` creada (ver script abajo)

## 📦 Configuración de la base de datos

Ejecuta este script en MySQL:

```sql
CREATE DATABASE IF NOT EXISTS empresa;
USE empresa;

CREATE TABLE empleados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    correo VARCHAR(120) UNIQUE NOT NULL,
    salario DECIMAL(10,2) NOT NULL
);

⚙️ Configuración del proyecto
Clona este repositorio.
Abre el archivo src/main/resources/application.properties y verifica las credenciales:

spring.datasource.url=jdbc:mysql://localhost:3306/empresa
spring.datasource.username=root
spring.datasource.password=tu_contraseña

▶️ Cómo ejecutar
Opción 1: Con Maven (recomendado)

mvn spring-boot:run

Opción 2: Empaquetar y ejecutar

mvn clean package
java -jar target/*.jar

La aplicación iniciará en:

👉 http://localhost:8080

🌐 Endpoints de la API
| Método | Endpoint              | Descripción                |
| ------ | --------------------- | -------------------------- |
| POST   | `/api/empleados`      | Crear nuevo empleado       |
| GET    | `/api/empleados`      | Listar empleados           |
| GET    | `/api/empleados/{id}` | Buscar empleado por ID     |
| PUT    | `/api/empleados/{id}` | Actualizar empleado por ID |
| DELETE | `/api/empleados/{id}` | Eliminar empleado por ID   |


Ejemplo de cuerpo para POST o PUT:

{
  "nombre": "Alisson Campos",
  "correo": "ali@example.com",
  "salario": 3500000
}

📁 Estructura del proyecto

src/
├── main/
│   ├── java/
│   │   └── com.example.demo_spring/
│   │       ├── DemoSpringApplication.java  # Clase principal
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       └── application.properties
└── pom.xml

📏 Estándares de codificación aplicados
Nombres de variables y métodos: camelCase
Nombres de clases: PascalCase
Paquetes: en minúsculas y jerárquicos (com.example.demo_spring)
Encapsulamiento: atributos private con getters/setters
Responsabilidad única: cada clase tiene un propósito claro
📎 Entrega académica
Este proyecto cumple con la evidencia GA7-220501096-AA3-EV01 del programa Análisis y Desarrollo de Software del SENA.

🔗 Enlace de repositorio
Este repositorio está vinculado a la entrega académica.


---

🔹 **Autor:** Mónica Ismelia Cañas Reyes  
🔹 **Programa:** Tecnólogo en Análisis y Desarrollo de Software  
🔹 **Institución:** SENA — 2025

---
