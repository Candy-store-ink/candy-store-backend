package com.github.candy.store.controllers;

import com.github.candy.store.modules.manager.Manager;
import com.github.candy.store.modules.token.TokenService;
import com.github.candy.store.modules.token.Token;
import com.github.candy.store.modules.token.TokenMapper;
import com.github.candy.store.modules.manager.ManagerInfo;
import com.github.candy.store.modules.token.payload.TokenResponse;
import com.github.candy.store.modules.user.UserInfo;
import com.github.candy.store.modules.token.payload.LoginRequest;
import com.github.candy.store.modules.manager.ManagerMapper;
import com.github.candy.store.modules.user.User;
import com.github.candy.store.modules.user.UserMapper;
import com.github.candy.store.modules.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class AuthController {

    private final TokenService tokenService;
    private final UserService userService;
    private final TokenMapper tokenMapper;
    private final UserMapper userMapper;
    private final ManagerMapper managerMapper;

    @PostMapping("auth/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = this.userService.validateAuthentication(loginRequest);
        Token token = tokenService.generate(user);
        TokenResponse tokenResponse = getTokenResponse(user, token);
        return ResponseEntity.ok(tokenResponse);
    }

    private TokenResponse getTokenResponse(User user, Token token) {
        Manager manager = user.getManager();
        ManagerInfo managerInfo = managerMapper.toManagerInfo(manager);
        UserInfo userInfo = this.userMapper.toUserInfo(user, managerInfo);
        return tokenMapper.toTokenResponse(token, userInfo);
    }

    @DeleteMapping("logout")
    public void logout() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        Token token = (Token) requestAttributes.getAttribute("token", RequestAttributes.SCOPE_REQUEST);
        this.tokenService.revoke(token);
        SecurityContextHolder.clearContext();
    }
}
