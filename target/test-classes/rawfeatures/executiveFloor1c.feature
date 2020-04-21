Feature: executiveFloor1
Background: Setup
Given The environment is set up with "push button to call elevator,door opened,press target floor button,press target floor button,push button to close door,door closed,reach target floor,door opened,elevator empty for ten seconds"

Scenario: 0
When [
Then push button to call elevator

Scenario: 1
Given push button to call elevator
When door opened
Then press target floor button

Scenario: 2
Given push button to call elevator
And door opened
When press target floor button
Then press target floor button

Scenario: 3
Given push button to call elevator
And door opened
And press target floor button
When press target floor button
Then push button to close door

Scenario: 4
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
When push button to close door
Then door closed

Scenario: 5
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And push button to close door
When door closed
Then reach target floor

Scenario: 6
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And push button to close door
And door closed
When reach target floor
Then door opened

Scenario: 7
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And push button to close door
And door closed
And reach target floor
When door opened
Then elevator empty for ten seconds

