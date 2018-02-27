import java.util.*;
import java.io.*;

public class AES {

	/* main function for execution of the program */
 	public static void main (String args[])  throws IOException {
		if (args.length == 3 && args[0].equalsIgnoreCase("D") || args[0].equalsIgnoreCase("E")) {
			String fileName = args[2];
			Scanner keyFile = new Scanner(new File(args[1]));
			Scanner inputFile = new Scanner(new File(args[2]));
			FileHandler file = new FileHandler();
			file.readFile(inputFile, keyFile, args[0], fileName);
		}
		else {
			System.out.println("Error: Not enough arguments, too few, or incorrect formatting!");
		}
	}
}