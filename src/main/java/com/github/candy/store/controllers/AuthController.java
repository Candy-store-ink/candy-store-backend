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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final TokenService tokenService;
    private final UserService userService;
    private final TokenMapper tokenMapper;
    private final UserMapper userMapper;
    private final ManagerMapper managerMapper;

    @PostMapping("login")
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
}
