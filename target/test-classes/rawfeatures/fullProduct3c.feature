Feature: fullProduct3
Background: Setup
Given The environment is set up with "push button to call elevator,reach target floor,door opened,elevator empty for ten seconds"

Scenario: 0
When [
Then push button to call elevator

Scenario: 1
Given push button to call elevator
When reach target floor
Then door opened

Scenario: 2
Given push button to call elevator
And reach target floor
When door opened
Then elevator empty for ten seconds

