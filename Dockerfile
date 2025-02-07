# Estágio 1: Build (compilação)
FROM eclipse-temurin:17-jdk-jammy as builder

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Executa a build do projeto com o Gradle
RUN ./gradlew shadowJar

# Estágio 2: Execução (imagem final)
FROM eclipse-temurin:17-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado no estágio de build
COPY --from=builder /app/build/libs/Portifolio-1.0-SNAPSHOT-all.jar app.jar

# Copia o arquivo .env (se necessário)
COPY .env /app/.env

# Expõe a porta 8080
EXPOSE 8080

# Comando para executar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]