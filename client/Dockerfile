FROM python

WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

COPY ./client.py /docker-entrypoint-initdb.d/

# Install any needed packages specified in requirements.txt
RUN pip install --trusted-host pypi.python.org -r requirements.txt

# Make port 80 available to the world outside this container
EXPOSE 80

# Run app.py when the container launches
CMD ["python3", "-u", "client.py"]
