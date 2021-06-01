module.exports = {
    /* Local Database */
    multipleStatements  : true,
    host                : 'localhost',
    user                : 'root',
    password            : '',
    database            : 'capstone'
    
    /* Cloud Database
    user: process.env.DB_USER,
    password: process.env.DB_PASS,
    database: process.env.DB_NAME,
    socketPath: `/cloudsql/${process.env.INSTANCE_CONNECTION_NAME}` */
};