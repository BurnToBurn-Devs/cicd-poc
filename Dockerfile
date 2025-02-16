FROM krmp-d2hub-idock.9rum.cc/goorm/gradle:jdk17

# Remove existing Gradle installation (adjust paths as needed)
RUN rm -rf /opt/gradle /usr/local/gradle && \
    rm -f $(which gradle) || true

# Install required packages
RUN apt-get update -y && apt-get install -y \
    curl wget zip unzip vim net-tools htop

# Install SDKMAN, then install Java 21.0.4-librca and Gradle 8.12, setting both as default
RUN curl -s "https://get.sdkman.io" | bash && \
    bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
             sdk install java 21.0.4-librca && sdk default java 21.0.4-librca && \
             sdk install gradle 8.12 && sdk default gradle 8.12"

# Set JAVA_HOME and update PATH to include SDKMAN-installed Gradle
ENV JAVA_HOME=/root/.sdkman/candidates/java/current
ENV PATH=$JAVA_HOME/bin:/root/.sdkman/candidates/gradle/current/bin:$PATH

# Set working directory
WORKDIR /app

# Copy only necessary files for the Gradle Spring Boot project
COPY build.gradle /app/
COPY settings.gradle /app/
COPY gradlew /app/
COPY gradlew.bat /app/
COPY gradle /app/gradle
COPY src /app/src

# Expose the application port
EXPOSE 8080

# Start the Spring Boot app via the Gradle wrapper using the SDKMAN-installed Gradle
CMD ["sh", "-c", "gradle --version && gradle -Dorg.gradle.java.installations.auto-detect=false -Dorg.gradle.java.home=/root/.sdkman/candidates/java/current -Dhttp.proxyHost=krmp-proxy.9rum.cc -Dhttp.proxyPort=3128 -Dhttps.proxyHost=krmp-proxy.9rum.cc -Dhttps.proxyPort=3128 bootRun"]
