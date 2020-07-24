# Use an official Python runtime as a parent image
FROM mysql:5.7

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

COPY ./script.sql /docker-entrypoint-initdb.d/

# Install any needed packages specified in requirements.txt

EXPOSE 3306:3306

# Define environment variable
ENV MYSQL_ROOT_PASSWORD q

