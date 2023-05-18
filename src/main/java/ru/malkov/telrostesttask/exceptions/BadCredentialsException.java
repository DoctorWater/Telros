package ru.malkov.telrostesttask.exceptions;

import javax.naming.AuthenticationException;

public class BadCredentialsException extends AuthenticationException {

    public BadCredentialsException(String username, String password) {
        super("This credentials are not valid: " + username + ", " + password);
    }
}
