package ElevatorSystem; 

import java.util.Arrays; 
import java.util.HashSet; 
import java.util.Set; 

public   class  Elevator  implements IElevator {
	
	public enum  DoorState { OPEN ,  CLOSED}

	;

	
	public enum  Direction { UP ,  DOWN}

	;

	
	
	private IEnvironment environment;

	
	private boolean normalCallRight;

	
	private DoorState doors;

	
	private boolean reachedTargetFloor;

	
	private boolean hasMovedSinceInitialization;

	
	private boolean closeButtonPressed;

	
	private Set<IPerson> persons;

	
	private Set<IPerson> passengers;

	
	private boolean[] floorButtons;

	
	private int currentFloorID;

	
	private Direction currentDirection;

	
	private boolean newInput;

	 //Accept Target Floor Button Press, Accept Person, Accept Close Button Press
	private boolean emptied;

	
	
	public Elevator  (IEnvironment environment) {
		super();
		this.environment = environment;
		this.normalCallRight = true;
		this.doors = DoorState.CLOSED;
		this.reachedTargetFloor = false;
		this.hasMovedSinceInitialization = false;
		this.closeButtonPressed = false;
		this.persons = new HashSet<IPerson>();;
		this.passengers = new HashSet<IPerson>();;
		this.floorButtons = new boolean[environment.getFloors().length];
		this.currentFloorID = 0;
		this.newInput = false;
		this.emptied = false;
	
		this.weight = 0;
	
		this.overloaded = false;
		this.overloadedAlarmPrinted = false;
	
		this.executiveCallRight = true;
	}

	
	
	//Getters & Setters TODO Make copies
	public IEnvironment getEnvironment() 			{ return environment; 					}

	
	public boolean hasNormalCallRight() 			{ return normalCallRight; 				}

	
	public void useNormalCallRight() 				{ this.normalCallRight = false; 		}

	
	public DoorState getDoorState() 				{ return doors; 						}

	
	public boolean hasReachedTargetFloor() 			{ return reachedTargetFloor; 			}

	
	public boolean isCloseButtonPressed() 			{ return closeButtonPressed; 			}

	
	public Set<IPerson> getPersons() 				{ return persons; 						}

	
	public Set<IPerson> getPassengers() 			{ return passengers; 					}

	
	public boolean[] getFloorButtons() 				{ return floorButtons; 					}

	
	public int getCurrentFloorID() 					{ return currentFloorID; 				}

	
	public boolean isEmptied()						{ return emptied;						}

	
	
	public int getWeight  () { 
		return weight;
	}

	
	
	public boolean isOverloadedAlarmPrinted  () { 
		return overloadedAlarmPrinted; 
	}

	
	
	public int getExecutiveFloorID  () { 
		return EXECUTIVE_FLOOR;
	}

	
	
	public boolean hasExecutiveCallRight  () { 
		return executiveCallRight;
	}

	
	
	public void useExecutiveCallRight  () {
		this.executiveCallRight = false;
	}

	
	
	public void printOverloadedAlarm  () throws Exception {
		//Pre-Conditions
		if (doors != DoorState.OPEN)
			throw new Exception();
		
		if (!overloaded)
			throw new Exception();
		
		if (overloadedAlarmPrinted)
			throw new Exception();
		
		//Body
		overloadedAlarmPrinted = true;
	}

	

	//Public Methods
	
	 private void  openDoors__wrappee__Core() throws Exception {
		//Pre-Conditions
		if (emptied)
			throw new Exception();
		
		if (doors != DoorState.CLOSED)
			throw new Exception();
		
		if (!environment.getFloor(currentFloorID).hasElevatorCall() && !reachedTargetFloor)
			throw new Exception();
		
		if (!reachedTargetFloor && hasMovedSinceInitialization)
			throw new Exception();
		
		//Body
		doors = DoorState.OPEN;
		reachedTargetFloor = false;
		hasMovedSinceInitialization = true;
		environment.getFloor(currentFloorID).resetElevatorCall();
		normalCallRight = false;
		newInput = false;
	}

	
	
