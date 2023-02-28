FROM adoptopenjdk:16-jre
COPY target/CabBooking*.jar   /app.jar
CMD java -jar /app.jar