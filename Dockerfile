FROM maven:3.6.1-jdk-11-slim AS builder

COPY . /src
WORKDIR /src
RUN mvn package
RUN ls /src/target

FROM adoptopenjdk/openjdk11:x86_64-debian-jre-11.0.3_7
COPY --from=builder /src/target /apps
COPY application.properties /apps/application.properties

WORKDIR /apps
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "apiPhoto-1.0.0.jar", "--spring.config.location=file:/apps/application.properties"]
