const { error } = require("console");
const express = require("express");
const app = express();
const port = 3000;
app.listen(port, () => {
  console.log("Server ddang chayj cong 3000:", +port);
});
app.get("/", (req, res) => {
  res.sendFile(__dirname + "/lab4/upload.html");
});

const upload= multer({storage: storage})

app.post("/taifile",upload.single('myfile') ,(req, res, next) => {
  let file = req.file;
  if(!file){
    let err= new Error('can chon file tai len')
    error.httpStatusCode  =400;
    return next(err)
  }
    res.send("vao API xu ly");
});
const fs = require("fs");
const multer = require("multer");

