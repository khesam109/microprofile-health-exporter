FROM eclipse-temurin:17-jdk-alpine
COPY src/main/resources/* /etc/healthexporter/
COPY target/prom-microprofile-health-exporter-1.0.0-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]