FROM java:8
VOLUME /tmp
ADD url-min/build/libs/url-min-0.0.1-SNAPSHOT.jar url-min-0.0.1-SNAPSHOT.jar
RUN bash -c 'touch /url-min-0.0.1-SNAPSHOT.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/url-min-0.0.1-SNAPSHOT.jar"]