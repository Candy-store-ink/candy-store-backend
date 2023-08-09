package com.github.candy.store.modules.token;

public enum TokenType {
    BEARER("Bearer");

    private final String tokenType;

    TokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenType() {
        return tokenType;
    }
}
