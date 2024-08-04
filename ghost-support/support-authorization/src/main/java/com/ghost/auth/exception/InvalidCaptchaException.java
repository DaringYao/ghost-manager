package com.ghost.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author yaolsœ
 * @version 1.0
 * @since 2024/8/4 05:10
 */
public class InvalidCaptchaException extends AuthenticationException {

    public InvalidCaptchaException(String msg) {
        super(msg);
    }

}
