# Usa una imagen base oficial de Java 21
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el 'wrapper' de Maven para poder construir el proyecto
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Descarga las dependencias
RUN ./mvnw dependency:go-offline

# Copia el resto del código fuente
COPY src ./src

# Construye la aplicación y genera el archivo .jar
RUN ./mvnw clean install

# Expone el puerto 8080 para que el mundo exterior pueda acceder a él
EXPOSE 8080

# El comando que se ejecutará cuando el contenedor arranque
ENTRYPOINT ["java", "-jar", "target/planverano-0.0.1-SNAPSHOT.jar"]