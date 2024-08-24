FROM openjdk:22-jdk
ADD target/url-shortener.jar tmp/url-shortener.jar
ENTRYPOINT ["java","-jar","/tmp/url-shortener.jar"]