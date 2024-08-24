FROM openjdk:22-jdk
ADD target/url-shortener.jar tmp/url-shortener.jar
EXPOSE 8080
CMD ["java","-jar","tmp/url-shortener.jar"]

# build- docker build -t [image-name] .
#run- docker run --env-file .env -p 8080:8080 [image-name]