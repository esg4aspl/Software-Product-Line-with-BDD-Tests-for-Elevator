package gherkincreators;

import java.util.List;

public class Feature {
	
	private String name;
	private List<Scenario> scenarios;
	
	public Feature(String name, List<Scenario> scenarios) {
		super();
		this.name = name;
		this.scenarios = scenarios;
	}

	public String getName() {
		return name;
	}

	public List<Scenario> getScenarios() {
		return scenarios;
	}

	@Override
	public String toString() {
		String result = "Feature: " + name + "\n";
		for (Scenario s : scenarios)
			result += s.toString() + "\n";
		return result;
	}
	
}
