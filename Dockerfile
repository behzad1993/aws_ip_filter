FROM openjdk:21
LABEL authors="behzadkarimi"

ARG JAR_FILE=target/*.jar
COPY ./target/aws_ip_filter-0.0.1-SNAPSHOT.jar ipFilter.jar
ENTRYPOINT ["java", "-jar", "/ipFilter.jar"]