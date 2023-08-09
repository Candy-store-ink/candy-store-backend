package com.github.candy.store.modules.token;

import com.github.candy.store.modules.token.payload.TokenResponse;
import com.github.candy.store.modules.user.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {

    public TokenResponse toTokenResponse(Token token, UserInfo userInfo) {
        return new TokenResponse(
                token.getToken(),
                token.getType().getTokenType(),
                userInfo
        );
    }

}
