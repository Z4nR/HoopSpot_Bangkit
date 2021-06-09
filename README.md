# Step to run the API
1. Install [Node.js](https://nodejs.org/en/)
2. Clone this repository branch of cloud-computing using command `git clone -b cloud-computing <remote-repo>`
3. Create database capstone, you can read the `capstone.sql` file and copy the command in Mysql DBMS
4. Setting your local database config in `./src/configs/database.js`
5. Install all dependencies or modules that needed in this API `npm install`
6. Type the command to run the server of Node.js `npm run start`
7. After then you can access the API in the browser with command that I already type in `./src/routes/route-capstone.js`
# Consider you want to deploy the API into Google Cloud Platform service of Cloud Run
1. Create database in GCP using service of Cloud SQL
2. After created the database you need to create table by copy the syntax from `capstone.sql` into database Cloud SQL
3. Setting the config in `./src/configs/database.js` from local into cloud, you need commant or deleted the local setting
4. Setting the variable value in `./deploy.sh` into your configuration of cloud
5. Run the `./deploy.sh` to create a container corresponding of `./Dockerfile` and deploy it into Cloud Run
6. You can access the database of Cloud SQL value from the Rest API using url link that Cloud Run provided