package com.lcobzaru.bootcamp.socialmediaapp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "There already exists an account with this email")
public class EmailAlreadyUsedExemption extends RuntimeException{
}
