FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--logging.file.path=c:/var/log", "--logging.file.name=hcmValidator.log"]