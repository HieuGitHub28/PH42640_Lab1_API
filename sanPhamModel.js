const mongoose = require("mongoose")

const SanPhamSchema = mongoose.Schema(
    {
        ten : {
            type: String,
            require:true
        },
        gia:{
            type: Number,
            require: true
        },
        soluong:{
            type:Number,
            require:true
        },
        tonkho:{
            type:Boolean
        },
    }
);
const  SanPhamModel = mongoose.model('sanpham', SanPhamSchema);
module.exports = SanPhamModel;