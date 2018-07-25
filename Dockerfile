# Use an official Python runtime as a parent image
FROM java:8

# Set the working directory to /app
ADD /target/standard_tokenization-0.0.1-SNAPSHOT.jar standard_tokenization_1.0.jar

#ENTRYPOINT ["java","-jar","demo.jar"]

# Run app.py when the container launches
#CMD ["python", "pubmed_standardization.py", "-p","config.properties"]
