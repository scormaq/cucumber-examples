Feature: pass arguments from Cucumber to Java

  As a test engineer
  I want to pass arguments from text file to code without extra boilerplate code
  So that I will save my time and energy


  Scenario: pass single arguments

  demonstrate how single step parameter can be passed from scenario to test

    Given single number 42 is passed to test
    And single word, without spaces, 'hello' is passed to test
    And whole sentence "Hello World!" is passed to test
    And enum value DECEMBER is passed to test
    And next multi-line string is passed to test:
    """
    Lorem ipsum,
    dolor sit amet,
    consectetur adipiscing elit
    """


  Scenario: variative steps

  demonstrate how step text can be altered to make scenario more expressive

    Given next string used in test: "this is text"
    And next text used in test: "this is text"
    And next text also used in test: "this is text"
    And some words "blah-blah" should pe passed into test

    And old style regex params also available


  Scenario: pass table arguments

  demonstrate how to pass complex parameters to test (lists, maps, DTOs)

    Given next list is used in test:
      | hello |
      | world |

    And next vertical map is used in test:
      | key1 | hello  |
      | key2 | world  |
      | key3 | foobar |

    And next horizontal map is used in test:
      | key1  | key2  | key3   |
      | hello | world | foobar |

    And next DTO is used in test:
      | id           | 12345                                  |
      | addressLine1 | Sezame st. 1                           |
      | addressLine2 | apt.123                                |
      | zipCode.code | A0A 0A0                                |
      | comments     | [comment1,then comment2,comment3 also] |

    And next DTOs are used in test:
      | id  | addressLine1 | addressLine2 | zipCode.code | comments |
      | 555 |              | some line1   | AAA BBB      | []       |
      |     | [blank]      | [blank]      |              |          |


  Scenario: pass non-trivial custom arguments

  demonstrate how to customize cucumber transformation logic

    Given XML gregorian calendar for "May 04, 2020" is used in test
    Given next test resource is used in test: data/test.txt

    # pay attention how entries are ordered in table and in transformed map
    Given next enum-key based map is used in test:
      | DECEMBER | cold month            |
      | MAY      | the force be with you |
      | JULY     | hot month             |
