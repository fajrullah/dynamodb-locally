Feature: Student Crud
  Scenario: Call backend to get list of students
    When the client calls endpoint "/api/students"
    Then response status code is 200
    And response media type is application JSON


#  Scenario: Call backend with ONE
#    When the client calls endpoint "/launch/counter/1"
#    Then response status code is 200
#    And returned string should be "Launch! Go go go!"
#
#  Scenario: Call backend with rubbish non existing endpoint
#    When the client calls endpoint "/launch/yesyes/mycounter/1"
#    Then response status code is not present