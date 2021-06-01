const express = require("express");
const mysqll = require("mysql2");
const appRoute = require('./routes/route-capstone');
//const dotenv = require("dotenv");

const app = express();
const port = process.env.PORT || 8080;

app.use('/', appRoute);
app.listen(port, ()=>{
    console.log(`Rest API listening on port ${port}`);
});

/* app.use(express.json()); --Development Interests--
app.listen(port, () => {
  console.log(`Rest API listening on port ${port}`);
});
app.get("/", async (req, res) => {
  res.json({ status: "Rest API! Ready to roll!" });
});
app.get("/:breed", async (req, res) => {
  const query = "SELECT * FROM breeds WHERE name = ?";
  pool.query(query, [ req.params.breed ], (error, results) => {
    if (!results[0]) {
      res.json({ status: "Not found!" });
    } else {
      res.json(result[0]);
    }
  });
});
const pool = mysql.createPool({
  user: process.env.DB_USER,
  password: process.env.DB_PASS,
  database: process.env.DB_NAME,
  socketPath: `/cloudsql/${process.env.INSTANCE_CONNECTION_NAME}`,
});
app.post("/", async (req, res) => {
  const data = {
    name: req.body.name,
    type: req.body.type,
    lifeExpectancy: req.body.lifeExpectancy,
    origin: req.body.origin
  }
  const query = "INSERT INTO breeds VALUES (?, ?, ?, ?)";
  pool.query(query, Object.values(data), (error) => {
    if (error) {
      res.json({ status: "failure", reason: error.code });
    } else {
      res.json({ status: "success", data: data });
    }
  });
}); */