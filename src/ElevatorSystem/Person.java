package ElevatorSystem; 

public  class  Person  implements IPerson {
	
	
	private String name;

	
	private int weight;

	
	private int origin;

	
	private int target;

	
	private IEnvironment environment;

	
	
	//Constructors
	public Person(String name, int weight, int origin, int target, IEnvironment environment) {
		super();
		this.name = name;
		this.weight = weight;
		this.origin = origin;
		this.target = target;
		this.environment = environment;
	}

	

	//Public Methods
	@Override
	public void callElevator() throws Exception {
		environment.getFloor(origin).acceptElevatorCall(this);
	}

	

	@Override
	public void enterElevator() throws Exception {
		environment.getElevator().acceptPerson(this);
		environment.getFloor(origin).releasePerson(this);
	}

	

	@Override
	public void pressTargetFloorButton() throws Exception {
		environment.getElevator().acceptTargetFloorButtonPress(this);
	}

	

	@Override
	public void leaveElevator() throws Exception {
		environment.getElevator().releasePerson(this);
	}

	
	
	//Getters
	public String getName() 			{ return name; 			}

	
	public int getWeight() 				{ return weight; 		}

	
	public int getOrigin() 				{ return origin; 		}

	
	public int getTarget() 				{ return target; 		}

	
	public IEnvironment getEnvironment() { return environment; 	}


}
