# Build
FROM maven:3-openjdk-17-slim as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN mvn clean install -DskipTests

# Run 
FROM openjdk:17-jdk-alpine
COPY --from=build /usr/app/target/*.jar /app/payment.jar
ENTRYPOINT java -jar /app/payment.jar