FROM openjdk:17
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/condo-rest-api-0.0.1-SNAPSHOT.jar /app/condo-rest-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/condo-rest-api-0.0.1-SNAPSHOT.jar"]
# docker run -p 8080:8080 condo-rest-api