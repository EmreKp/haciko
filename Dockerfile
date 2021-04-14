FROM openjdk:11 as builder
WORKDIR application
ARG JAR_FILE=target/haciko-0.1.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
RUN java -Djarmode=layertools -jar app.jar extract

FROM openjdk:11
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]