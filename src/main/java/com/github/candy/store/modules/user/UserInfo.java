package com.github.candy.store.modules.user;

import com.github.candy.store.modules.manager.ManagerInfo;

public record UserInfo(
        String name,
        String secondName,
        String role,
        String email,
        String phoneNumber,
        ManagerInfo managerInfo
) {
}
