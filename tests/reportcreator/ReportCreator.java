package reportcreator;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReportCreator {

	private boolean showOnlyFailedTestSequences;
	private String summary;
	private String header;
	private String result;
	private String formattedResult;
	private JSONArray jsonarray;
	private int numberOfPassedTestSequences;
	private int numberOfFailedTestSequences;
	
	public ReportCreator(String filePath, boolean showOnlyFailedTestSequences) {
		JSONParser parser = new JSONParser();
		
        try (FileReader reader = new FileReader(filePath))
        {
        	Object obj = parser.parse(reader);
        	this.jsonarray = (JSONArray) obj;
        	this.result = "";
        	this.formattedResult = "";
        	this.summary = "";
        	this.header = "";
        	this.numberOfPassedTestSequences = 0;
        	this.numberOfFailedTestSequences = 0;
        	this.showOnlyFailedTestSequences = showOnlyFailedTestSequences;
        	createResult();
        	formatResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	//Public Methods
	public void createReport(String filePath) {
		try {
		    FileWriter myWriter = new FileWriter(filePath);
		    myWriter.write(getReport());
		    myWriter.close();
		    System.out.println("Successfully created report at " + filePath);
		} catch (IOException e) {
		    System.out.println("An error occurred while creating reports.");
		    e.printStackTrace();
		}
	}
	
	//Private Methods
	private void createResult() {
		for (Object obj : jsonarray) { 
			JSONObject testseq = (JSONObject) obj;
			JSONArray elements = (JSONArray) testseq.get("elements");
			
			boolean sequencePassed = true;
			
			
			for (Object scenarioObject : elements) {
				JSONObject scenario = (JSONObject) scenarioObject;
				
				
				if (scenario.get("type").equals("background"))
					continue;

				
				JSONArray steps = (JSONArray) scenario.get("steps");
				
				boolean failed = false;
				
				List<String> stepsSoFar = new ArrayList<String>();
				
				for (Object stepObject : steps) {
					JSONObject step = (JSONObject) stepObject;
					stepsSoFar.add((String) step.get("name"));
					if (((JSONObject) step.get("result")).get("status").equals("failed")) {
						failed = true;
						break;
					}
				}
				int size = stepsSoFar.size();
				if (failed) {
					sequencePassed = false;
					result += testseq.get("name");
					result += ",failed," + stepsSoFar.get(size-2) + " -> " + stepsSoFar.get(size-1) + ",[Scenario " + scenario.get("name") + " Step " + size + "]";
					result += "\n";
					numberOfFailedTestSequences++;
					break;
				}
			}
			if (sequencePassed) {
				if (!showOnlyFailedTestSequences) {
					result += testseq.get("name");
					result += ",passed, , ";
					result += "\n";
				}
				numberOfPassedTestSequences++;
			}
		}
		
		if (numberOfPassedTestSequences == jsonarray.size()) {
			summary = "All " + numberOfPassedTestSequences + " test sequences passed.";
		} else if (numberOfFailedTestSequences == jsonarray.size()) {
			summary = "All " + numberOfFailedTestSequences + " test sequences failed.";
		} else {
			summary = numberOfPassedTestSequences + " test sequences passed, " + numberOfFailedTestSequences + " test sequences failed.";
		}
	}

	private void formatResult() {
		if (result.length() == 0) {
			return;
		}
		String[] titles = { "GHERKIN FEATURE", "STATUS", "TRANSITION WHERE THE ERROR OCCURED", "ERROR LOCATION" };
		int[] colLengths = {getMaxColLength(0, titles[0].length()), getMaxColLength(1, titles[1].length()), getMaxColLength(2, titles[2].length()), getMaxColLength(3, titles[3].length())};
		
		header += "| ";
		for (int c = 0; c < titles.length; c++) {
			header += titles[c];
			int colLength = colLengths[c];
			int titleLength = titles[c].length();
			for (int i = 0; i < colLength - titleLength; i++) {
				header += " ";
			}
			if (c != titles.length - 1) 
				header += " | ";
			else
				header += " |";
		}
		
		String[] lines = result.split("\n");
		int counter = 0;
		for (String line : lines) {
			String[] elements = line.split(",");
			formattedResult += "| ";
			for (int eCounter = 0; eCounter < elements.length; eCounter++) {
				formattedResult += elements[eCounter];
				int colLength = colLengths[eCounter];
				int elementLength = elements[eCounter].length();
				for (int i = 0; i < colLength - elementLength; i++) {
					formattedResult += " ";
				}
				if (eCounter != elements.length -1)
					formattedResult += " | ";
				else
					formattedResult += " |";
			}
			
			if (counter != lines.length -1)
				formattedResult += "\n";
			
			counter++;
		}
	}
	
	private int getMaxColLength(int col, int minLength) {
		String[] lines = result.split("\n");
		int longestLength = minLength;
		for (String line : lines) {
			String[] elements = line.split(",");
			int currentLength = elements[col].length();
			if (currentLength > longestLength) {
				longestLength = currentLength;
			}
		}
		return longestLength;
	}


	//Getters
	public String getSummary() 							{ return summary; 						}
	public String getResult() 							{ return result; 						}
	public String getFormattedResult() 					{ return formattedResult; 				}
	public int getNumberOfPassedTestSequences() 		{ return numberOfPassedTestSequences; 	}
	public int getNumberOfFailedTestSequences() 		{ return numberOfFailedTestSequences; 	}
	public String getReport() {
		if (formattedResult.length() == 0)
			return summary;
		
		String finalSummary = "";
		finalSummary += summary;
		if (showOnlyFailedTestSequences)
			finalSummary += " (Showing only the failed test sequences)";
		return finalSummary + "\n\n" + header + "\n" + formattedResult; 
	}

	public static void main (String[] args) {
		
		ReportCreator rc = new ReportCreator("./target/cuke-results.json", false);
		rc.createReport("./target/customized-results.txt");
		
	}
	
}
