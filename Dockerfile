FROM ubuntu

RUN apt-get update
RUN apt-get -y upgrade

# Install Java 8
RUN apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer

# Set JAVA_HOME
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

ADD /build/libs/Time-Serialize-1.0.jar /apps/
WORKDIR /apps

ENTRYPOINT ["java", "-jar", "Time-Serialize-1.0.jar"]
