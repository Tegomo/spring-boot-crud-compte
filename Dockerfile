FROM openjdk:17
EXPOSE 8085
ADD target/Spring-Boot-CRUD-compte-0.0.1-SNAPSHOT.jar Spring-Boot-CRUD-compte-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/Spring-Boot-CRUD-compte-0.0.1-SNAPSHOT.jar"]