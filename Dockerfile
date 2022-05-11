#######################################################################
# this file creates a container running the application inside wildfly
#
# Authors: Daniel Mehlber,
#######################################################################

# Unfortunately, there is no wildfly image using jakarta, so I have to build one myself

# 1) Pull openjdk and install wget for wildfly download
FROM openjdk:11
RUN apt update
RUN apt install -y wget

# 1.1) download wildfly server (jakarta version)
RUN wget https://github.com/wildfly/wildfly/releases/download/26.1.0.Final/wildfly-preview-26.1.0.Final.tar.gz

# 1.2) Extract and install wildfly server
RUN mkdir /opt/jboss
RUN tar -xf *.tar.gz -C /opt/jboss/
RUN mv /opt/jboss/wildfly-preview-26.1.0.Final /opt/jboss/wildfly

# 2) Copy generated war file into deployment directory of wildfly
COPY target/*.war /opt/jboss/wildfly/standalone/deployments/ROOT.war

# 3) Copy custom configuration into wildfly
COPY wildfly-config/*.xml /opt/jboss/wildfly/standalone/configuration/

# 3) Standard http access port
EXPOSE 8080

# 4) Admin interface
EXPOSE 9990

# 5) Add admin user
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

# Run wildfly server
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "--server-config=standalone.xml"]