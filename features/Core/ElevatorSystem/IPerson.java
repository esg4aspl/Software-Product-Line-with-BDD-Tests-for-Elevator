package ElevatorSystem;

public interface IPerson {
	
	//Getters
	public String getName();
	public int getWeight();
	public int getOrigin();
	public int getTarget();
	public IEnvironment getEnvironment();
	
	//Methods
	public void callElevator() throws Exception;
	public void enterElevator() throws Exception;
	public void pressTargetFloorButton() throws Exception;
	public void leaveElevator() throws Exception;

}