	public void openDoors() throws Exception {
		openDoors__wrappee__Core();
		executiveCallRight = false;
	}

	

	
	 private void  acceptPerson__wrappee__Core(IPerson person) throws Exception {
		//Pre-Conditions
		if (emptied)
			throw new Exception();
		
		if (doors != DoorState.OPEN || closeButtonPressed)
			throw new Exception();
		
		if (person == null)
			throw new Exception();
		
		//Body
		persons.add(person);
		newInput = true;
	}

	
	
	 private void  acceptPerson__wrappee__Weight  (IPerson person) throws Exception {
		acceptPerson__wrappee__Core(person);
		weight += person.getWeight();
	}

	
	
	public void acceptPerson(IPerson person) throws Exception {
		if (overloaded)
			throw new Exception();
		
		acceptPerson__wrappee__Weight(person); //After Weight feature
		
		if (weight > MAXIMUM_WEIGHT)
			overloaded = true;
	}

	
	
	
	 private void  acceptTargetFloorButtonPress__wrappee__Core(IPerson person) throws Exception {
		//Pre-Conditions
		if (emptied)
			throw new Exception();
		
		if (doors != DoorState.OPEN || closeButtonPressed)
			throw new Exception();
		
		if (person == null)
			throw new Exception();
		
		if (!persons.contains(person))
			throw new Exception();
		
		//Body
		passengers.add(person);
		floorButtons[person.getTarget()] = true;
		newInput = true;
	}

	
	
	@Override
	public void acceptTargetFloorButtonPress(IPerson person) throws Exception {
		if (overloaded)
			throw new Exception();
		
		acceptTargetFloorButtonPress__wrappee__Core(person);
	}

	
	
	
	 private void  releasePerson__wrappee__Core(IPerson person) throws Exception {
		//Pre-Conditions
		if (emptied)
			throw new Exception();
		
		if (doors != DoorState.OPEN || closeButtonPressed)
			throw new Exception();
		
		if (person == null)
			throw new Exception();
		
		//Body
		persons.remove(person);
		passengers.remove(person);
		newInput = true;
	}

	
	
	 private void  releasePerson__wrappee__Weight  (IPerson person) throws Exception {
		releasePerson__wrappee__Core(person);
		weight -= person.getWeight();
	}

	
	
	public void releasePerson(IPerson person) throws Exception {
		releasePerson__wrappee__Weight(person); //After Weight feature
		
		if (weight <= MAXIMUM_WEIGHT) {
			overloaded = false;
			overloadedAlarmPrinted = false;
		}
	}

	

	
	 private void  acceptCloseButtonPress__wrappee__Core() throws Exception {
		//Pre-Conditions
		if (emptied)
			throw new Exception();
		
		if (doors != DoorState.OPEN || closeButtonPressed)
			throw new Exception();
		
		if (passengers.size() == 0)
			throw new Exception();
		
		//Body
		closeButtonPressed = true;
		newInput = true;
	}

	
	
	public void acceptCloseButtonPress() throws Exception {
		if (overloaded)
			throw new Exception();
		
		acceptCloseButtonPress__wrappee__Core();
	}

	

	@Override
	public void closeDoors() throws Exception {
		//Pre-Conditions
		if (emptied)
			throw new Exception();
		
		if (doors != DoorState.OPEN)
			throw new Exception();
		
		if (!closeButtonPressed)
			throw new Exception();
		
		//Body
		doors = DoorState.CLOSED;
		closeButtonPressed = false;
		reachedTargetFloor = false;
	}

	

