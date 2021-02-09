Feature: share state between cucumber steps

  As a test engineer
  I want to share state between cucumber step definitions
  Also I want to clear this state automatically between scenarios
  So that I won't create tons of useless code just to support cucumber test model

  We use here custom Spring annotation, and wipe all annotated beans in @After method.


  Scenario: first scenario
    Given user ID "user1" is saved as test variable
    Then this step should have access to it


  Scenario: second scenario
    Given user ID "user555" is saved as test variable
    Then this step should have access to it


  Scenario Outline: outline scenario
    Given user ID "<user_id>" is saved as test variable
    Then this step should have access to it

    Examples:
      | user_id  |
      | id1234   |
      | id555    |
      | id987654 |