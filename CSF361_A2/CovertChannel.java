import java.util.*;
import java.io.*;

public class CovertChannel {
 	public static void main (String args[])  throws IOException {
		SecureSystem system = new SecureSystem();
		int bad = 1;
		/* If number of arguments not 1 then there is no file or too many files specified */
		if (args.length == 1 || args.length == 2) {
			if (args[0].equalsIgnoreCase("v") && args.length == 2 && args[1].contains(".txt")) {
				bad = 0;
				system.securityStart(args, 1, system);  //Verbose mode on
			}
			else if (!args[0].equalsIgnoreCase("v") && args.length == 1) {
				if (args[0].contains(".txt")) {
					bad = 0;
					system.securityStart(args, 0, system);  //Verbose mode off
				}
			}
		}
		/* bad file */
		if (bad == 1) {
			System.out.println("ERROR: No file specified or too many files specified! Be sure to have .txt extension!");
		}
	}
}
