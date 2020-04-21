package gherkincreators;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
	
	private boolean background;
	private String name;
	private List<String> givens;
	private List<String> whens;
	private List<String> thens;
	
	public Scenario(String given, String when, String then) {
		this(false, "Unnamed", given, when, then);
	}
	
	public Scenario(String name, String given, String when, String then) {
		this(false, name, given, when, then);
	}
	
	public Scenario(boolean background, String name, String given, String when, String then) {
		List<String> givens = new ArrayList<String>();
		List<String> whens = new ArrayList<String>();
		List<String> thens = new ArrayList<String>();
		if (given != null)
			givens.add(given);
		if (when != null)
			whens.add(when);
		if (then != null)
			thens.add(then);
		this.background = background;
		this.name = name;
		this.givens = givens;
		this.whens = whens;
		this.thens = thens;
	}
	
	public Scenario(List<String> givens, List<String> whens, List<String> thens) {
		this(false, "Unnamed", givens, whens, thens);
	}

	public Scenario(String name, List<String> givens, List<String> whens, List<String> thens) {
		this(false, name, givens, whens, thens);
	}

	public Scenario(boolean background, String name, List<String> givens, List<String> whens,
			List<String> thens) {
		super();
		this.background = background;
		this.name = name;
		this.givens = givens;
		this.whens = whens;
		this.thens = thens;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isBackground() {
		return background;
	}

	public List<String> getGivens() {
		return givens;
	}

	public List<String> getWhens() {
		return whens;
	}

	public List<String> getThens() {
		return thens;
	}


	@Override
	public String toString() {
		String result = "";
		
		if (background)
			result += "Background: ";
		else
			result += "Scenario: ";
		result += name + "\n";
		
		for (int i = 0; i < givens.size(); i++) {
			String given = givens.get(i);
			if (i == 0)
				result += "Given ";
			else
				result += "And ";
			
			result += given + "\n";
		}
		
		for (int i = 0; i < whens.size(); i++) {
			String when = whens.get(i);
			if (i == 0)
				result += "When ";
			else
				result += "And ";
			
			result += when + "\n";
		}
		
		for (int i = 0; i < thens.size(); i++) {
			String then = thens.get(i);
			if (i == 0)
				result += "Then ";
			else
				result += "And ";
			
			result += then + "\n";
		}
		
		return result;
	}
	
}
