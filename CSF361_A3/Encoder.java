import java.util.*;
import java.io.*;

public class Encoder {
 	
	/* main method of execution for encoding */
 	public static void main (String args[])  throws IOException {
		/* Checks if args match expected length */
		if (args.length == 2) {
			Scanner file = new Scanner(new File(args[0]));
			FileHandler handler = new FileHandler();
			handler.readFile(file, Integer.parseInt(args[1]));
		}
		else {
			System.out.println("Error: Not enough arguments, too many arguments, or incorrect formatting!");
		}
	}
}