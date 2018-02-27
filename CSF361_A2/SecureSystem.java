import java.util.*;
import java.io.*;

public class SecureSystem {
	/* Security label domination; High > Low */
	private static final String HIGH = "1", LOW = "0";

	/* Variable and instance declarations */	
	String[] subjectInfo;
	ArrayList<String> subjectList = new ArrayList<String>();
	ArrayList<String> objectList = new ArrayList<String>();
	RefMonitor reference = new RefMonitor();
	RefMonitor.ObjectManager objectInstance = reference.new ObjectManager();

	/* Main function for execution the program */
	public void securityStart(String[] args, int isVerbose, SecureSystem system) throws IOException {	
		/* Variable declarations */
		String value = "0", tempValue = "0", fileName = "";
		Scanner scannedFile;

		if (isVerbose == 1) {
		    System.out.println("Reading from file: " + args[1]);
		    fileName = args[1];
		}
		else {
    		System.out.println("Reading from file: " + args[0]);
    		fileName = args[0];
		}

		/* Create subject and object as needed */
		system.initializeSubject("lyle", tempValue, system.LOW);
		system.initializeSubject("hal", tempValue, system.HIGH);


		/* Read file contents to ObjectInstruction */
		ObjectInstruction instructions = new ObjectInstruction();
		if (isVerbose == 1) {
			scannedFile  = new Scanner(new File(args[1]));
		}
		else {
	    	scannedFile  = new Scanner(new File(args[0]));
		}
		instructions.readFile(isVerbose, scannedFile, system.subjectList, system.objectList, reference, system, fileName);
		scannedFile.close();
	}

	/* initializeSubject void will set up the subject’s info; name and value  */
	public void initializeSubject (String subjectName, String tempValue, String securityLevel) {
		/* store subject info into an array and add name to list of subjects and objects */
		subjectInfo = new String[3];
		subjectInfo[0] = subjectName;
		subjectInfo[1] = tempValue;
		subjectInfo[2] = securityLevel;
		subjectList.add(subjectName.toLowerCase());

		/* Store subject into hashtable with label */
		secureSubject(subjectInfo);
	}

	/* initializeObject void will set up the objects info; name and value in ObjectManager class */
	public void initializeObject (String objectName, String value, String securityLevel) {
		objectInstance.newObject(objectName, value, securityLevel, objectList);
	}

	/* secureSubject function will setup the variables map labels and stored in RefMonitor */
	public void secureSubject(String[] subjectInfo) {
		reference.subject.put(subjectInfo[0], subjectInfo);
	}

	/* DominationRule boolean function will determine if the subject’s clearance is enough */
	public boolean dominationRule(String command, String subjectLevel, String objectLevel) {
		if (command.equalsIgnoreCase("WRITE")) {
			if (Integer.parseInt(subjectLevel) <= Integer.parseInt(objectLevel)) {
				return true;
			}
			return false;
		}
		else if (command.equalsIgnoreCase("READ")) {
			if (Integer.parseInt(subjectLevel) >= Integer.parseInt(objectLevel)) {
				return true;
			}
			return false;
		}
		return false;
	}
}