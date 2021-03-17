$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/API_Tests/dog_api.feature");
formatter.feature({
  "name": "Dog_Api",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Verify that a successful message is returned when user searches random breeds",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@run"
    }
  ]
});
formatter.step({
  "name": "I send a get request \"\u003cresource\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "name": "I get a response",
  "keyword": "When "
});
formatter.step({
  "name": "A \"\u003cstatus\u003e\" message should be returned",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "resource",
        "status"
      ]
    },
    {
      "cells": [
        "/image/random",
        "success"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Verify that a successful message is returned when user searches random breeds",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@run"
    }
  ]
});
formatter.step({
  "name": "I send a get request \"/image/random\"",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.Steps.i_send_a_get_request(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get a response",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.Steps.i_get_a_response()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "A \"success\" message should be returned",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.Steps.a_message_should_be_returned(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenarioOutline({
  "name": "Verify that \"\u003cbreed\u003e\" is on the list of all breeds",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@run"
    }
  ]
});
formatter.step({
  "name": "I send a get request \"\u003cresource\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "name": "I get a response",
  "keyword": "When "
});
formatter.step({
  "name": "A \"\u003cbreed\u003e\" should be on the list returned",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "resource",
        "breed"
      ]
    },
    {
      "cells": [
        "/list/all",
        "bulldog"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Verify that \"bulldog\" is on the list of all breeds",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@run"
    }
  ]
});
formatter.step({
  "name": "I send a get request \"/list/all\"",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.Steps.i_send_a_get_request(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get a response",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.Steps.i_get_a_response()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "A \"bulldog\" should be on the list returned",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.Steps.a_should_be_on_the_list_returned(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenarioOutline({
  "name": "Retrieve all \"\u003cbreed\u003e\" sub breeds and their respective images",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@run"
    }
  ]
});
formatter.step({
  "name": "I send a get request for sub breed \"\u003cresource\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "name": "I retrieve all sub breeds for \"\u003cbreed\u003e\"",
  "keyword": "When "
});
formatter.step({
  "name": "I should see all sub breeds",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "resource",
        "breed"
      ]
    },
    {
      "cells": [
        "/bulldog/images",
        "bulldog"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Retrieve all \"bulldog\" sub breeds and their respective images",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@run"
    }
  ]
});
formatter.step({
  "name": "I send a get request for sub breed \"/bulldog/images\"",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.Steps.i_send_a_get_request_for_sub_breed(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I retrieve all sub breeds for \"bulldog\"",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.Steps.i_retrieve_all_sub_breeds_for(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should see all sub breeds",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.Steps.i_should_see_all_sub_breeds()"
});
formatter.result({
  "status": "passed"
});
formatter.scenarioOutline({
  "name": "Retrieve all available pets and confirm that the name \"\u003cname\u003e\" with category \"\u003cid\u003e is on the list",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@run"
    }
  ]
});
formatter.step({
  "name": "that I send an xml request to base url",
  "keyword": "Given "
});
formatter.step({
  "name": "I pass parameter and \"\u003cvalue\u003e\"",
  "keyword": "When "
});
formatter.step({
  "name": "I validate that name \"\u003cname\u003e\" with categoryid \"\u003cid\u003e\" are is available in the list",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "name",
        "id",
        "value"
      ]
    },
    {
      "cells": [
        "doggie",
        "122",
        "/findByStatus?status\u003davailable"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Retrieve all available pets and confirm that the name \"doggie\" with category \"122 is on the list",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@run"
    }
  ]
});
formatter.step({
  "name": "that I send an xml request to base url",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.Steps.that_I_send_an_xml_request_to_base_url()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I pass parameter and \"/findByStatus?status\u003davailable\"",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.Steps.i_pass_parameter_and(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I validate that name \"doggie\" with categoryid \"122\" are is available in the list",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.Steps.i_validate_that_name_with_categoryid_are_is_available_in_the_list(java.lang.String,java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.AssertionError: Category ID: 122 with Name: doggie was not found in the list\r\n\tat org.junit.Assert.fail(Assert.java:89)\r\n\tat org.junit.Assert.assertTrue(Assert.java:42)\r\n\tat stepDefinitions.Steps.i_validate_that_name_with_categoryid_are_is_available_in_the_list(Steps.java:105)\r\n\tat ✽.I validate that name \"doggie\" with categoryid \"122\" are is available in the list(file:///C:/Users/i7/Desktop/Stimela/ilab.rest.assured.bdd/src/test/resources/API_Tests/dog_api.feature:37)\r\n",
  "status": "failed"
});
formatter.scenario({
  "name": "Add a new pet with an auto generated name",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@run"
    }
  ]
});
formatter.step({
  "name": "I add a pet with an auto generated name",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.Steps.i_add_a_pet_with_an_auto_generated_name()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I confirm the pet was added",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.Steps.i_confirm_the_pet_was_added()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should be able to search the pet using the id",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.Steps.i_should_be_able_to_search_the_pet_using_the_id()"
});
formatter.result({
  "status": "passed"
});
});