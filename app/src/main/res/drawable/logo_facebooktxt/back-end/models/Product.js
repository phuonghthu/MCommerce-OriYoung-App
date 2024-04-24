const mongoose = require("mongoose");
const CapacityPrice = require("./CapacityPrice");
const ProductSchema = new mongoose.Schema({
  productCode: {
    type: String,
    required: true,
  },
  name: {
    type: String,
    required: true,
  },
  image: {
    type: String,
    required: true,
  },
  description: {
    type: String,
    required: true,
  },
  capacitiesAndPrices: [CapacityPrice.schema],
  quantity: {
    type: Number,
    required: true,
  },
  isDeleted: {
    type: Boolean,
    default: false,
  },
  createdAt: {
    type: Date,
    default: Date.now,
  },
  updateAt: {
    type: Date,
    required: false,
  },
});

const Product = mongoose.model("Product", ProductSchema);
module.exports = Product;
