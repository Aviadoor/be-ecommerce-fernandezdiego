# Imagen base ligera con Java 17 para ejecucion en Linux
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR compilado por Maven al contenedor
COPY target/be-ecommerce-fernandezdiego-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080 del API REST
EXPOSE 8080

# Comando de arranque del contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]