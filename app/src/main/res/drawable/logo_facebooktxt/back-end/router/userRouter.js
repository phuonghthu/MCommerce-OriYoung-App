const express = require("express");
const router = express.Router();
const userController = require("../controllers/userController");

// router api user
router.post("/login", userController.handleLogin);
router.post("/register", userController.handleRegister);
router.put("/update-info", userController.updateInfoUser);
router.get("/get-info-mine", userController.getInfoMine);
router.put("/change-password", userController.changePassword);
module.exports = router;
