# Sistema de Gestión de Estudiantes – API REST

Este proyecto es una **API REST** desarrollada con **Spring Boot 3.4.1** y **Java 21** para la gestión de estudiantes de una institución educativa.  
Incluye validaciones de negocio, persistencia de datos en **MySQL** y carga automática de datos de prueba.

---

## Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

- Java JDK 21  
- MySQL Server 8.0 o superior  
- IntelliJ IDEA (recomendado)
  
---

## Configuración e Instalación

### 1. Preparar la Base de Datos

Abre MySQL Workbench o tu terminal de MySQL y ejecuta:

```sql
CREATE DATABASE instituto_bd;
```
### 2. Configurar el archivo application.properties
Dirígete a: src/main/resources/application.properties

Verifica y ajusta las credenciales de tu base de datos MySQL:
```properties
spring.datasource.username=tu_usuario   
spring.datasource.password=tu_password  
```
### 3. Ejecutar la aplicación
Desde el IDE:
Ejecuta la clase ProyectoApplication.java.

---

## Configuración e Instalación

La API estará disponible en:
```bash
http://localhost:8080/api/estudiante
```
Al iniciar la aplicación por primera vez, se cargarán automáticamente 5 registros de ejemplo definidos en el archivo: src/main/resources/data.sql

---

## Validaciones Implementadas

- ID: Generado automáticamente.
- Cédula: Debe tener exactamente 10 dígitos y ser única.
- Email: Formato válido (nombre@example.com) y único.
- Edad: Solo se permiten estudiantes entre 18 y 60 años.
- Carrera: Solo se aceptan: Administración, Marketing, Contabilidad, Farmacia, Desarrollo de software, Turismo.
- Formateo: Los nombres y apellidos se guardan automáticamente con la primera letra en mayúscula.
