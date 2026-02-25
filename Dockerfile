# Estágio de Build - Já vem com Maven 3.9.6 e Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
# -DskipTests evita falhas se o banco não estiver disponível no momento do build
RUN mvn clean package -DskipTests

# Estágio de Execução - Imagem leve e oficial do Java 21
FROM eclipse-temurin:21-jdk-jammy
# Corrigido para referenciar o nome do estágio 'build' corretamente
COPY --from=build /target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx300m", "-Xss512k", "-jar", "app.jar"]