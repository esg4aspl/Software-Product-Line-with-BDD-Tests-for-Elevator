package ElevatorSystem;

import java.util.List;

public interface IFloor {

	//Getters
	public int getId();
	public boolean hasElevatorCall();
	public IEnvironment getEnvironment();
	public List<IPerson> getWaitingPersons();
	
	//Methods
	public void acceptElevatorCall(IPerson person) throws Exception;
	public void resetElevatorCall();
	public void releasePerson(IPerson person);
	public IPerson getNextWaitingPerson();
	public void addPersonToWaitingList(IPerson person);
	
}
