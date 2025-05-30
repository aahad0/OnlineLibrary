FROM eclipse-temurin:17-jdk

# Install GlassFish
RUN apt-get update && \
    apt-get install -y curl unzip && \
    curl -L -o glassfish.zip https://download.eclipse.org/glassfish/glassfish-6.2.5.zip && \
    unzip glassfish.zip -d /opt && \
    rm glassfish.zip

ENV GLASSFISH_HOME=/opt/glassfish6
ENV PATH=$GLASSFISH_HOME/bin:$PATH

# COPY the correct WAR file
COPY OnlineLibrary.war $GLASSFISH_HOME/glassfish/domains/domain1/autodeploy/

EXPOSE 8080

CMD ["asadmin", "start-domain", "-v"]
