Feature: Validating Place API's
  @AddPlace
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
    | name    | language  | address                 |
    | AAhouse | Poland    |29, side lyaout, cohen 90|
    | BBhouse | Poland    |20, side lyaout, cohen 90|

  @DeletePlace
  Scenario: Verify if Delete Place functionality is working

    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"