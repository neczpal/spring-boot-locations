#FROM adoptopenjdk:11-jre-hotspot
#RUN mkdir /opt/app
#COPY target/locations-spring-0.0.1-SNAPSHOT.jar /opt/app/locations.jar
#CMD ["java", "-jar", "/opt/app/locations.jar"]

FROM adoptopenjdk:11-jre-hotspot as builder
WORKDIR application
COPY target/locations-spring-0.0.1-SNAPSHOT.jar locations.jar
RUN java -Djarmode=layertools -jar locations.jar extract

FROM adoptopenjdk:11-jre-hotspot
WORKDIR application
COPY wait-for-it/wait-for-it.sh wait-for-it.sh
RUN chmod +x ./wait-for-it.sh
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]