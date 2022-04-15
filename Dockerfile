FROM openjdk
WORKDIR chat
ADD jar/job4j_chat-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -jar app.jar
