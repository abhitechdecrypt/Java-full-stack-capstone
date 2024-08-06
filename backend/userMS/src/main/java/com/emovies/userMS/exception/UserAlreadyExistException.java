package com.emovies.userMS.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAlreadyExistException extends RuntimeException {
    private String message;
    private String statusCode;
}
