FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV DB_URL=jdbc:postgresql://postgres:5432/proposta
ENV DB_USERNAME=keycloak
ENV DB_PASSWORD=password
ENV DB_PLATFORM=postgres
ENV JPA_DATABASE=POSTGRESQL
ENV JPA_SHOW_SQL=false
ENV JPA_DDL_AUTO=update
ENV DB_DRIVER=org.postgresql.Driver
ENV STATUS_API=http://analise:9999
ENV CARTAO_API=http://contas:8888
ENTRYPOINT java -jar /app.jar