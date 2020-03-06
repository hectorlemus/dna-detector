# DNA Detector   

Este proyecto es un ejemplo para desplegar  y conectar una aplicación de
Spring Boot con Google Cloud - Sql Cloud.

## Requerimientos:

Para poder desplegar la aplicación es necesario tener instalado

* Java 8
* Maven 
* Google cloud sdk

Así mismo, 

* Crear un proyecto en Google cloud platform.
* Tener una instancia de PostgreSQL en la misma plataforma (Configurar el usuario y la base de datos).
* También configurar AppEngine para el proyecto.

## Configuraciones

Para poder desplegar la aplicación es necesario realizar algunas configuraciones: 

#### pom.xml 
En la parte de los plugins configurar el `project-id` del plugin `appengine-maven-plugin`

#### appengine-web.xml

Configurar la etiqueta `application` con el `project-id` correspondiente.

#### application.properties

Poner el nombre de la base de datos  
`spring.cloud.gcp.sql.database-name=`  

Este dato se obtiene de la instancia de SQL  
`spring.cloud.gcp.sql.instance-connection-name=`  

Poner el projectId correspondiente  
`spring.cloud.gcp.project-id=`  

Usuario y password de la base de datos  
`spring.datasource.username=`  
`spring.datasource.password=`

Configurar esta propiedad segun necesidades (`none` para producción)  
`spring.jpa.hibernate.ddl-auto=none`

Firmante del token para JWT  
`config.jwtIssuer=`

Secret con el cual se va a firmar el token  
`config.jwtSecretKey=`

Tiempo de vida en milisegundos del token   
`config.jwtExpirationDate=`


### Ejecutar proyecto en modo local
`mvn package appengine:run`

Le proyecto se despliega en el puerto 8080

### Desplegar poyecto en google cloud 
`mvn package appengine:deploy`

### Crear usuario para prueba:

Después de ejecutar el proyecto es necesario crear un usuario para probar la aplicación, para ello se procese ingresar
a la base de datos y en la tabla de `account` se registra el usuario que necesitamos (usar `Bcrypt` para generar el password)

### Probando api rest

Realizar el una petición `POST` a la url `http://localhost:8080/login`
y en el cuerpo de la petición mandar el siguiente json (credenciales sgun configuradas)
```
{
	"username": "user",
	"password": "mypassword"
}
``` 
 
 La respuesta de la petición tendrian que ser un token valido. 

