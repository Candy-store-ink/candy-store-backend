package com.github.candy.store.modules.token;

import com.github.candy.store.modules.user.User;
import com.github.candy.store.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtTokenProvider tokenProvider;
    private final TokenRepository tokenRepository;

    @Override
    public Token generate(User user) {
        Token token = new Token(
                tokenProvider.generateToken(user),
                TokenType.BEARER,
                user
        );
        return tokenRepository.save(token);
    }

    @Override
    public void revoke(Token token) {
        tokenRepository.revokeToken(token.getToken());
    }
}
