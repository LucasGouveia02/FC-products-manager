FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

EXPOSE ${PORT}

# Copie o JAR para o container
COPY target/products-manager-0.0.1-SNAPSHOT.jar /app/products-manager.jar

# Defina o comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "/app/products-manager.jar"]