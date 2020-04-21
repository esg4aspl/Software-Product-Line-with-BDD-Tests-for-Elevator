package stepdefinitions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import ElevatorSystem.Environment;
import ElevatorSystem.IElevator;
import ElevatorSystem.IEnvironment;
import ElevatorSystem.IPerson;
import ElevatorSystem.Person;
import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {
	
	
	private String[] steps;
	private int stepCounter;
	private IEnvironment environment;
	private IElevator elevator;
	private static final int OVERWEIGHT_PERSON_WEIGHT = 6000;
	private boolean executiveFloorCalled;
	
	
	@Before
	public void before(Scenario scenario) {
		environment = new Environment(10);
		elevator = environment.getElevator();
		stepCounter = 0;
		executiveFloorCalled = false;
	}
	
	@AfterStep
	public void afterStep() {
		try {
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
	@Given("The environment is set up with {string}")
	public void the_environment_is_set_up_with(String string) {
		steps = string.split(",");
	}

	@When("[")
	public void init () {
	    
	}

	@Then("push button to call elevator")
	public void push_button_to_call_elevator() {
		//Actions
		IPerson personToCall;
		if (nextStep() != null && nextStep().equals("reach target floor")) {
			personToCall = new Person("John", randomWeight(), randomFloor(elevator.getCurrentFloorID()), randomFloor(), environment);
		} else {
			personToCall = new Person("John", randomWeight(), elevator.getCurrentFloorID(), randomFloor(), environment);
		}
		try {
			personToCall.callElevator();
		} catch (Exception e) {
			assertTrue(false);
		}
		
		//Assertions
		assertTrue(environment.getFloor(personToCall.getOrigin()).hasElevatorCall());
		
		stepCounter++;
	}

	@When("call elevator from executive floor")
	public void call_elevator_from_executive_floor() {
		try {
			new Person("Boss", randomWeight(), elevator.getExecutiveFloorID(), randomFloor(), environment).callElevator();
		} catch (Exception e) {
			assertTrue(false);
		}
		assertTrue(environment.getFloor(elevator.getExecutiveFloorID()).hasElevatorCall());
		executiveFloorCalled = true;
	}
	
	@When("door opened")
	public void door_opened() {
	    //Actions
		try {
			elevator.openDoors();
			IPerson personToEnter = environment.getFloor(elevator.getCurrentFloorID()).getNextWaitingPerson();
			while (personToEnter != null) {
				elevator.getPersons().add(personToEnter);
				environment.getFloor(elevator.getCurrentFloorID()).releasePerson(personToEnter);
				personToEnter = environment.getFloor(elevator.getCurrentFloorID()).getNextWaitingPerson();
			}
		} catch (Exception e) {
			assertTrue(false);
		}
		
		stepCounter++;
	}

	@Then("press target floor button")
	public void press_target_floor_button() {
	    try {
	    	getRandomPersonInElevator().pressTargetFloorButton();
		} catch (Exception e) {
			assertTrue(false);
		}
	    
		stepCounter++;
	}

	@Then("signal weight increase")
	public void signal_weight_increase() {
		//Actions
		IPerson personToEnter;
		if (nextStep() != null && nextStep().equals("print overloaded alarm")) {
			personToEnter = new Person("Wilson", OVERWEIGHT_PERSON_WEIGHT, elevator.getCurrentFloorID(), randomFloor(), environment);
		} else {
			personToEnter = environment.getFloor(elevator.getCurrentFloorID()).getNextWaitingPerson();
		    if (personToEnter == null)
		    	personToEnter = new Person("Mark", randomWeight(), elevator.getCurrentFloorID(), randomFloor(), environment);
		}
	    
	    try {
	    	int oldWeight = elevator.getWeight();
			personToEnter.enterElevator();
			if (personToEnter.getWeight() != OVERWEIGHT_PERSON_WEIGHT)
				personToEnter.pressTargetFloorButton();
			int newWeight = elevator.getWeight();
			assertTrue(newWeight - oldWeight == personToEnter.getWeight());
		} catch (Exception e) {
			assertTrue(false);
		}
	    
	    
	    
		stepCounter++;
	}

	@Then("print overloaded alarm")
	public void print_overloaded_alarm() {
		//Actions
	    try {
			elevator.printOverloadedAlarm();
		} catch (Exception e) {
			assertTrue(false);
		}
	    
	    assertTrue(elevator.isOverloadedAlarmPrinted());
		stepCounter++;
	}

	@Then("signal weight decrease")
	public void signal_weight_decrease() {
		//Actions
		IPerson personToLeave = null;
		
		if (elevator.isOverloadedAlarmPrinted() && willContinue())
			personToLeave = this.getRandomOverweightPersonInElevator();
		else
			personToLeave = this.getRandomPersonInElevator();
		
		if (personToLeave == null)
			assertTrue(false);
		
		try {
	    	int oldWeight = elevator.getWeight();
			personToLeave.leaveElevator();
			int newWeight = elevator.getWeight();
			assertTrue(oldWeight - newWeight == personToLeave.getWeight());
		} catch (Exception e) {
			assertTrue(false);
		}
		
		//Assertions
		
		stepCounter++;
	}

	@Then("push button to close door")
	public void push_button_to_close_door() {
	    try {
			elevator.acceptCloseButtonPress();
		} catch (Exception e) {
			assertTrue(false);
		}
	    
		stepCounter++;
	}

	@Then("door closed")
	public void door_closed() {
	    try {
			elevator.closeDoors();
		} catch (Exception e) {
			assertTrue(false);
		}
	    
		stepCounter++;
	}

	@Then("reach target floor")
	public void reach_target_floor() {
	    try {
			elevator.reachTargetFloor();
		} catch (Exception e) {
			assertTrue(false);
		}
	    
	    if (executiveFloorCalled)
	    	assertTrue(elevator.getCurrentFloorID() == elevator.getExecutiveFloorID());
	    
	    executiveFloorCalled = false;
	    
		stepCounter++;
	}

	@Then("elevator empty for ten seconds")
	public void elevator_empty_for_ten_seconds() {
		try {
			elevator.emptyElevator();
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	//Private Methods
	private String nextStep() {
		if (stepCounter < steps.length-1)
			return steps[stepCounter+1];
		return "";
	}
	
	private boolean willContinue() {
		boolean result = false;
	
		for (int i = stepCounter+1; i < steps.length-1; i++) {
			if (steps[i].equals("push button to close door")) {
				result = true;
				break;
			} else if (steps[i].equals("print overloaded alarm")) {
				break;
			}
		}
		return result;
	}
	
	private int randomFloor() {
		int result = new Random().nextInt((9) + 1);
		while (result == elevator.getExecutiveFloorID())
			result = new Random().nextInt((9) + 1);
		return result;
	}
	
	private int randomFloor(int exclude) {
		int result = new Random().nextInt((9) + 1);
		while (result == exclude)
			result = new Random().nextInt((9) + 1);
		return result;
	}
	
	private int randomWeight() {
		return new Random().nextInt((99) + 1);
	}

	private IPerson getRandomPersonInElevator() {
		IPerson result = null;
		for (IPerson p : elevator.getPersons()) {
			result = p;
			break;
		}
		
		return result;
	}
	
	private IPerson getRandomOverweightPersonInElevator() {
		IPerson result = null;
		for (IPerson p : elevator.getPersons()) {
			if (p.getWeight() == OVERWEIGHT_PERSON_WEIGHT) {
				result = p;
				break;
			}
		}
		return result;
	}
}
