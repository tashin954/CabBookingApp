FROM adoptopenjdk:16-jre
COPY CabBooking*.jar   /app.jar
CMD java -jar /app.jar