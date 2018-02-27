import java.util.*;
import java.io.*;

public class RefMonitor {
	Hashtable<String, String[]> subject = new Hashtable<String, String[]>();
	Hashtable<String, String[]> object = new Hashtable<String, String[]>();
	ObjectManager objManager = new ObjectManager();	

	/* checkRequest boolean function determines if instruction is legal based on *property & simple security property */
	public boolean checkRequest(String type, String subjectLevel, String objectLevel, SecureSystem system) {
		return system.dominationRule(type, subjectLevel, objectLevel);
	}
	
	/* executeRead String function will read and return the current value of the object by telling ObjectManager */
	public String executeRead(String subjectName, String objectName, String type, SecureSystem system) {

		if (checkRequest(type, subject.get(subjectName)[2], object.get(objectName)[2], system)) {
			return objManager.objectUpdate(type, subjectName, objectName, "0");
		}
		else {
			subject.get(subjectName)[1] = "0";
			return "0"; //FIX?
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
	public void handlerString (String type, String[] tokens) {
		System.out.print("\n");
		if (type.equalsIgnoreCase("WRITE")) {
			System.out.println(tokens[1].toLowerCase() + " writes value " + tokens[3] + " to " + tokens[2].toLowerCase());
		}
		else if (type.equalsIgnoreCase("READ")) {
			System.out.println(tokens[1].toLowerCase() + " reads " + tokens[2].toLowerCase());
		}
		else if (type.equalsIgnoreCase("BAD INSTRUCTION")) {
			System.out.println(type);
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
		public void newObject(String objectName, String objectValue, String securityLevel, ArrayList<String> list) {
 			/* store object info and add object name to list of subjects and objects */
			objectInfo = new String[3];
			objectInfo[0] = objectName;
			objectInfo[1] = objectValue;
			objectInfo[2] = securityLevel;
			list.add(objectName.toLowerCase());
	
			/* Store object info and label into hashmap */
			RefMonitor.this.object.put(objectName.toLowerCase(), objectInfo);
		}
	}
}
