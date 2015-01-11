package guda.shop.common.security;

public class UserNotAcitveException extends AccountStatusException {
    public UserNotAcitveException() {
    }

    public UserNotAcitveException(String paramString) {
        super(paramString);
    }
}

