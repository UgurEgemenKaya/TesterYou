Feature: TesterYou IMDB
  Scenario Outline: Movie Credits Check
      Given User clicks Oscars at Awards and Events section which is under Menu
      And Chooses date "1929" at Event History table
      And Clicks on "<movie>"
      And Takes note of Director Writer and Stars
      And Clicks on IMDB logo at the left upper side
      And Types "<movie>" to the search bar
      And Selects movie acording to "<year>"
      Then Compares Director Writer and Stars values
      Then Validate the visibility of all photos
      Examples:
      |movie               |year |
      |The Circus          | 1928|
      |The Jazz Singer     | 1927|