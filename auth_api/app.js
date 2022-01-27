import express from "express";
import * as db from "./src/config/db/InitialData.js";
import userRoute from "./src/modules/user/routes/UserRoutes.js"
import CheckToken from "./src/config/auth/CheckToken.js";

const app = express();
const env = process.env;
const PORT = env.PORT || 8080;

db.createInitialData();

app.use(express.json());

app.use(userRoute);


app.use(CheckToken)


app.get("/api/status", (req, res) => {
    return res.status(200).json({
      service: "Auth-API",
      status: "up",
      httpStatus: 200,
    });
});
  



app.listen(PORT, () => {
    console.log(`Server started success at port ${PORT}`)
});