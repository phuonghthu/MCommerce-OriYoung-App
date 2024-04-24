const express = require("express");
const router = express.Router();
const productController = require("../controllers/productController");

router.post("/add-product", productController.addProduct);
router.delete("/delete-product/:productCode", productController.deleteProduct);
//using query params to filter products by category and price range
router.get("/get-products-by-category", productController.getProductByCategory);
//get detail product
router.get("/get-product-detail/:id", productController.getProductDetail);
module.exports = router;
