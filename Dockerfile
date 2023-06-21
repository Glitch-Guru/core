FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY build/libs/issue-tracker-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "./issue-tracker-0.0.1-SNAPSHOT.jar"]
