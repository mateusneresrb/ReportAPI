FROM openjdk:8-jdk

RUN apt-get update && \
    apt-get install -y wget
	
WORKDIR /app
RUN wget https://github.com/mateusneresrb/ReportAPI/releases/download/0.1.2/ReportAPI.jar

CMD ["java", "-jar", "ReportAPI.jar"]