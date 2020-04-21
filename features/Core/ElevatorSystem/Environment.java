package ElevatorSystem;

public class Environment implements IEnvironment {
	
	private IFloor[] floors;
	private IElevator elevator;
	
	//Constructors
	public Environment(int numberOfFloors) {
		this.floors = new IFloor[numberOfFloors];
		for (int i = 0; i < numberOfFloors; i++) {
			this.floors[i] = new Floor(i, this);
		}
		this.elevator = new Elevator(this);
	}
	
	//Getters TODO Make copies
	public IFloor[] getFloors() 		{ return floors; 		}
	public IElevator getElevator() 		{ return elevator; 		}
	
	public IFloor getFloor(int id) { 
		if (id < 0 || id >= floors.length)
			return null;
		
		return floors[id];	
	}
}