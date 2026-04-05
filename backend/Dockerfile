FROM eclipse-temurin:17-jdkWORKDIR /app
COPY target/backend-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]