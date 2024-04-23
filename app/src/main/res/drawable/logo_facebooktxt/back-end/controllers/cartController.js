const Utils = require("../common/utils");
const Cart = require("../models/Cart");
const Product = require("../models/Product");

const checkUserLoggedIn = (req, res, next) => {
  if (!req.session.user) {
    return res.json(Utils.createErrorResponseModel("Vui lòng đăng nhập."));
  }
  next();
};

const getCartByUserId = async (req, res) => {
  try {
    let cart = await Cart.findOne({ userId: req.session.user });
    if (!cart || cart === null) {
      cart = new Cart({ userId: req.session.user, products: [] });
      await cart.save();
    }

    // Get product information based on productId and add it to products, and also remove productId from that object.
    const productPromises = cart.products.map(async (product) => {
      const foundProduct = await Product.findById(product.productId);
      // Update product with productInfo and remove productId
      return { ...product.toObject(), productInfo: foundProduct.toObject() };
    });
    const products = await Promise.all(productPromises);

    return res.json(
      Utils.createSuccessResponseModel(products.length, products)
    );
  } catch (err) {
    console.log(err);
    return res.json(Utils.createErrorResponseModel("Vui lòng thử lại"));
  }
};

const addProductToCart = async (req, res) => {
  try {
    const { productId, capacity, quantity } = req.body;
    const cart =
      (await Cart.findOne({ userId: req.session.user })) ||
      new Cart({ userId: req.session.user });

    const existingProduct = cart.products.find(
      (product) =>
        product.productId === productId && product.capacity === capacity
    );

    if (existingProduct) {
      existingProduct.quantity += quantity;
      existingProduct.price =
        existingProduct.capacityPrice * existingProduct.quantity;
    } else {
      const product = await Product.findById(productId);
      product.capacitiesAndPrices.map((item) => {
        if (item.capacity === capacity) {
          item.isChoose = true;
          cart.products.push({
            productId: productId,
            capacity: capacity,
            capacityPrice: item.price,
            quantity: quantity,
            price: item.price * quantity,
          });
        }
      });
      product.save();
    }

    await cart.save();

    return res.json(
      Utils.createSuccessResponseModel("Thêm sản phẩm thành công", cart)
    );
  } catch (err) {
    console.log(err);
    return res.json(Utils.createErrorResponseModel("Vui lòng thử lại"));
  }
};

//update quantity and capacity of product in cart
const updateProductInCart = async (req, res) => {
  try {
    let currentCapacityPrice = 0;
    const { productId, capacity } = req.body;
    const product = await Product.findById(productId);
    product.capacitiesAndPrices.map((item) => {
      if (item.capacity === capacity) {
        item.isChoose = true;
        currentCapacityPrice = item.price;
      }
      if (item.capacity !== capacity && item.isChoose === true) {
        item.isChoose = false;
      }
    });
    //save product
    product.save();

    const cart = await Cart.findOne({ userId: req.session.user });
    const existingProduct = cart.products.find(
      (product) => product.productId === productId
    );

    if (existingProduct) {
      existingProduct.capacity = capacity;
      existingProduct.capacityPrice = currentCapacityPrice;
      existingProduct.price =
        existingProduct.capacityPrice * existingProduct.quantity;
    }

    await cart.save();

    return res.json(
      Utils.createSuccessResponseModel("Cập nhật sản phẩm thành công", cart)
    );
  } catch (err) {
    console.log(err);
    return res.json(Utils.createErrorResponseModel("Vui lòng thử lại"));
  }
};

module.exports = {
  checkUserLoggedIn: checkUserLoggedIn,
  getCartByUserId: getCartByUserId,
  addProductToCart: addProductToCart,
  updateProductInCart: updateProductInCart,
};
