FROM openjdk:11
VOLUME /proposta
EXPOSE 8080
COPY ./target/proposta.jar proposta.jar
ENTRYPOINT ["java", "-jar", "proposta.jar"]
