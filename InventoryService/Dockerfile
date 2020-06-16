FROM openjdk:8
ADD target/inventory-service.jar inventory-service.jar
EXPOSE 9093
ENTRYPOINT ["java", "-jar", "inventory-service.jar"]