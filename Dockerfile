FROM payara/server-full:5.2022.5-jdk17

# Copy your WAR file into the deployment folder
COPY OnlineLibrary.war $DEPLOY_DIR

# Expose the default HTTP port
EXPOSE 8080

# Start Payara (which is compatible with Jakarta EE)
CMD ["asadmin", "start-domain", "-v"]

