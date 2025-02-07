# Usa uma imagem base com Java 17 (versão que você está usando no JVM Toolchain)
FROM eclipse-temurin:17-jre-jammy

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o JAR gerado pelo shadowJar para o contêiner
COPY build/libs/Portifolio-1.0-SNAPSHOT-all.jar app.jar

COPY .env /app/.env


# Expõe a porta que o Ktor está usando (geralmente 8080)
EXPOSE 8080

# Comando para executar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]