# Sistema de Gestión de Estudiantes – API REST

Este proyecto es una **API REST** desarrollada con **Spring Boot 3.4.1** y **Java 21** para la gestión de estudiantes de una institución educativa.  
Incluye validaciones de negocio, persistencia de datos en **MySQL** y carga automática de datos de prueba.

---

## Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

- Java JDK 21  
- MySQL Server 8.0 o superior  
- IntelliJ IDEA (recomendado) o Eclipse  
- Gradle (incluido en el proyecto mediante Gradle Wrapper)

---

## Configuración e Instalación

### 1. Preparar la Base de Datos

Abre MySQL Workbench o tu terminal de MySQL y ejecuta:

```sql
CREATE DATABASE instituto_bd;
