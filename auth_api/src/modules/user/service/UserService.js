import bcrypt from "bcrypt"
import jwt from "JsonWebToken"
import * as secrets from "../../../config/constants/secrets.js"
import UserRepository from "../repository/UserRepository.js";
import * as HttpStatus from "../../../config/constants/HttpStatus.js";
import UserException from "../exception/UserException.js";


class UserService {
    async findByEmail(req) {
      try {
        const { email } = req.params;
        this.validateRequestData(email);
        let user = await UserRepository.findByEmail(email);
        this.validateUserNotFound(user);
        return {
          status: HttpStatus.SUCCESS,
          user: {
            id: user.id,
            name: user.name,
            email: user.email,
          },
        };
      } catch (err) {
        return {
          status: err.status ? err.status : HttpStatus.INTERNAL_SERVER_ERROR,
          message: err.message,
        };
      }
    }
  
    validateRequestData(email) {
      if (!email) {
        throw new UserException("User email was not informed.");
      }
    }
  
    validateUserNotFound(user) {
      if (!user) {
        throw new UserException(HttpStatus.BAD_REQUEST, "User was not found.");
      }
    }

    validateAccessTokenData(email, password) {
      if(!email || !password){
        throw new UserException(HttpStatus.UNAUTHORIZED, 
          "Email and Password must be informed.")
      }
    }
    async validatePasswordData(password, hashPassword){
      if(!await bcrypt.compare(password, hashPassword)){
          throw new UserException(HttpStatus.UNAUTHORIZED, 
            "Password doesn´t match. ")
      }
    }

    async getAccessToken(req){
      try {
        const{email, password} = req.body;

        this.validateAccessTokenData(email, password);
        let user = await UserRepository.findByEmail(email);
        this.validateUserNotFound(user);
        await this.validatePasswordData(password, user.password);

        const authUser = {id: user.id,name: user.name,email: user.email,}
        const accessToken = jwt.sign({authUser}, secrets.API_SECRET,{expiresIn: '1d'});
        console.log(accessToken);
          return{
            status: HttpStatus.SUCCESS,
            accessToken,
          }
         
      } catch (err) {
        return {
          status: err.status ? err.status : HttpStatus.INTERNAL_SERVER_ERROR,
          message: err.message,
        };
        
      }
    }



}

export default new UserService();