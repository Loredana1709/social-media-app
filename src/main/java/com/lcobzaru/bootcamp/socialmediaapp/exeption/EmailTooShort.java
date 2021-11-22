package com.lcobzaru.bootcamp.socialmediaapp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The email length is too short! The email should not have less that 15 characters")
public class EmailTooShort extends RuntimeException{
}
