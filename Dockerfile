FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8010
ADD target/*.war app.war
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.war" ]