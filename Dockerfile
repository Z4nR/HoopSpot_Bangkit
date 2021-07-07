FROM node:12-slim

# Create an app directory in the docker
WORKDIR /app

# Copy the package.json and package-lock.json. 
COPY package*.json ./

# Install production dependencies.
RUN npm install --only=production

# Copy local code to the container image.
COPY . ./

# Run the server
CMD npm run start
