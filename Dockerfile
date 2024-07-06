# Dockerfile for inverte-ya

#FROM openjdk:17-jdk-alpine
#WORKDIR /app
#COPY ./target/inverte-ya-0.0.1-SNAPSHOT.jar inverte-ya.jar
#CMD ["java", "-jar", "inverte-ya.jar"]


# Usar una imagen base de Java
FROM openjdk:17-jdk-alpine

# Crear directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR compilado
COPY ./target/inverte-ya-0.0.1-SNAPSHOT.jar inverte-ya.jar

# Descargar e instalar el Cloud SQL Auth Proxy
RUN apk add --no-cache wget \
    && wget https://dl.google.com/cloudsql/cloud_sql_proxy.linux.amd64 -O cloud_sql_proxy \
    && chmod +x cloud_sql_proxy

# Exponer el puerto
EXPOSE 8081

# Comando para iniciar el proxy y la aplicación
CMD ./cloud_sql_proxy -instances="prueba-invertirya:us-central1:ms-invertiryadb=tcp:5432" & \
    java -jar inverte-ya.jar


