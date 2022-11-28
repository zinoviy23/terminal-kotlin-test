FROM bellsoft/liberica-openjre-alpine:17.0.5

COPY build/install/terminal-playground /app

WORKDIR /app

ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

ENTRYPOINT ["./bin/terminal-playground"]