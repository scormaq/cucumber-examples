package com.github.scormaq.cucumber.definitions.user;

import com.github.scormaq.utils.BeanUtils;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDefinitions {


    @And("next user is created in test:")
    public void passFullUserToTest(User user) {
        log.info("User passed to test: {}", user);
    }

    @And("user with next details is used in test:")
    public void passUsedDetailsToTest(User userDetails) {
        User user = UserFactory.getDefaultUser();
        BeanUtils.copyNonNullProperties(user, userDetails);
        log.info("User populated with default details: {}", user);
    }
}
