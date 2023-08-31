package com.github.candy.store.modules.user;

import com.github.candy.store.exception.InvalidCredentialsException;
import com.github.candy.store.exception.UserNotFoundException;
import com.github.candy.store.modules.token.payload.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User validateAuthentication(LoginRequest authenticationRequest) {
        User user = userRepository.findByEmail(authenticationRequest.email())
                .orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(authenticationRequest.password(), user.getPassword())) {
            log.error("Invalid credentials for user: {}", user.getEmail());
            throw new InvalidCredentialsException();
        }
        return user;
    }
}
