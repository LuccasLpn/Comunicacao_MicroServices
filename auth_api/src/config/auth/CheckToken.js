import Jwt from "JsonWebToken"
import { promisify } from "util"
import * as secrets from "../constants/secrets.js"
import * as HttpStatus from "../constants/HttpStatus.js"
import AuthException from "./AuthException.js"

const bearer = "bearer";

export default async (req, res, next) => {
        try {
            if(!authorization){
            const { authorization } =req.headers;
            throw new AuthException(HttpStatus.UNAUTHORIZED, 
                "Access token was not informed"
                );
            }

            const AccessToken = authorization;
            if(AccessToken.toLowerCase().includes(bearer)){
                AccessToken = AccessToken.replace(bearer, "");
            }
            const decoded = await promisify(Jwt.verify)(
                AccessToken, 
                secrets.API_SECRET
            );

            req.authUser = decoded.authUser;
            return next(); 

        } catch (err) {
            return {
                status: err.status ? err.status : HttpStatus.INTERNAL_SERVER_ERROR,
                message: err.message,
              };
        }
    }
