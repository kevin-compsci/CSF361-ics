import java.util.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;

public class RefMonitor {
	Hashtable<String, String[]> subject = new Hashtable<String, String[]>();
	Hashtable<String, String[]> object = new Hashtable<String, String[]>();
	ObjectManager objManager = new ObjectManager();	

	/* checkRequest boolean function determines if instruction is legal based on *property & simple security property */
	public boolean checkRequest(String type, String subjectLevel, String objectLevel, SecureSystem system) {
		return system.dominationRule(type, subjectLevel, objectLevel);
	}
	
	/* writeFile void function will turn the binary bit string into a byte and write it to the file  */
    public void writeFile(String bitString, FileOutputStream outputStream) throws IOException {
	    byte byteResult = Byte.parseByte(bitString, 2);
		outputStream.write(byteResult);  //Writes to file based on stream path
    }
	
	/* executeRun void function will add the bit to the string array, if there are 8 bits then write to file */
	public void executeRun(String subjectName, ArrayList<String> tempBytes, FileOutputStream outputStream) throws IOException {
	    String bitString = "";
	    int count = 0;
	    if (tempBytes.size() == 8) {
	        while (count < 8) {
	            bitString += tempBytes.get(count);  //build bit string from array
	            count++;
	        }
	        writeFile(bitString, outputStream);
	        tempBytes.clear();
	    }
	}
	
	/* create String function will create a new object if it doesn’t exist in the system and add it to our system references */
    public String executeCreate(String subjectName, String objectName, SecureSystem system) {
	    String subjectLevel = subject.get(subjectName)[2]; //SUBJECT HAS TO EXIST!!!
		objManager.newObject(objectName, "0", subjectLevel, system.objectList);
		return "0";
    }

    /* destroy void function will get rid of an existing object and null all references */
    public void executeDestroy(String subjectName, String objectName, SecureSystem system) {
        int count = 0;
	    boolean exists = false;
	    while (count < system.objectList.size()) {
		    if (objectName.equalsIgnoreCase(system.objectList.get(count))) {
			    exists = true;
			    String objectLevel = object.get(objectName)[2];
			    break;
		    }
		    count++;
	    }
	    if (exists) {
		    system.objectList.remove(count);
		    object.remove(objectName);
	    }
    }
	
	/* executeRead String function will read and return the current value of the object by telling ObjectManager */
	public String executeRead(String subjectName, String objectName, String type, SecureSystem system) {
		if (checkRequest(type, subject.get(subjectName)[2], object.get(objectName)[2], system)) {
			String result = objManager.objectUpdate(type, subjectName, objectName, "0");
			return "1";
		}
		else {
			subject.get(subjectName)[1] = "0";
			return "0";
		}
	}

	/* executeWrite void function will write and update the current value of the object by telling ObjectManager */
	public String executeWrite(String subjectName, String objectName, String value, String type, SecureSystem system) {
		if (checkRequest(type, subject.get(subjectName)[2], object.get(objectName)[2], system)) {
			return objManager.objectUpdate(type, subjectName, objectName, value);
		}
		return "0";
	}

	/* handlerString void function will print the activity string */
	public void handlerString (String type, String[] tokens, PrintWriter outLog, int isVerbose) {
		if (isVerbose == 1) {
			if (type.equalsIgnoreCase("WRITE")) {
				outLog.write(tokens[1].toLowerCase() + " writes value " + tokens[3] + " to " + tokens[2].toLowerCase() + '\n');
			}
			else if (type.equalsIgnoreCase("READ")) {
				outLog.write(tokens[1].toLowerCase() + " reads " + tokens[2].toLowerCase() + '\n');
			}
			else if (type.equalsIgnoreCase("CREATE")) {
		   		outLog.write(tokens[1].toLowerCase() + " created " + tokens[2].toLowerCase() + '\n');
			}
			else if (type.equalsIgnoreCase("DESTROY")) {
			    outLog.write(tokens[1].toLowerCase() + " destroyed " + tokens[2].toLowerCase() + '\n');
			}
			else if (type.equalsIgnoreCase("RUN")) {
		  	  	outLog.write(tokens[1].toLowerCase() + " RUNS" + '\n');
			}
			else if (type.equalsIgnoreCase("BAD INSTRUCTION")) {
				outLog.write(type + '\n');
			}
		}
	}

	/* Nested class, ObjectManager, that’s  local to the RefMonitor */
	class ObjectManager {
		String[] objectInfo;
		
		/* objectUpdate will update the values in the objects or set it to 0, this assumes true case */
		public String objectUpdate(String type, String subjectName, String objectName, String value) {
			if (type.equalsIgnoreCase("WRITE")) {
				RefMonitor.this.object.get(objectName)[1] = value;
				return value;
			}
			else {
				value = RefMonitor.this.object.get(objectName)[1];
				RefMonitor.this.subject.get(subjectName)[1] = value;
				return value;
			}
		}

		/* newObject will create a new object of data for the subject */
		public void newObject(String objectName, String objectValue, String securityLevel, ArrayList<String> objectList) {
 			/* store object info and add object name to list of subjects and objects */
			objectInfo = new String[3];
			objectInfo[0] = objectName;
			objectInfo[1] = objectValue;
			objectInfo[2] = securityLevel;
			objectList.add(objectName.toLowerCase());
	
			/* Store object info and label into hashmap */
			RefMonitor.this.object.put(objectName.toLowerCase(), objectInfo);
		}
	}
}
