package com.github.candy.store.modules.user;

import com.github.candy.store.modules.token.payload.LoginRequest;

public interface UserService {

    /**
     * This method is used to validate the user's credentials
     * and return the user if the credentials are valid.
     *
     * @param authenticationRequest The user's login credentials
     * @return The user if the credentials are valid
     * @throws com.github.candy.store.exception.UserNotFoundException will be thrown if the user is not found
     * @throws com.github.candy.store.exception.InvalidCredentialsException will be thrown if the credentials are invalid
     */
    User validateAuthentication(LoginRequest authenticationRequest);
}
