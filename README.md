# FOROHUB 🗣️

## Comenzando 🚀

El objetivo principal del reto "ForoHub" es replicar el funcionamiento de un foro a nivel de back end. Para ello, se creará una API REST utilizando Spring, lo que permitirá entender y desarrollar las estructuras y procesos necesarios para que un foro funcione correctamente desde el lado del servidor.

### Funcionalidades del Proyecto

Nuestra API se centrará específicamente en los tópicos, y debe permitir a los usuarios:

- Crear un nuevo tópico
- Mostrar todos los tópicos creados
- Mostrar un tópico específico
- Actualizar un tópico
- Eliminar un tópico

## Pasos para lograr el CHALLENGE 🔧

1. **Crear repositorio en GitHub**: Iniciar el proyecto creando un repositorio en GitHub.
2. **Crear la base de datos con MySQL**: Configurar y crear la base de datos utilizando MySQL.
3. **Configurar el entorno Java y Spring**: Utilizar [Spring Initializr](https://start.spring.io/) y el entorno de desarrollo IntelliJ IDEA para configurar el proyecto.
4. **Desarrollar las reglas de negocio**: Implementar la lógica necesaria para las operaciones del foro.
5. **Implementar seguridad en la API**: Utilizar [Spring Security](https://spring.io/projects/spring-security) para proteger la API.
6. **Probar la API**: Realizar pruebas exhaustivas para asegurar el correcto funcionamiento de la API.

## Configuración del archivo `application.properties` 🛠️

En el archivo `application.properties`, se utilizaron variables de entorno para la configuración de la base de datos:

```properties
spring.datasource.url = jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

- `DB_HOST`: es el host de la base de datos.
- `DB_NAME`: es el nombre de la base de datos.
- `DB_USER`: es el usuario para ingresar a la base de datos.
- `DB_PASSWORD`: es la contraseña para poder ingresar a la base de datos.

## Construido con las siguientes herramientas 🛠️

- Spring - Framework de aplicaciones
- Spring Security - Seguridad en aplicaciones Spring
- MySQL - Sistema de gestión de bases de datos
- IntelliJ IDEA - IDE (Entorno de desarrollo integrado)

## Autor ✒️

- **Jorge Arturo Barahona de la Cruz** - [jb-rgb](https://github.com/jb-rgb)