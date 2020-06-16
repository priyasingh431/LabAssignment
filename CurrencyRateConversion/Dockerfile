FROM openjdk:8
ADD target/currency-rate-conversion.jar currency-rate-conversion.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "currency-rate-conversion.jar"]