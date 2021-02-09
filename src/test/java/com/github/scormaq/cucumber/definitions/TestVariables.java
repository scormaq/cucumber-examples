package com.github.scormaq.cucumber.definitions;

import com.github.scormaq.spring.TestSession;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TestSession
public class TestVariables {

    private String testUserId;
}
