package com.github.candy.store.modules.manager;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ManagerMapper {

    public ManagerInfo toManagerInfo(Manager manager) {
        if (Objects.isNull(manager)) {
            return null;
        }
        return new ManagerInfo(
                manager.getName(),
                manager.getEmail()
        );
    }
}
