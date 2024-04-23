const mongoose = require("mongoose");

const CapacityPriceSchema = new mongoose.Schema({
  capacity: {
    type: String,
    required: true,
  },
  price: {
    type: Number,
    required: true,
  },
  isChoose: {
    type: Boolean,
    default: false,
  },
});

const CapacityPrice = mongoose.model("CapacityPrice", CapacityPriceSchema);
module.exports = CapacityPrice;
