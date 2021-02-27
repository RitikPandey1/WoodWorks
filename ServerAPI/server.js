const express = require("express");
const dotenv = require("dotenv");
const mongoose = require("mongoose");

const { registerUser, login } = require("./src/User");

const app = express();

dotenv.config({ path: "./config.env" });

(async () => {
  await mongoose.connect(process.env.DATABASE, {
    useNewUrlParser: true,
    useCreateIndex: true,
    useFindAndModify: false,
    useUnifiedTopology: true,
  });
  console.log("-- Database connected --");
})();

app.use(express.json());
//routes
const initial = "/woodworks/api";
app.post(`${initial}/register`, registerUser);
app.post(`${initial}/login`, login);

app.listen(process.env.PORT, () =>
  console.log(`-- server running PORT:${process.env.PORT} ....`)
);
