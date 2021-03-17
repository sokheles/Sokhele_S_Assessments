Feature: Dog_Api
  @run
  Scenario Outline: Verify that a successful message is returned when user searches random breeds
  Given I send a get request "<resource>"
    When I get a response
    Then A "<status>" message should be returned

    Examples:
    |resource|status|
    |/image/random|success|

  @run
  Scenario Outline: Verify that "<breed>" is on the list of all breeds
    Given I send a get request "<resource>"
    When I get a response
    Then A "<breed>" should be on the list returned

    Examples:
    |resource|breed|
    |/list/all|bulldog|

      @run
      Scenario Outline: Retrieve all "<breed>" sub breeds and their respective images
        Given I send a get request for sub breed "<resource>"
        When I retrieve all sub breeds for "<breed>"
        Then I should see all sub breeds

        Examples:
          |resource|breed|
          |/bulldog/images|bulldog|


         @run
        Scenario Outline:  Retrieve all available pets and confirm that the name "<name>" with category "<id> is on the list
          Given that I send an xml request to base url
          When I pass parameter and "<value>"
          Then I validate that name "<name>" with categoryid "<id>" are is available in the list
          Examples:
          |name|id|value|
          |doggie|12|/findByStatus?status=available|

           @run
          Scenario: Add a new pet with an auto generated name
            Given I add a pet with an auto generated name
            When I confirm the pet was added
              Then I should be able to search the pet using the id