package ElevatorSystem; 

import java.util.List; 
import java.util.ArrayList; 

public  class  Floor  implements IFloor {
	
	
	private int id;

	
	private boolean elevatorCall;

	
	private IEnvironment environment;

	
	private List<IPerson> waitingPersons;

	
	
	//Constructors
	public Floor(int id, IEnvironment environment) {
		super();
		this.id = id;
		this.elevatorCall = false;
		this.environment = environment;
		this.waitingPersons = new ArrayList<IPerson>();
	}

	

	//Public Methods
	@Override
	public void acceptElevatorCall(IPerson person) throws Exception {
		if (person == null)
			throw new Exception();
		
		if (environment.getElevator().getExecutiveFloorID() == this.id) {
			if (!environment.getElevator().hasExecutiveCallRight())
				throw new Exception();
			this.elevatorCall = true;
			environment.getElevator().useNormalCallRight();
			environment.getElevator().useExecutiveCallRight();
			if (!waitingPersons.contains(person))
				waitingPersons.add(person);
		} else {
			if (!environment.getElevator().hasNormalCallRight())
				throw new Exception();
			this.elevatorCall = true;
			environment.getElevator().useNormalCallRight();
			if (!waitingPersons.contains(person))
				waitingPersons.add(person);
		}
	}

	
	
	@Override
	public void resetElevatorCall() {
		this.elevatorCall = false;
	}

	
	
	@Override
	public void releasePerson(IPerson person) {
		if (person == null)
			return;
		
		waitingPersons.remove(person);
	}

	
	
	@Override
	public IPerson getNextWaitingPerson() {
		if (waitingPersons.size() == 0)
			return null;
		return waitingPersons.get(0);
	}

	
	
	@Override
	public void addPersonToWaitingList(IPerson person) {
		if (person == null)
			return;
		
		if (person.getOrigin() != this.id)
			return;
		
		if (!waitingPersons.contains(person))
			waitingPersons.add(person);
	}

	

	//Getters
	public int getId() 							{ return id; 				}

	
	public boolean hasElevatorCall() 			{ return elevatorCall; 		}

	
	public IEnvironment getEnvironment() 		{ return environment; 		}

	
	public List<IPerson> getWaitingPersons() 	{ return waitingPersons; 	}


}
