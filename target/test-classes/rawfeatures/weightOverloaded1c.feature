Feature: weightOverloaded1
Background: Setup
Given The environment is set up with "push button to call elevator,door opened,press target floor button,press target floor button,signal weight increase,print overloaded alarm,signal weight decrease,signal weight increase,signal weight increase,push button to close door,door closed,reach target floor,door opened,signal weight increase,signal weight decrease,signal weight decrease,push button to close door,door closed,reach target floor,door opened,signal weight decrease,signal weight increase,press target floor button,signal weight decrease,press target floor button,push button to close door,door closed,reach target floor,door opened,elevator empty for ten seconds"

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
Then signal weight increase

Scenario: 4
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
When signal weight increase
Then print overloaded alarm

Scenario: 5
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
When print overloaded alarm
Then signal weight decrease

Scenario: 6
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
When signal weight decrease
Then signal weight increase

Scenario: 7
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
When signal weight increase
Then signal weight increase

Scenario: 8
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
When signal weight increase
Then push button to close door

Scenario: 9
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
When push button to close door
Then door closed

Scenario: 10
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
When door closed
Then reach target floor

Scenario: 11
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
When reach target floor
Then door opened

Scenario: 12
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
When door opened
Then signal weight increase

Scenario: 13
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
When signal weight increase
Then signal weight decrease

Scenario: 14
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
When signal weight decrease
Then signal weight decrease

Scenario: 15
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
When signal weight decrease
Then push button to close door

Scenario: 16
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
When push button to close door
Then door closed

Scenario: 17
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
And push button to close door
When door closed
Then reach target floor

Scenario: 18
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
And push button to close door
And door closed
When reach target floor
Then door opened

Scenario: 19
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
And push button to close door
And door closed
And reach target floor
When door opened
Then signal weight decrease

Scenario: 20
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
And push button to close door
And door closed
And reach target floor
And door opened
When signal weight decrease
Then signal weight increase

Scenario: 21
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight decrease
When signal weight increase
Then press target floor button

Scenario: 22
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight decrease
And signal weight increase
When press target floor button
Then signal weight decrease

Scenario: 23
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight decrease
And signal weight increase
And press target floor button
When signal weight decrease
Then press target floor button

Scenario: 24
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight decrease
And signal weight increase
And press target floor button
And signal weight decrease
When press target floor button
Then push button to close door

Scenario: 25
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight decrease
And signal weight increase
And press target floor button
And signal weight decrease
And press target floor button
When push button to close door
Then door closed

Scenario: 26
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight decrease
And signal weight increase
And press target floor button
And signal weight decrease
And press target floor button
And push button to close door
When door closed
Then reach target floor

Scenario: 27
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight decrease
And signal weight increase
And press target floor button
And signal weight decrease
And press target floor button
And push button to close door
And door closed
When reach target floor
Then door opened

Scenario: 28
Given push button to call elevator
And door opened
And press target floor button
And press target floor button
And signal weight increase
And print overloaded alarm
And signal weight decrease
And signal weight increase
And signal weight increase
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight increase
And signal weight decrease
And signal weight decrease
And push button to close door
And door closed
And reach target floor
And door opened
And signal weight decrease
And signal weight increase
And press target floor button
And signal weight decrease
And press target floor button
And push button to close door
And door closed
And reach target floor
When door opened
Then elevator empty for ten seconds

