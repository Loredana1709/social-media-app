package com.lcobzaru.bootcamp.socialmediaapp.utils;

import com.lcobzaru.bootcamp.socialmediaapp.dto.UserDTO;
import com.lcobzaru.bootcamp.socialmediaapp.exeption.EmailTooShort;

public class ValidationUtils {

    //validarile userului primit (la gen, la birth date, la email etc)

    public static void validateGender(UserDTO user) {

    }

    public static void validateEmail(UserDTO user) {
        if(user.getEmail().length() < 15){
            throw new EmailTooShort();
        }
    }

    public static void validatePassword(UserDTO user){

    }
}
