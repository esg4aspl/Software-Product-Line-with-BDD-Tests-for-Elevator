Feature: executiveFloor2
Background: Setup
Given The environment is set up with "call elevator from executive floor,reach target floor,door opened,elevator empty for ten seconds"

Scenario: 0
When [
Then call elevator from executive floor

Scenario: 1
Given call elevator from executive floor
When reach target floor
Then door opened

Scenario: 2
Given call elevator from executive floor
And reach target floor
When door opened
Then elevator empty for ten seconds

