FROM openjdk:8
ADD target/currency-conversion-service.jar currency-conversion-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "currency-conversion-service.jar"]