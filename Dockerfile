FROM eclipse-temurin:19-jre-jammy
COPY /target/classes /app
WORKDIR /app
EXPOSE 3306
ENTRYPOINT ["java", "-cp", "com.example.carthief.CarThiefApplication"]