FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -DskipTests clean package \
 && JAR="$(ls -1 target/*.jar | grep -v '^target/original-' | head -n 1)" \
 && cp "$JAR" /app/app.jar

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh","-c","exec java ${JAVA_OPTS:-} -jar app.jar"]
