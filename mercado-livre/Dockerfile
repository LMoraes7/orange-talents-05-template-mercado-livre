FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/*.jar /app/mercado-livre.jar
ENTRYPOINT ["java", "-jar", "mercado-livre.jar"]
EXPOSE 8080 