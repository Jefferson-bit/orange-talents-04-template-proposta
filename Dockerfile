FROM openjdk:11
VOLUME /cdc
RUN mvnw clean package
EXPOSE 8080
ADD ./target/proposta.jar proposta.jar
ENTRYPOINT ["java", "-jar", "proposta.jar"]
