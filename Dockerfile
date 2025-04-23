FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN ./gradlew --no-daemon build

ENV JAVA_OPTS "-Xmx512M -Xms512M"

CMD java -jar build/libs/min-value-extractor-0.0.1-SNAPSHOT.jar
