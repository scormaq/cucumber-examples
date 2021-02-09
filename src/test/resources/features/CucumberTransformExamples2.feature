Feature: pass arguments from Cucumber to Java - continue examples


  Scenario: keep scenarios neat

  Sometimes large objects should be used in test. Often this objects can be populated with default data,
  so no need to expose all the fields on cucumber levels. Expose only that data which matter, keep scenarios small
  and readable.

    #bad - a lot of useless details here
    Given next user is created in test:
      | firstName | lastName | isActive | isAdmin | isNotAdmin | gradpaName | isNotPassive | username      | password |
      | Johny     | Moe      | true     | false   | true       | Rikky      | true         | john@mail.com | qwerty   |


    #slightly better - meaningful details (username/password) arranged to the left
    Given next user is created in test:
      | username      | password | firstName | lastName | isActive | isAdmin | isNotAdmin | gradpaName | isNotPassive |
      | john@mail.com | qwerty   | Johny     | Moe      | true     | false   | true       | Rikky      | false        |


    #good - only that user details which matter to test are exposed
    Given user with next details is used in test:
      | username      | password |
      | john@mail.com | qwerty   |


    # same definition is reusable for other test cases, when other details matter
    And user with next details is used in test:
      | isActive | isAdmin | isNotAdmin |
      | true     | true    | false      |