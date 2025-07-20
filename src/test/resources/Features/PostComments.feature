@POST
Feature: Verify different POST operations using REST-assured

  Scenario: Verify Post Operation
    Given I perform POST operation for "/posts"
   
  Scenario: Verify Post operation for Pro
    Given I perform POST operation for "/posts/{profileNo}/comments" with
      | name | profile |
      | Sam  |       2 |
    Then I should see the body has name as "Sam"
