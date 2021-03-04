FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV DB_DRIVER=org.h2.Driver
ENV DB_URL=jdbc:h2:mem:proposta
ENV DB_USER=sa
ENV DB_PASS=
ENV JPA_DIALECT=org.hibernate.dialect.H2Dialect
ENV JPA_DDL=update
ENV JPA_SHOWSQL=true
ENV JPA_FORMATSQL=true
ENV STATUS_API=http://analise:9999
ENV CARTAO_API=http://contas:8888
ENTRYPOINT java -jar /app.jar