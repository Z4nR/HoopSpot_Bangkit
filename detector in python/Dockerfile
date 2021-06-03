FROM python:3.7-slim-buster

# Create an app directory in the docker
WORKDIR /app

# Copy the requirement.txt. 
COPY requirement.txt ./

# Install requirement dependencies.
RUN pip install -r requirement.txt

# Copy local code to the container image.
COPY . ./

ENV APP_HOME=main

# Run the server
CMD exec gunicorn --bind :$PORT --workers 1 --threads 8 --timeout 0 main:app