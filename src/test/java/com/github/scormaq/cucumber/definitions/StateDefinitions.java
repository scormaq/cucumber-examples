package com.github.scormaq.cucumber.definitions;

import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class StateDefinitions {

    @Autowired
    private TestVariables testVariables;

    @And("user ID {string} is saved as test variable")
    public void saveTestVariable(String userId) {
        log.info("Variable before test: {}", testVariables.getTestUserId());
        testVariables.setTestUserId(userId);
    }
}
