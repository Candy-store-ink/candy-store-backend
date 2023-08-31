package com.github.candy.store.modules.token.payload;

import com.github.candy.store.modules.user.UserInfo;

public record TokenResponse(String token, String tokenType, UserInfo userInfo) {
}
