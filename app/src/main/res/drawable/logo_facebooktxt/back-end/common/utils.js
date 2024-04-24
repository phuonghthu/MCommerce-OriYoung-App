const bcrypt = require("bcrypt");

const message = {
  SUCCESS: "Successful",
  ERROR: "Error",
  NOTFOUND: "Data Not found",
};

const code = {
  SUCCESS: 200,
  ERROR: 404,
  BAD_REQUEST: 400,
  ERROR_SERVER: 500,
};

const getUserNameByEmail = (email) => {
  let index = email.indexOf("@");
  return email.substring(0, index);
};

const createResponseModel = (code, message, data = null) => {
  return {
    statusCode: code,
    message: message,
    totalRecord: 0,
    data: data,
  };
};

const createSuccessResponseModel = (totalRecord = 1, data) => {
  return {
    statusCode: 200,
    message: "Successful",
    totalRecord: totalRecord,
    data: data,
  };
};

const createErrorResponseModel = (message, data = false) => {
  return {
    statusCode: 400,
    message: message,
    totalRecord: 0,
    data: data,
  };
};

const hashPassword = (password) => {
  const salt = bcrypt.genSaltSync(10);
  const hash = bcrypt.hashSync(password, salt);
  return hash;
};

const validatePassword = async (password, hashPassword) => {
  return await bcrypt.compare(password, hashPassword);
};

const generateRandomToken = (length) => {
  const characters =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  let token = "";

  for (let i = 0; i < length; i++) {
    const randomIndex = Math.floor(Math.random() * characters.length);
    token += characters.charAt(randomIndex);
  }

  return token;
};

module.exports = {
  messageCode: message,
  statusCode: code,
  getUserNameByEmail: getUserNameByEmail,
  createResponseModel: createResponseModel,
  createSuccessResponseModel: createSuccessResponseModel,
  generateRandomToken: generateRandomToken,
  createErrorResponseModel: createErrorResponseModel,
  validatePassword: validatePassword,
  hashPassword: hashPassword,
};
