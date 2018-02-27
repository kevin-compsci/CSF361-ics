import java.util.*;
import java.io.*;

public class PasswordCrack {

	/* main function for execution of the program */
 	public static void main (String args[])  throws IOException {
		if (args.length == 2) {
			//Scanner dictionaryList = new Scanner(new File(args[0]));
			Scanner passwordList = new Scanner(new File(args[1]));
			FileHandler file = new FileHandler();
			file.readFile(passwordList, args[0]);
			passwordList.close();
		}
		else {
			System.out.println("Error: Not enough arguments, too few, or incorrect formatting!");
		}
	}
}