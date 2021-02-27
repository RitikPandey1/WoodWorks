const User = require("../model/userModel");
const jwt = require("jsonwebtoken");
const { promisify } = require("util");

const getToken = (payload) => jwt.sign(payload, process.env.KEY);

exports.registerUser = async (req, res, next) => {
  const { name, email, mobileNo, address, password, avatarCode } = req.body;
  const user = await User.create({
    name,
    email,
    mobileNo,
    address,
    password,
    avatarCode,
  });

  res.status(201).json({
    status: "Success",
    data: getToken({ id: user._id }),
  });
};

exports.login = async (req, res, next) => {
  const { email, password } = req.body;
  const user = await User.findOne({ email }).select("+password");
  if (!user || !(await user.checkPassword(password, user.password)))
    return res
      .status(401)
      .json({ status: "Fail", data: "email or password incorrect" });

  res.status(200).json({
    status: "Success",
    data: getToken({ id: user._id }),
  });
};

exports.firewall = async (req, res, next) => {
  let token;

  if (
    req.headers.authorization &&
    req.headers.authorization.startsWith("Bearer")
  ) {
    token = req.headers.authorization.split(" ")[1];
  }

  if (!token) {
    return res
      .status(401)
      .json({ status: "Fail", data: "Please login to get access" });
  }
  
  let decode = await promisify(jwt.verify)(token, process.env.KEY);
  const user = await User.findById(decode.id).select("+password");

  if (!user) {
    return res.status(404).json({
      status: "Fail",
      data: "Account not found ,Please create your account",
    });
  }
  req.user = user;
  next();
};
