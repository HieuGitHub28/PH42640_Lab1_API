const express = require("express");
const multer = require("multer");
const fs = require("fs");
const app = express();
const port = 3000;

// Khai báo nơi lưu trữ tệp tải lên
const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        var dir = './uploads';
        if (!fs.existsSync(dir)) {
            fs.mkdirSync(dir, { recursive: true });
        }
        cb(null, 'uploads')
    },
    filename: function (req, file, cb) {
        let fileName = file.originalname;
        cb(null, fileName)
    }
})

// Khởi tạo middleware Multer
const upload = multer({ storage: storage });

// Thiết lập route cho trang gửi biểu mẫu HTML
app.get("/", (req, res) => {
    res.sendFile(__dirname + "/upload.html");
});

// Route để xử lý việc tải tệp lên
app.post('/uploadfile', upload.single('myfile'), (req, res, next) => {
    let file = req.file;
    
    if (!file) {
        let error = new Error('Vui lòng chọn file để tải lên');
        error.httpStatusCode = 400;
        return next(error);
    }

    res.send(file);
});

// Middleware xử lý lỗi
app.use((err, req, res, next) => {
    res.status(err.httpStatusCode || 500).json({ error: err.message });
});

// Khởi động máy chủ
app.listen(port, () => {
    console.log("Server đang chạy trên cổng:", port);
});
