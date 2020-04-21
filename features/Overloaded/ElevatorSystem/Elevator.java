package ElevatorSystem;

public class Elevator implements IElevator {
	
	private static final int MAXIMUM_WEIGHT = 5000;
	private boolean overloaded;
	private boolean overloadedAlarmPrinted;
	
	public Elevator(IEnvironment environment) {
		this.overloaded = false;
		this.overloadedAlarmPrinted = false;
	}
	
	public boolean isOverloadedAlarmPrinted() { 
		return overloadedAlarmPrinted; 
	}
	
	public void acceptPerson(IPerson person) throws Exception {
		if (overloaded)
			throw new Exception();
		
		original(person); //After Weight feature
		
		if (weight > MAXIMUM_WEIGHT)
			overloaded = true;
	}
	
	@Override
	public void acceptTargetFloorButtonPress(IPerson person) throws Exception {
		if (overloaded)
			throw new Exception();
		
		original(person);
	}
	
	public void printOverloadedAlarm() throws Exception {
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
	
	public void releasePerson(IPerson person) throws Exception {
		original(person); //After Weight feature
		
		if (weight <= MAXIMUM_WEIGHT) {
			overloaded = false;
			overloadedAlarmPrinted = false;
		}
	}
	
	public void acceptCloseButtonPress() throws Exception {
		if (overloaded)
			throw new Exception();
		
		original();
	}

	
	public String toString() {
		return original() + "\n\toverloaded=" + overloaded + "\n\toverloadedAlarmPrinted=" + overloadedAlarmPrinted;
	}
	
}