	@Override
	public void emptyElevator() throws Exception {
		//Pre-Conditions
		if (emptied)
			throw new Exception();
		
		if (doors != DoorState.OPEN)
			throw new Exception();
		
		if (newInput)
			throw new Exception();
		
		//Body
		persons = new HashSet<IPerson>();
		passengers = new HashSet<IPerson>();
		emptied = true;
		doors = DoorState.CLOSED;
	}

	
	
	
	 private void  reachTargetFloor__wrappee__Core() throws Exception {
		//Pre-Conditions
		if (emptied)
			throw new Exception();
		
		if (doors != DoorState.CLOSED)
			throw new Exception();
		
		if (reachedTargetFloor)
			throw new Exception();
		
		int targetFloor = findTargetFloor();
		if (targetFloor == -1) //Elevator doen't need to move
			throw new Exception();
		
		//Body
		if (targetFloor > currentFloorID)
			currentDirection = Direction.UP;
		else if (targetFloor < currentFloorID)
			currentDirection = Direction.DOWN;
		
		currentFloorID = targetFloor;
		reachedTargetFloor = true;
		hasMovedSinceInitialization = true;
		normalCallRight = false;
	}

	

	public void reachTargetFloor() throws Exception {
		reachTargetFloor__wrappee__Core();
		executiveCallRight = false;
	}

	
	
	//Private Methods
	 private int  findTargetFloor__wrappee__Core  () {
		int result = -1;
		if (currentDirection == Direction.UP) {
			result = checkHigherFloors();
			if (result == -1)
				result = checkLowerFloors();
		} else {
			result = checkLowerFloors();
			if (result == -1)
				result = checkHigherFloors();
		}
		
		return result;
	}

	
	
	protected int findTargetFloor() {
		if (floorButtons[EXECUTIVE_FLOOR] || environment.getFloor(EXECUTIVE_FLOOR).hasElevatorCall())
			return EXECUTIVE_FLOOR;
		
		return findTargetFloor__wrappee__Core();
	}

	
	
	private int checkHigherFloors() {
		IFloor[] floors = environment.getFloors();
		for (int i = currentFloorID; i < floors.length; i++)
			if (floorButtons[i] || floors[i].hasElevatorCall())
				return i;
		
		return -1;
	}

	
	
	private int checkLowerFloors() {
		IFloor[] floors = environment.getFloors();
		for (int i = currentFloorID; i >= 0; i--)
			if (floorButtons[i] || floors[i].hasElevatorCall())
				return i;
		
		return -1;
	}

	
	
	//Other Methods
	
	 private String  toString__wrappee__Core() {
		return "Elevator"
				+ "\n\tnormalCallRight=" + normalCallRight + "\n\tdoors=" + doors + "\n\treachedTargetFloor="
				+ reachedTargetFloor + "\n\thasMovedSinceInitialization=" + hasMovedSinceInitialization
				+ "\n\tcloseButtonPressed=" + closeButtonPressed + "\n\tpersons=" + persons + "\n\tpassengers=" + passengers
				+ "\n\tfloorButtons=" + Arrays.toString(floorButtons) + "\n\tcurrentFloorID=" + currentFloorID
				+ "\n\tcurrentDirection=" + currentDirection 
				+ "\n\tnewInput=" + newInput;
	}

	
	
	 private String  toString__wrappee__Weight  () {
		return toString__wrappee__Core() + "\n\tweight=" + weight;
	}

	

	
	 private String  toString__wrappee__Overloaded  () {
		return toString__wrappee__Weight() + "\n\toverloaded=" + overloaded + "\n\toverloadedAlarmPrinted=" + overloadedAlarmPrinted;
	}

	
	
	public String toString() {
		return toString__wrappee__Overloaded() + "\n\texecutiveCallRight=" + executiveCallRight;
	}

	
	
	private int weight;

	
	
	private static final int MAXIMUM_WEIGHT = 5000;

	
	private boolean overloaded;

	
	private boolean overloadedAlarmPrinted;

	

	private static final int EXECUTIVE_FLOOR = 7;

	
	
	private boolean executiveCallRight;


}
