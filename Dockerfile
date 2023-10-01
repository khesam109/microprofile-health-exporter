FROM eclipse-temurin:17-jdk-alpine
COPY src/main/resources/* /etc/health-exporter/
COPY target/prom-microprofile-health-exporter-1.1.1-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]