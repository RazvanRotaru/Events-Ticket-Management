FROM openjdk

WORKDIR /app

COPY . /app

EXPOSE 8080

ARG JAR_FILE=target/events-1.0-SNAPSHOT.jar

ADD ${JAR_FILE} events-1.0-SNAPSHOT.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/events-1.0-SNAPSHOT.jar"]
