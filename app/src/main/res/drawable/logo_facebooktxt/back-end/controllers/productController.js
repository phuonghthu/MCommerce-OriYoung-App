const Utils = require("../common/utils");
const Product = require("../models/Product");
const Category = require("../models/Category");
const Cart = require("../models/Cart");

const addProducts = async (req, res) => {
  try {
    let category = await Category.findOne({ name: req.body.categoryName });
    if (!category || category === null) {
      category = new Category({
        name: req.body.categoryName,
      });
    }
    const products = req.body.products; // Assuming req.body.products is an array of products
    // Validate if products is an array
    if (!Array.isArray(products)) {
      return res.json(Utils.createErrorResponseModel("Invalid products data"));
    }

    // Map each product in the array to a new product instance and save it
    const newProducts = await Promise.all(
      products.map(async (product) => {
        const { productCode, name, description, capacity, image } = product;
        const newProduct = new Product({
          productCode,
          name,
          description,
          capacitiesAndPrices: capacity,
          image,
        });
        category.products.push(newProduct);
        await newProduct.save();
      })
    );
    await category.save();

    return res.json(
      Utils.createSuccessResponseModel("Thêm sản phẩm thành công", newProducts)
    );
  } catch (err) {
    console.log(err);
    return res.json(Utils.createErrorResponseModel("Vui lòng thử lại"));
  }
};

// delete product
const deleteProduct = async (req, res) => {
  try {
    const product = await Product.findOne({
      productCode: req.params.productCode,
    });
    if (!product || product === null) {
      return res.json(Utils.createErrorResponseModel("Sản phẩm không tồn tại"));
    }
    product.isDeleted = true;
    await product.save();
    return res.json(Utils.createSuccessResponseModel(0, true));
  } catch (err) {
    console.log(err);
    return res.json(Utils.createErrorResponseModel("Vui lòng thử lại"));
  }
};

//get product by category
const getProductByCategory = async (req, res) => {
  try {
    const { categoryName, minPrice, maxPrice } = req.query;
    const category = await Category.findOne({ name: categoryName });
    if (!category || category === null) {
      return res.json(Utils.createErrorResponseModel("Danh mục không tồn tại"));
    }

    let products;
    if (typeof minPrice === "undefined" || typeof maxPrice === "undefined") {
      products = category.products;
    } else {
      products = category.products.filter(
        (product) =>
          product.capacitiesAndPrices[0].price >= minPrice &&
          product.capacitiesAndPrices[0].price <= maxPrice
      );
    }

    return res.json(
      Utils.createSuccessResponseModel(products.length, products)
    );
  } catch (err) {
    console.log(err);
    return res.json(Utils.createErrorResponseModel("Vui lòng thử lại"));
  }
};

//get detail product
const getDetailProduct = async (req, res) => {
  try {
    const product = await Product.findOne({
      _id: req.params.id,
    });
    if (!product || product === null) {
      return res.json(Utils.createErrorResponseModel("Sản phẩm không tồn tại"));
    }
    return res.json(Utils.createSuccessResponseModel(1, product));
  } catch (err) {
    console.log(err);
    return res.json(Utils.createErrorResponseModel("Vui lòng thử lại"));
  }
};

module.exports = {
  addProduct: addProducts,
  deleteProduct: deleteProduct,
  getProductByCategory: getProductByCategory,
  getProductDetail: getDetailProduct,
};
