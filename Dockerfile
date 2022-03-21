#######################################################################
# this file creates a container running the application inside wildfly
#
# Authors: Daniel Mehlber
#######################################################################

# 1) Pull wildfly server
FROM jboss/wildfly

# 2) Copy generated war file into deployment directory of wildfly
COPY target/*.war /opt/jboss/wildfly/standalone/deployments/ROOT.war

# 3) Standard http access port
EXPOSE 8080

# 4) Admin interface
# EXPOSE 9990

# Run wildfly server
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]


