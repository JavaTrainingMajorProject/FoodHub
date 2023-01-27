FROM openjdk:17
ADD target/Food-service-0.0.1-SNAPSHOT.jar Food-service-0.0.1-SNAPSHOT.jar
EXPOSE 8001
ENTRYPOINT ["java", "-jar" , "Food-service-0.0.1-SNAPSHOT.jar"]