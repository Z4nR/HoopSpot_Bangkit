const express = require('express');
const app = express();

const appRoute = require('./src/routes/route-capstone');
app.use('/', appRoute);

app.listen(8080, ()=>{
    console.log('Server Berjalan di Port : 8080');
});