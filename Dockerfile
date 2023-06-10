FROM amazoncorretto:17.0.6

VOLUME /tmp

RUN yum update -y

COPY build/libs/samplesecretsmanagerservice-0.1.jar /app/

USER nobody

ENTRYPOINT ["java", "-Dlog4j2.formatMsgNoLookups=true", "-jar", "/app/samplesecretsmanagerservice-0.1.jar"]
