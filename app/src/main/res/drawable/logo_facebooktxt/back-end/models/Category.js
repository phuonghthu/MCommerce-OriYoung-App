const mongoose = require("mongoose");
const Product = require("./Product");

const CategorySchema = new mongoose.Schema({
  name: {
    type: String,
    required: true,
    unique: true,
  },
  products: {
    type: [Product.schema],
    required: false,
  },
});

const Category = mongoose.model("Category", CategorySchema);
module.exports = Category;
