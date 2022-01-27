import jwt from "JsonWebToken"
import { promisify } from "util"
import * as secrets from "../constants/secrets.js"
import * as HttpStatus from "../constants/HttpStatus.js"
import AuthException from "./AuthException.js"

const emptySpace = " ";

export default async (req, res, next) => {
  
  try {
    let { authorization } = req.headers;
    if (!authorization) {
      throw new AuthException(
        HttpStatus.UNAUTHORIZED,
        "Access token was not informed."
      );
    }
    let accessToken = authorization;
    if (accessToken.includes(emptySpace)) {
      accessToken = accessToken.split(emptySpace)[1];
    } else {
      accessToken = authorization;
    }
    
    const decoded = await promisify(jwt.verify)(
      accessToken,
      secrets.API_SECRET
    );

    req.authUser = decoded.authUser;
    return next();


  } catch (err) {
    const status = err.status ? err.status : HttpStatus.INTERNAL_SERVER_ERROR;
    return res.status(status).json({ status, message: err.message });
  }
  
};