Feature: Verify different GET operations using REST- Assured

  Scenario: Verify collection of authors in the post
    Given I perform GET operation for "/posts"
    Then I should see the author names
      | typicode        |
      | java-author     |
      | selenium-author |
      | your author      |

  Scenario: Verify one author of the post with path parameter
    Given I perform GET with path parameter for "/posts/{postid}"
      | postid |
      |      1 |
    Then I should see the author name as "typicode"

  Scenario: Verify one author of the post with query parameter
    Given I perfrom GET with query parameter for "/posts"
      | id | title       |
      |  1 | json-server |
    Then I should see the author names
      | typicode |
