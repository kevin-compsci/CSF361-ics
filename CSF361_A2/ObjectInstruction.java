import java.util.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;

public class ObjectInstruction {
	private static final String BADINSTRUCTION = "Bad Instruction";
	boolean firstRun = true;

	/* readFile function will read into the file line by line */
	public void readFile(int isVerbose, Scanner file, ArrayList<String> subjectList, ArrayList<String> objectList, RefMonitor reference, SecureSystem system, String fileName) throws IOException {
		/* Timer start */
		//double startTime = System.nanoTime(), bitCount = 0.0;

		/* Declaration of variable */
		char bit = ' ';
		int outerCount = 0, innerCount = 0;
		String fileLine, bitString;
		ArrayList<String> tempBytes = new ArrayList<String>();
		fileName = fileName.replace(".txt", "");
        	FileOutputStream outputStream = new FileOutputStream(fileName + ".out");
        	RefMonitor.ObjectManager objectInstance = reference.new ObjectManager();
        	File tempFile = null;
        	if (isVerbose == 1) {
        		tempFile = new File("log.out");
        	}

		/* Loop to get line by line then split the line up into tokens and parse them */
		while (file.hasNextLine()) {
			fileLine = file.nextLine();
			/* Get the bytes from each line */
			byte fileBytes[] = fileLine.getBytes();
		    	while (outerCount < fileBytes.length) {
		        	/* get bits from byte and parse each bit */
		    		bitString = String.format("%8s", Integer.toBinaryString(fileBytes[outerCount] & 0xFF)).replace(' ', '0');
		        	while (innerCount < bitString.length()) {
                    	bit = bitString.charAt(innerCount);
                    	if (bit == '0') {
		                	objectInstance.newObject("OBJ", "2" , "1", objectList);
                    	}
                    	executeInstructions(subjectList, objectList, tempBytes, reference, system, outputStream, isVerbose, tempFile);
		          		innerCount++;
		          		//bitCount++;
		        	}
		        	outerCount++; innerCount = 0;
		   	}
		    	outerCount = 0; innerCount = 0;
		   	outputStream.write('\n');
		}
		outputStream.close();  //closes stream

		/* Bandwidth calculation */
		//double endTime = ((System.nanoTime() - startTime)/1000.0);
		//System.out.println((bitCount/endTime) + " <- Bandwidth ");
	}

    /* executeInstructions void function will run a series of instruction to get covert signal */
    public void executeInstructions(ArrayList<String> subjectList, ArrayList<String> objectList, ArrayList<String> tempBytes, RefMonitor reference, SecureSystem system, FileOutputStream outputStream, int isVerbose, File tempFile) throws IOException {
        String instruction;
        String[] tokens;

        instruction = "CREATE LYLE OBJ";
        tokens = instruction.split("\\s+");
        parseStringTokens(tokens, subjectList, objectList, tempBytes, reference, system, outputStream, isVerbose, tempFile);
	
        instruction = "WRITE LYLE OBJ 3";
        tokens = instruction.split("\\s+");
        parseStringTokens(tokens, subjectList, objectList, tempBytes, reference, system, outputStream, isVerbose, tempFile);

	    instruction = "READ LYLE OBJ";
        tokens = instruction.split("\\s+");
        parseStringTokens(tokens, subjectList, objectList, tempBytes, reference, system, outputStream, isVerbose, tempFile);

	    instruction = "DESTROY LYLE OBJ";
        tokens = instruction.split("\\s+");
        parseStringTokens(tokens, subjectList, objectList, tempBytes, reference, system, outputStream, isVerbose, tempFile);
        
        instruction = "RUN LYLE";
        tokens = instruction.split("\\s+");
        parseStringTokens(tokens, subjectList, objectList, tempBytes, reference, system, outputStream, isVerbose, tempFile);
    }

