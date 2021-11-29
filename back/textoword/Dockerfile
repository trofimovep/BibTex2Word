FROM gradle:latest
WORKDIR = /usr/app
COPY . .
RUN ./gradlew build
EXPOSE 8081
ENTRYPOINT ["java","-jar", "-Dserver.port=8081", "build/libs/textoword-0.0.1-SNAPSHOT.jar"]