package ElevatorSystem; 

import java.util.Set; 

import ElevatorSystem.Elevator.DoorState; 

public  interface  IElevator {
	

	//Core Getters & Setters
	public IEnvironment getEnvironment();

	
	public boolean hasNormalCallRight();

	
	public DoorState getDoorState();

	
	public boolean hasReachedTargetFloor();

	
	public boolean isCloseButtonPressed();

	
	public Set<IPerson> getPersons();

	
	public Set<IPerson> getPassengers();

	
	public boolean[] getFloorButtons();

	
	public int getCurrentFloorID();

	
	public void useNormalCallRight();

	
	public boolean isEmptied();

	
	
	//Core Methods
	public void reachTargetFloor() throws Exception;

	
	public void openDoors() throws Exception;

	
	public void acceptPerson(IPerson person) throws Exception;

	
	public void acceptTargetFloorButtonPress(IPerson person) throws Exception;

	
	public void releasePerson(IPerson person) throws Exception;

	
	public void acceptCloseButtonPress() throws Exception;

	
	public void closeDoors() throws Exception;

	
	public void emptyElevator() throws Exception;

	
	
	//Weight
	public int getWeight();

	
	
	//Overloaded
	public boolean isOverloadedAlarmPrinted();

	
	public void printOverloadedAlarm() throws Exception;

	
	
	//ExecutiveFloor
	public boolean hasExecutiveCallRight();

	
	public int getExecutiveFloorID();

	
	public void useExecutiveCallRight();


}
