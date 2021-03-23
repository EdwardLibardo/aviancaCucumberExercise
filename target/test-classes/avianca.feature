Feature: Avianca


  Scenario: booking a Flight
    Given the user is in the home page to book a flight
    When user add the "Bogotá" as origen and "Miami" as destination
    And user takes flight since "27 marzo 2021" until "29 abril 2021"
    Then user confirms the flight

  Scenario: sorting flights
    Given the user in the menu options
    And user adds the "Bogotá" as origen and "Cartagena" as destination from menu options
    When user books the flight since "30 marzo 2021" until "23 abril 2021"
    Then user can sort the flights by time