	/* parseStringTokens function will parse each token in the file line by invoking other classes to help */
	public void parseStringTokens(String[] tokens, ArrayList<String> subjectList, ArrayList<String> objectList, ArrayList<String> tempBytes, RefMonitor reference, SecureSystem system, FileOutputStream outputStream, int isVerbose, File tempFile) throws IOException {
		boolean goodInstruction = false;
		String result = "0";
		String subjectName, objectName;
		
		/* if-else-if-case structure to determine whether instructions are valid */
		if (tokens[0].equalsIgnoreCase("WRITE") && tokens.length > 2) {
			subjectName = tokens[1].toLowerCase(); objectName = tokens[2].toLowerCase();
			if (tokens.length == 4) {
				if (subjectList.contains(subjectName) && objectList.contains(objectName)) {
					goodInstruction = true;
					result = reference.executeWrite(subjectName, objectName, tokens[3], "WRITE", system);
				}
			}
		}
		else if (tokens[0].equalsIgnoreCase("READ") && tokens.length > 2) {
			subjectName = tokens[1].toLowerCase(); objectName = tokens[2].toLowerCase();
			if (tokens.length == 3) {
				if (subjectList.contains(subjectName) && objectList.contains(objectName)) {
					goodInstruction = true;
					result = reference.executeRead(subjectName, objectName, "READ", system);
				}
			}
			tempBytes.add(result);
		}
		else if (tokens[0].equalsIgnoreCase("CREATE") && tokens.length > 2) {
		    goodInstruction = true;
			subjectName = tokens[1].toLowerCase(); objectName = tokens[2].toLowerCase();
			if (tokens.length == 3) {
				if (subjectList.contains(subjectName) && !objectList.contains(objectName)) {
					result = reference.executeCreate(subjectName, objectName, system);
				}
				else {
				    result = "1";
				}
			}
		}
		else if (tokens[0].equalsIgnoreCase("DESTROY") && tokens.length > 2) {
		    	subjectName = tokens[1].toLowerCase(); objectName = tokens[2].toLowerCase();
		    	if (tokens.length == 3) {
			    	if (subjectList.contains(subjectName) && objectList.contains(objectName)) {
			 	   	goodInstruction = true;
		    			reference.executeDestroy(subjectName, objectName, system);
		    		}
	    		}
    		}  
    		else if (tokens[0].equalsIgnoreCase("RUN") && tokens.length > 1) {
		    	subjectName = tokens[1].toLowerCase();
		    	if (tokens.length == 2) {
			    	if (subjectList.contains(subjectName)) {
				    	goodInstruction = true;
		    			reference.executeRun(subjectName, tempBytes, outputStream);
		    		}
		    		else {
		    			System.out.println("GONE");
		    		}
	    		}
    		}   
		
		if (isVerbose == 1) {
			printInfo(goodInstruction, tokens, subjectList, objectList, reference, isVerbose, tempFile);
		}
	}

	/* printInfo void function will print all results accordingly */
	public void printInfo(boolean goodInstruction, String[] tokens, ArrayList<String> subjectList, ArrayList<String> objectList, RefMonitor reference, int isVerbose, File tempFile) throws IOException {
		PrintWriter outLog = null;
		if (firstRun && tempFile.exists()) {
			outLog = new PrintWriter(new BufferedWriter(new FileWriter("log.out", false)));
			firstRun = false;
		}
		else {
			outLog = new PrintWriter(new BufferedWriter(new FileWriter("log.out", true)));
			firstRun = false;
		}
		outLog.println("");

		/* Test cases failed above, then send bad instruction; result set back to false for next iteration */
		if (!goodInstruction) {
			reference.handlerString(BADINSTRUCTION, tokens, outLog, isVerbose);
		}
		else {
			reference.handlerString(tokens[0], tokens, outLog, isVerbose);
		}
		printState(reference, subjectList, objectList, outLog, isVerbose);
		outLog.close();
	}

	/* printResult void function will output the results for each line */
	public void printState(RefMonitor reference, ArrayList<String> subjectList, ArrayList<String> objectList, PrintWriter outLog, int isVerbose) throws IOException {
		int count = 0;
		boolean end = false;
		outLog.println("The current state is: ");

		if (isVerbose == 1) {
			while (count < objectList.size()) {
				outLog.println("	" + objectList.get(count) + " has value: " + reference.object.get(objectList.get(count))[1]);
				count++;
			}
			count = 0;
			while (count < subjectList.size()) {
				outLog.println("	" + subjectList.get(count) + " has recently read: " + reference.subject.get(subjectList.get(count))[1]);
				count++;	
			}
			outLog.println("");
		}
	}
}
