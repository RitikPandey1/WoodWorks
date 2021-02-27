const express = require("express");
const dotenv = require("dotenv");
const mongoose = require("mongoose");

const { registerUser, login } = require("./src/User");
const { addToCart, removeFromCart } = require("./src/Cart");

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

app.post("/user/register", registerUser);
app.post("/user/login", login);

app.get("/cart/add/:pid", addToCart);
app.get("/cart/remove/:id", removeFromCart);
app.get("/cart/item/:id/qty/:op", addToCart);



app.listen(process.env.PORT, () =>
  console.log(`-- server running PORT:${process.env.PORT} ....`)
);
