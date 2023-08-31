package com.github.candy.store.modules.user;

import com.github.candy.store.modules.manager.ManagerInfo;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserInfo toUserInfo(User user, ManagerInfo managerInfo) {
        return new UserInfo(
                user.getUsername(),
                user.getSecondName(),
                user.getRole().name(),
                user.getEmail(),
                user.getPhone(),
                managerInfo
        );
    }
}
