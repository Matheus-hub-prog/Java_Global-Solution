# Etapa 1: Construir o projeto com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Roda o clean package pulando testes para ser mais rápido
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem leve para rodar
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copia os arquivos gerados pelo Quarkus (estrutura fast-jar)
COPY --from=build /app/target/quarkus-app/lib/ ./lib/
COPY --from=build /app/target/quarkus-app/*.jar ./
COPY --from=build /app/target/quarkus-app/app/ ./app/
COPY --from=build /app/target/quarkus-app/quarkus/ ./quarkus/

# Expõe a porta 8080
EXPOSE 8080
# Comando para rodar
CMD ["java", "-jar", "quarkus-run.jar"]