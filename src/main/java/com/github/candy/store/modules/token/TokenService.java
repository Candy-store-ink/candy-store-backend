package com.github.candy.store.modules.token;

import com.github.candy.store.modules.user.User;

public interface TokenService {

    Token generate(User user);

    void revoke(Token token);
}
