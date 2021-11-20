FROM openjdk:latest
WORKDIR = /usr/app
COPY build/libs/textoword-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar", "-Dserver.port=8081", "app.jar"]