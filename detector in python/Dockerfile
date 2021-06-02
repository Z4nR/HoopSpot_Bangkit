FROM python:3.8-slim-buster

# Create an app directory in the docker
WORKDIR /app

# Copy the requirement.txt. 
COPY requirement.txt ./

# Install requirement dependencies.
RUN pip3 install -r requirement.txt

# Copy local code to the container image.
COPY . ./

ENV FLASK_APP=main2

# Run the server
CMD ["python3", "-m" , "flask", "run", "--host=0.0.0.0"]