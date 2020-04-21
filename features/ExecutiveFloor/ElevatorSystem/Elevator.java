package ElevatorSystem;

public class Elevator implements IElevator {

	private static final int EXECUTIVE_FLOOR = 7;
	
	private boolean executiveCallRight;
	
	public Elevator(IEnvironment environment) {
		this.executiveCallRight = true;
	}
	
	public int getExecutiveFloorID() { 
		return EXECUTIVE_FLOOR;
	}
	
	public boolean hasExecutiveCallRight() { 
		return executiveCallRight;
	}
	
	public void useExecutiveCallRight() {
		this.executiveCallRight = false;
	}
	
	protected int findTargetFloor() {
		if (floorButtons[EXECUTIVE_FLOOR] || environment.getFloor(EXECUTIVE_FLOOR).hasElevatorCall())
			return EXECUTIVE_FLOOR;
		
		return original();
	}
	
	public void openDoors() throws Exception {
		original();
		executiveCallRight = false;
	}

	public void reachTargetFloor() throws Exception {
		original();
		executiveCallRight = false;
	}
	
	public String toString() {
		return original() + "\n\texecutiveCallRight=" + executiveCallRight;
	}
	
}