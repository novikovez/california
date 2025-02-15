package org.california.exception.user;

public class UserExistsException extends RuntimeException{

    public UserExistsException(String s) {
        super(s);
    }
}
