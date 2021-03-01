FROM maven:3.6.3-openjdk-14 AS MAVEN_BUILD
WORKDIR /build
COPY . /build
RUN mvn clean package

FROM openjdk:14-alpine
COPY --from=MAVEN_BUILD /build/target/user-management-service.jar /user-management-service.jar
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ENTRYPOINT ["java","-jar","user-management-service.jar"]
