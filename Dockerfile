FROM adoptopenjdk:11-jre-hotspot

USER root

ENV JAVA_OPTS="-Xmx768m -Xms256m"

ENV LANG     C.UTF-8
ENV LC_ALL   C.UTF-8
ENV LC_CTYPE C.UTF-8

RUN mkdir -p -m 775 /logs && mkdir -p -m 775 /reports

COPY target/city-suggestion-service-*.jar /city-suggestion-service.jar

EXPOSE 8000

ENTRYPOINT java ${JAVA_OPTS} -jar /city-suggestion-service.jar