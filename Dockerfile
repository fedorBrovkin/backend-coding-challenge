ARG DOCKER_REGISTRY=registry.access.redhat.com
FROM ${DOCKER_REGISTRY}/openjdk/openjdk-11-rhel8

USER root

ENV JAVA_OPTS="-Xmx768m -Xms256m"

ENV LANG     C.UTF-8
ENV LC_ALL   C.UTF-8
ENV LC_CTYPE C.UTF-8

RUN mkdir -p -m 775 /logs && mkdir -p -m 775 /reports

COPY target/city-suggestion-service-*.jar /city-suggestion-service.jar

ENTRYPOINT java ${JAVA_OPTS} -jar /city-suggestion-service.jar