package com.lcobzaru.bootcamp.socialmediaapp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The username provided could not be found!")
public class UserNameNotFound extends RuntimeException{
}
