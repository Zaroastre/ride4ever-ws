FROM ubuntu:latest

RUN apt update
RUN apt full-upgrade -y
RUN apt install openjdk-14-jre -y

RUN mkdir /opt/ineo
RUN mkdir /opt/ineo/ride4ever

WORKDIR /opt/ineo/ride4ever

COPY "releases/ride4ever-ws-LATEST.jar" .

EXPOSE 9600
EXPOSE 9601
EXPOSE 9602

ENTRYPOINT [ "java", "-jar", "ride4ever-ws-LATEST.jar", "-Dlogging.properties=/root/.ineo/ride4ever/logging.properties" ]