FROM azul/zulu-openjdk-alpine:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
#COPY build/libs/backend-1.0-SNAPSHOT.jar backend-1.0-SNAPSHOT.jar
#COPY build/libs/backend-1.0-SNAPSHOT.jar backend-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app.jar"]


