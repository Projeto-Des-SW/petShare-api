# Usa a imagem do OpenJDK 17 para rodar o backend Java
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR do backend para dentro do container (Ajuste Maven ou Gradle)
COPY target/petshare-0.0.1-SNAPSHOT.jar app.jar
# COPY build/libs/petshare-backend.jar app.jar  # Para Gradle

# Expõe a porta do backend
EXPOSE 8181

# Comando para rodar o backend
CMD ["java", "-jar", "app.jar"]
