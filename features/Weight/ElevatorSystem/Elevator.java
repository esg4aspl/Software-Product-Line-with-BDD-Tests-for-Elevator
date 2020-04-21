package ElevatorSystem;

import java.util.Arrays;

public class Elevator implements IElevator {
	
	private int weight;
	
	public Elevator(IEnvironment environment) {
		this.weight = 0;
	}
	
	public void acceptPerson(IPerson person) throws Exception {
		original(person);
		weight += person.getWeight();
	}
	
	public void releasePerson(IPerson person) throws Exception {
		original(person);
		weight -= person.getWeight();
	}
	
	public int getWeight() { 
		return weight;
	}
	
	public String toString() {
		return original() + "\n\tweight=" + weight;
	}
	
}