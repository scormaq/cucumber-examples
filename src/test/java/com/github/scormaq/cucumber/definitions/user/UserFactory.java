package com.github.scormaq.cucumber.definitions.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserFactory {


    public static User getDefaultUser() {
        return User.builder()
            .firstName("john")
            .lastName("doe")
            .gradpaName("Rikki")
            .isActive(true)
            .isNotPassive(true)
            .isAdmin(true)
            .isNotAdmin(false)
            .username("default_username")
            .password("default_password")
            .build();
    }
}
