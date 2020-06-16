FROM openjdk:8
ADD target/auth-server.jar auth-server.jar
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "auth-server.jar"]