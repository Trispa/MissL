package io.gotech.missl.domain.users;

public class UserBuildError extends RuntimeException {

    private static final long serialVersionUID = 116331007948339981L;

    public UserBuildError(String message) {
	super(message);
    }

}
