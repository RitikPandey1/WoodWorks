const User = require("../model/userModel");
const jwt = require("jsonwebtoken");
const { promisify } = require("util");

const getToken = (payload) => jwt.sign(payload, process.env.KEY);

exports.registerUser = async (req, res, next) => {
  const { name, email, mobileNo, address, password } = req.body;
  const user = await User.create({
    name,
    email,
    mobileNo,
    address,
    password,
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
    return next(new AppError("Fail", "email or password incorrect", 401));
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

  if (!token)
    return next(new AppError("Fail", "Please login to get access", 401));

  let decode = await promisify(jwt.verify)(token, process.env.KEY);
  const user = await User.findById(decode.id).select("+password");
  if (!user)
    return next(
      new AppError("Fail", "Account not found ,Please create your account", 404)
    );

  req.user = user;
  next();
};
