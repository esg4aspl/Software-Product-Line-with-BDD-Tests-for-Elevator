Feature: weightExecutiveFloor4
Background: Setup
Given The environment is set up with "push button to call elevator,call elevator from executive floor,reach target floor,door opened,elevator empty for ten seconds"

Scenario: 0
When [
Then push button to call elevator

Scenario: 1
Given push button to call elevator
When call elevator from executive floor
Then reach target floor

Scenario: 2
Given push button to call elevator
And call elevator from executive floor
When reach target floor
Then door opened

Scenario: 3
Given push button to call elevator
And call elevator from executive floor
And reach target floor
When door opened
Then elevator empty for ten seconds

