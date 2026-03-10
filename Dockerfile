FROM openjdk:25-ea-21-jdk-slim AS layers
WORKDIR /application
COPY target/*.jar app.jar
RUN java -Djarmode=tools -jar app.jar extract --layers --destination extracted

FROM openjdk:25-ea-21-jdk-slim

VOLUME /tmp

RUN useradd -ms /bin/bash spring-user
USER spring-user

WORKDIR /application

COPY --from=layers /application/extracted/dependencies/ ./
COPY --from=layers /application/extracted/spring-boot-loader/ ./
COPY --from=layers /application/extracted/snapshot-dependencies/ ./
COPY --from=layers /application/extracted/application/ ./

RUN java -XX:ArchiveClassesAtExit=app.jsa -Dspring.context.exit=onRefresh -jar app.jar || true

ENV JAVA_CDS_OPTS="-XX:SharedArchiveFile=app.jsa -Xlog:class+load:file=/tmp/classload.log"
ENV JAVA_ERROR_FILE_OPTS="-XX:ErrorFile=/tmp/java_error.log"
ENV JAVA_HEAP_DUMP_OPTS="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp"
ENV JAVA_ON_OUT_OF_MEMORY_OPTS="-XX:+ExitOnOutOfMemoryError"
ENV JAVA_NATIVE_MEMORY_TRACKING_OPTS="-XX:NativeMemoryTracking=summary -XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics"

ENTRYPOINT ["java", \
    "-XX:+HeapDumpOnOutOfMemoryError", \
    "-XX:HeapDumpPath=/tmp", \
    "-XX:+ExitOnOutOfMemoryError", \
    "-XX:ErrorFile=/tmp/java_error.log", \
    "-XX:NativeMemoryTracking=summary", \
    "-XX:+UnlockDiagnosticVMOptions", \
    "-XX:+PrintNMTStatistics", \
    "-XX:SharedArchiveFile=app.jsa", \
    "-jar", "app.jar"]