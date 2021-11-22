package com.lcobzaru.bootcamp.socialmediaapp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The gender was declared incorrectly. Please use M for Male or F for Female!")
public class GenderInvalid extends RuntimeException{
}
