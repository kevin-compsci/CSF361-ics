import java.util.*;
import java.io.*;

public class FileHandler {
	double startTime = 0, stopTime = 0;
	MangleString mString = new MangleString();
	int success = 0, failure = 0;
	ArrayList<String> unableToCrack = new ArrayList<String>();

	/* readFile void function will take the two input files set up the encryption or decryption process */
	public void readFile(Scanner passwordList, String inputFile) throws IOException {
		String passwordLine = "";
		System.out.println();

		/* loop through password list and crack each one */
		while (passwordList.hasNextLine()) {
			startTime = System.nanoTime();
			passwordLine = passwordList.nextLine();
			Scanner dictionaryList = new Scanner(new File(inputFile));
			crackerPassword(passwordLine, dictionaryList);
			dictionaryList.close();
		}
		finalResults();
	}

	/* crackerPassword void function will start the password cracking process with the given phrase of encryption */
	public void crackerPassword(String line, Scanner dictionaryList) {
		/* variable declarations */
		String[] tokens = line.split(":");
		String[] name = tokens[4].split("\\s+");
		int count = 0;
		String dictionaryWord = "", encryptedPassword = tokens[1];
		String salt = encryptedPassword.substring(0,2), answer = "";
		boolean result = false;

		/* first loop to check if name is a possible guess; next loop checks through dictionary; mangle any phrase */
		while (count < 2) {
			result = tryMangling(salt, name[count], encryptedPassword);
			if (result) {
				success++;
				answer = mString.password;
				printResults(result, answer, name);
				break;
			}
			count++;
		}
		if (!result) {
			while (dictionaryList.hasNextLine()) {
				dictionaryWord = dictionaryList.nextLine();
				result = tryMangling(salt, dictionaryWord, encryptedPassword);
				if (result) {
					success++;
					answer = mString.password;
					printResults(result, answer, name);
					break;
				}
			}
		}
		if (!result) {
			failure++;
			unableToCrack.add(name[0]);
			printResults(result, "UNKNOWN", name);
		}
	}

	/* tryMangling function will attempt to apply as many mangling as possible to crack the password */
	public boolean tryMangling(String salt, String phrase, String original) {
		/* variable declaration */
		boolean result = false;

		/* apply mangling until result is true then skip to the return statement */
		if (!result) {
			result = mString.tryBasic(salt, phrase, original);
		}
		
		if (!result) {
			result = mString.prependString(salt, phrase, original);
		}
		
		if (!result) {
			result = mString.appendString(salt, phrase, original);
		}	

		if (!result) {
			result = mString.reverseAppendString(salt, phrase, original);
		}	
	
		if (!result) {
			result = mString.deleteFirstChar(salt, phrase, original);
		}		
		
		if (!result) {
			result = mString.deleteLastChar(salt, phrase, original);
		}

		if (!result) {
			result = mString.reverseString(salt, phrase, original);
		}

		if (!result) {
			result = mString.reverseStringUpperFirst(salt, phrase, original);
		}

		if (!result) {
			result = mString.reverseStringLower(salt, phrase, original);
		}

		if (!result) {
			result = mString.reverseStringUpper(salt, phrase, original);
		}

		if (!result) {
			result = mString.appendDuplicate(salt, phrase, original);
		}

		if (!result) {
			result = mString.upperString(salt, phrase, original);
		}
	
		if (!result) {
			result = mString.capitalizeFirstChar(salt, phrase, original);
		}

		if (!result) {
			result = mString.capitalizeLastChar(salt, phrase, original);
		}

		if (!result) {
			result = mString.capitalizeAllLowerFirstChar(salt, phrase, original);
		}

		if (!result) {
			result = mString.capitalizeAllLowerFirstCharAppend(salt, phrase, original);
		}

		if (!result) {
			result = mString.capitalizeAllLowerLastChar(salt, phrase, original);
		}

		if (!result) {
			result = mString.lowerAllUpperFirstChar(salt, phrase, original);
		}

		if (!result) {
			result = mString.lowerAllUpperLastChar(salt, phrase, original);
		}

		if (!result) {
			result = mString.toggleCase(salt, phrase, original);
		}

		if (!result) {
			result = mString.toggleCaseAlternate(salt, phrase, original);
		}

		if (!result) {
			result = mString.lowerString(salt, phrase, original);
		}
		
		if (!result) {
			result = mString.reflectionString(salt, phrase, original);
		}

		if (!result) {
			result = mString.reflectionReverseString(salt, phrase, original);
		}

		if (!result) {
			result = mString.reflectionStringUpper(salt, phrase, original);
		}

		if (!result) {
			result = mString.reflectionStringLower(salt, phrase, original);
		}

		if (!result) {
			result = mString.reflectionStringHalfUpper(salt, phrase, original);
		}

		if (!result) {
			result = mString.reflectionStringHalfUpper2(salt, phrase, original);
		}

		if (!result) {
			result = mString.removeLastCharAppend(salt, phrase, original);
		}

		if (!result) {
			result = mString.lowerAllUpperFirstCharRemoveLastRev(salt, phrase, original);
		}
		return result;
	}

	/* printResults void function will print out the results whether or not the crack was successful */
	public void printResults(boolean results, String answer, String[] name) {
		stopTime = ((System.nanoTime() - startTime)/1000);
		if (results) {
			System.out.println(":SUCCESS: The password for " + name[0] + " " + name[1] + " is " + answer + " : [Time = " + stopTime + " ms]");
		}
		else {
			System.out.println(":FAILURE: Unable to crack password for " + name[0] + " " + name[1] + " : [Time = " + stopTime + " ms]");
		}
	}

	/* finalResults void function will print any remaining information and overall progress */
	public void finalResults() {
		int counter = 0;
		System.out.println();
		System.out.println("In total, I can crack " + success + "/20 password. However, I can not crack " + failure + "/20 password, the list is...");
		while (counter < unableToCrack.size()) {
			System.out.println(unableToCrack.get(counter));
			counter++;
		}
		System.out.println();
	}

}
