@PUT
Feature: PUT Posts

  Scenario: Verify PUT operation after POST
    Given I ensure to perform POST operation for "/posts" with body as
      | id | title              | author            |
      |  8 | API Testing course | ExecuteAutomation |
    And I perform PUT operation for "/posts/{postid}/"
      | id | title       | author   |
      |  8 | RestAssured | myAuthor |
    And I perform GET with path parameter for "/posts/{postid}/"
      | postid |
      |      8 |
    Then I should see the body with title as "RestAssured"
