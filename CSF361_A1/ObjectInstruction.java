import java.util.*;
import java.io.*;

public class ObjectInstruction {
	private static final String BADINSTRUCTION = "Bad Instruction";

	/* readFile function will read into the file line by line */
	public void readFile(Scanner file, ArrayList<String> list, RefMonitor reference, SecureSystem system) {
		/* Declaration of variable */
		String fileLine;
		String[] fileTokens;

		/* Loop to get line by line then split the line up into tokens and parse them */
		while (file.hasNextLine()) {
			fileLine = file.nextLine();
			fileTokens = fileLine.split("\\s+");
			parseStringTokens(fileTokens, list, reference, system);
		}
	}

	/* parseStringTokens function will parse each token in the file line by invoking other classes to help */
	public void parseStringTokens(String[] tokens, ArrayList<String> list, RefMonitor reference, SecureSystem system) {
		boolean goodInstruction = false;
		int result = 0;
		String subjectName, objectName;
		/* if-else-if-case structure to determine whether instructions are valid */
		if (tokens[0].equalsIgnoreCase("WRITE") && tokens.length > 2) {
			subjectName = tokens[1].toLowerCase(); objectName = tokens[2].toLowerCase();
			if (tokens.length == 4) {
				if (list.contains(subjectName) && list.contains(objectName)) {
					goodInstruction = true;
					result = Integer.parseInt(reference.executeWrite(subjectName, objectName, tokens[3], "WRITE", system)); //?
				}
			}
		}
		else if (tokens[0].equalsIgnoreCase("READ") && tokens.length > 2) {
			subjectName = tokens[1].toLowerCase(); objectName = tokens[2].toLowerCase();
			if (tokens.length == 3) {
				if (list.contains(subjectName) && list.contains(objectName)) {
					goodInstruction = true;
					result = Integer.parseInt(reference.executeRead(subjectName, objectName, "READ", system)); //?
				}
			}
		}

		/* Test cases failed above, then send bad instruction; result set back to false for next iteration */
		if (!goodInstruction) {
			reference.handlerString(BADINSTRUCTION, tokens);
		}
		else {
			reference.handlerString(tokens[0], tokens);
		}
		printState(reference, list, result);
	}

	/* printResult void function will output the results for each line */
	public void printState(RefMonitor reference, ArrayList<String> list, int result) {
		int count = 2;
		boolean end = false;
		System.out.println("The current state is: ");
		
		while (count < list.size()) {
			if (count >= 2) {
				System.out.println("	" + list.get(count) + " has value: " + reference.object.get(list.get(count))[1]);
			}
			else {
				System.out.println("	" + list.get(count) + " has recently read: " + reference.subject.get(list.get(count))[1]);
			}
			count++;
			if (count > 3) {
				count = 0; 
				end = true;
			}
			else if (count == 2) {
				if (end) {
					break;
				}
			}
		}
	}
}
