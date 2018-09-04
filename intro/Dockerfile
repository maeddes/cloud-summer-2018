FROM openjdk:8-alpine
RUN mkdir -p /opt/intro
WORKDIR /opt/intro
EXPOSE 8081
COPY target/intro-0.0.1-SNAPSHOT.jar /opt/intro
CMD ["java", "-jar", "intro-0.0.1-SNAPSHOT.jar"]

