package com.github.scormaq.cucumber.definitions;

import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class StateDefinitions2 {

    @Autowired
    private TestVariables testVariables;

    @And("this step should have access to it")
    public void retrieveTestVariable() {
        log.info("retrieved variable: {}", testVariables.getTestUserId());
    }
}
