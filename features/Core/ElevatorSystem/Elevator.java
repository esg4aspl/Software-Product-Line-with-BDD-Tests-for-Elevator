package ElevatorSystem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Elevator implements IElevator {
	public enum DoorState { OPEN, CLOSED };
	public enum Direction { UP, DOWN };
	
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
	private boolean newInput; //Accept Target Floor Button Press, Accept Person, Accept Close Button Press
	private boolean emptied;
	
	
	//Constructors
	public Elevator(IEnvironment environment) {
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
	
	public int getWeight() 							{ return 0; 							}
	public boolean isOverloadedAlarmPrinted() 		{ return false; 						}
	public int getExecutiveFloorID() 				{ return -1;							}
	public boolean hasExecutiveCallRight() 			{ return false;				 			}
	public void useExecutiveCallRight() 			{ return; 								}
	public void printOverloadedAlarm() throws Exception { return; }

	//Public Methods
	@Override
	public void openDoors() throws Exception {
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

	@Override
	public void acceptPerson(IPerson person) throws Exception {
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
	
	@Override
	public void acceptTargetFloorButtonPress(IPerson person) throws Exception {
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
	public void releasePerson(IPerson person) throws Exception {
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

	@Override
	public void acceptCloseButtonPress() throws Exception {
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
	
	@Override
	public void reachTargetFloor() throws Exception {
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
	
	//Private Methods
	protected int findTargetFloor() {
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
	@Override
	public String toString() {
		return "Elevator"
				+ "\n\tnormalCallRight=" + normalCallRight + "\n\tdoors=" + doors + "\n\treachedTargetFloor="
				+ reachedTargetFloor + "\n\thasMovedSinceInitialization=" + hasMovedSinceInitialization
				+ "\n\tcloseButtonPressed=" + closeButtonPressed + "\n\tpersons=" + persons + "\n\tpassengers=" + passengers
				+ "\n\tfloorButtons=" + Arrays.toString(floorButtons) + "\n\tcurrentFloorID=" + currentFloorID
				+ "\n\tcurrentDirection=" + currentDirection 
				+ "\n\tnewInput=" + newInput;
	}

}