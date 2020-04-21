package ElevatorSystem;

public interface IEnvironment {

	//Getters
	public IFloor[] getFloors();
	public IFloor getFloor(int id);
	public IElevator getElevator();
	
}
