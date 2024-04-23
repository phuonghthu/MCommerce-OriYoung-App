const mongoose = require("../common/configdb");
const userSchema = new mongoose.Schema({
  email: {
    type: String,
    required: false,
    unique: true,
  },
  phone: {
    type: String,
    required: true,
    unique: true,
  },
  password: {
    type: String,
    required: true,
  },
  name: {
    type: String,
    required: false,
  },
  birthday: {
    type: Date,
    require: false,
  },
  role: {
    type: String,
    enum: ["admin", "user"],
    default: "user",
  },
  isDeleted: {
    type: Boolean,
    default: false,
  },
  avatar: {
    type: String,
    default: "/images/avatar.png",
  },
  created_at: {
    type: Date,
    default: Date.now,
  },
  updated_at: {
    type: Date,
    required: false,
  },
});
const User = mongoose.model("User", userSchema);
module.exports = User;
