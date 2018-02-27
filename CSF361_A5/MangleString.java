import java.util.*;
import java.io.*;

public class MangleString {
	ValidateGuess validate = new ValidateGuess();
	jcrypt jc = new jcrypt();
	String password = "";

	/* tryBasic boolean function will try the base phrase as a guess */
	public boolean tryBasic(String salt, String phrase, String original) {
		/* Declaration variables */
		String phraseCopy = phrase, result = jc.crypt(salt, phraseCopy);

		/* Compare and check */
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* prependString boolean will return true or false if the password has been guessed correctly */
	public boolean prependString(String salt, String phrase, String original) {
		/* declaration variables */
		int count = 33;
		String phraseCopy = phrase, result = "";

		/* loop through special ascii characters and append them to the front of the string */
		while (count > 32 && count < 123) {
			phraseCopy = (char) count + "" + phraseCopy;
			result = jc.crypt(salt, phraseCopy);
	
			if (validate.crackPassword(original, result)) {
				password = phraseCopy;
				return true;
			}
			else {
				phraseCopy = phrase;
			}
			count++;
		}		
		return false;
	}

	/* appendString boolean will return true or false if the password has been guessed correctly by appending */
	public boolean appendString(String salt, String phrase, String original) {
		/* declaration variables */
		int count = 33;
		String phraseCopy = phrase, result = "";

		/* loop through special ascii characters and append them to the end of the string */
		while (count > 32 && count < 123) {
			phraseCopy = phraseCopy + "" + (char) count;
			result = jc.crypt(salt, phraseCopy);

			if (validate.crackPassword(original, result)) {
				password = phraseCopy;
				return true;
			}
			else {
				phraseCopy = phrase;
			}
			count++;
		}		
		return false;
	}

	/* reverseAppendString boolean will return true or false if the password has been guessed correctly appending after reversing*/
	public boolean reverseAppendString(String salt, String phrase, String original) {
		/* declaration variables */
		int count = 33, counter = phrase.length()-1;
		String phraseCopy = phrase, tempReverse = "", result = "";

		/* loop to copy phrase characters to a new string in reverse order and then check it */
		while (counter > -1) {
			tempReverse += phrase.charAt(counter) + "";
			counter--;
		}
		phraseCopy = tempReverse;

		/* loop through special ascii characters and append them to the end of the string */
		while (count > 32 && count < 123) {
			phraseCopy = phraseCopy + "" + (char) count;
			result = jc.crypt(salt, phraseCopy);

			if (validate.crackPassword(original, result)) {
				password = phraseCopy;
				return true;
			}
			else {
				phraseCopy = tempReverse;
			}
			count++;
		}		
		return false;
	}

	/* deleteFirstChar boolean will delete the first character in the phrase and tests if it is the correct password */
	public boolean deleteFirstChar(String salt, String phrase, String original) {
		/* String declarations */
		String phraseCopy = phrase.substring(1, phrase.length()), result = "";
		
		/* Get new encryption and then compare with original; return if true; else return false */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* deleteLastChar function boolean will delete the first character in the phrase and tests if it is the correct password */
	public boolean deleteLastChar(String salt, String phrase, String original) {
		/* String declarations */
		String phraseCopy = phrase.substring(0, phrase.length()-1), result = "";
				
		/* Get new encryption and then compare with original; return if true; else return false */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* reverseString boolean function will reverse the string and check if it is the password */
	public boolean reverseString(String salt, String phrase, String original) {
		/* variable declarations */
		int count = phrase.length()-1;
		String phraseCopy = phrase, tempReverse = "", result = "";

		/* loop to copy phrase characters to a new string in reverse order and then check it */
		while (count > -1) {
			tempReverse += phrase.charAt(count) + "";
			count--;
		}
		phraseCopy = tempReverse;
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* reverseStringLower boolean function will reverse the string and lowercases it and check if it is the password */
	public boolean reverseStringLower(String salt, String phrase, String original) {
		/* variable declarations */
		int count = phrase.length()-1;
		String phraseCopy = phrase, tempReverse = "", result = "";

		/* loop to copy phrase characters to a new string in reverse order and then check it */
		while (count > -1) {
			tempReverse += phrase.charAt(count) + "";
			count--;
		}
		phraseCopy = tempReverse;
		phraseCopy = phraseCopy.toLowerCase();
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* reverseStringUpperFirst boolean function will reverse the string but the first char originally will be capitalized */
	public boolean reverseStringUpperFirst(String salt, String phrase, String original) {
		/* variable declarations */
		int count = phrase.length()-1;
		String phraseCopy = phrase, tempReverse = "", result = "";

		/* loop to copy phrase characters to a new string in reverse order and then check it */
		while (count > -1) {
			if (count == 0) {
				tempReverse += Character.toUpperCase(phrase.charAt(count));
			}
			else {
				tempReverse += phrase.charAt(count) + "";
			}
			count--;
		}
		phraseCopy = tempReverse;
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* reverseStringUpper boolean function will reverse the string and capitalize it and check if it is the password */
	public boolean reverseStringUpper(String salt, String phrase, String original) {
		/* variable declarations */
		int count = phrase.length()-1;
		String phraseCopy = phrase, tempReverse = "", result = "";

		/* loop to copy phrase characters to a new string in reverse order and then check it */
		while (count > -1) {
			tempReverse += phrase.charAt(count) + "";
			count--;
		}
		phraseCopy = tempReverse;
		phraseCopy = phraseCopy.toUpperCase();
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* appendDuplicate boolean function will append a duplicate of the string and checks to see if it is correct */
	public boolean appendDuplicate(String salt, String phrase, String original) {
		/* variable declarations */
		String phraseCopy = phrase + "" + phrase, result = "";
				
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* upperString boolean function will capitalize all characters in the string and checks to see if it is correct */
	public boolean upperString(String salt, String phrase, String original) {
		/* variable declarations */
		String phraseCopy = phrase.toUpperCase(), result = "";
				
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* lowerString boolean function will lowercase all characters in the string and checks to see if it is correct */
	public boolean lowerString(String salt, String phrase, String original) {
		/* variable declarations */
		String phraseCopy = phrase.toLowerCase(), result = "";

		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* capitalizeFirstChar boolean function will capitalize the first character in the string and checks to see if it is correct */
	public boolean capitalizeFirstChar(String salt, String phrase, String original) {
		/* variable declarations */
		String phraseCopy = Character.toUpperCase(phrase.charAt(0)) + "" + phrase.substring(1, phrase.length()), result = "";

		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* capitalizeLastChar boolean function will capitalize the last character in the string and checks to see if it is correct */
	public boolean capitalizeLastChar(String salt, String phrase, String original) {
		/* variable declarations */
		String phraseCopy = phrase.substring(0, phrase.length()-1) + "" + Character.toUpperCase(phrase.charAt(phrase.length()-1)), result = "";
				
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* capitalizeAllLowerFirstChar boolean function will capitalize all characters and lower the first char in the string and checks to see if it is correct */
	public boolean capitalizeAllLowerFirstChar(String salt, String phrase, String original) {
		/* variable declarations */
		String phraseCopy = Character.toLowerCase(phrase.charAt(0)) + "" + phrase.substring(1, phrase.length()).toUpperCase(), result = "";
				
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* capitalizeAllLowerFirstChar boolean function will capitalize all characters and lower the first char in the string and checks to see if it is correct */
	public boolean capitalizeAllLowerFirstCharAppend(String salt, String phrase, String original) {
		/* variable declarations */
		String phraseCopy = Character.toLowerCase(phrase.charAt(0)) + "" + phrase.substring(1, phrase.length()).toUpperCase(), result = "";
		String temp = phraseCopy;
		int count = 33;

		/* loop through special ascii characters and append them to the end of the string */
		while (count > 32 && count < 123) {
			phraseCopy = phraseCopy + "" + (char) count;
			result = jc.crypt(salt, phraseCopy);

			if (validate.crackPassword(original, result)) {
				password = phraseCopy;
				return true;
			}
			else {
				phraseCopy = temp;
			}
			count++;
		}		

		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* capitalizeAllLowerLastChar boolean function will capitalize all characters and lower the last char in the string and checks to see if it is correct */
	public boolean capitalizeAllLowerLastChar(String salt, String phrase, String original) {
		/* variable declarations */
		String phraseCopy = phrase.substring(0, phrase.length()-1).toUpperCase() + "" + Character.toLowerCase(phrase.charAt(phrase.length()-1)), result = "";
				
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* lowerAllUpperFirstChar boolean function will lowercase all characters and upper the first char in the string and checks to see if it is correct */
	public boolean lowerAllUpperFirstChar(String salt, String phrase, String original) {
		/* variable declarations */
		String phraseCopy = Character.toUpperCase(phrase.charAt(0)) + "" + phrase.substring(1, phrase.length()).toLowerCase(), result = "";
				
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* lowerAllUpperLastChar boolean function will lowercase all characters and upper the last char in the string and checks to see if it is correct */
	public boolean lowerAllUpperLastChar(String salt, String phrase, String original) {
		/* variable declarations */
		String phraseCopy = phrase.substring(0, phrase.length()-1).toLowerCase() + "" + Character.toUpperCase(phrase.charAt(phrase.length()-1)), result = "";
				
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* toggleCase boolean function will capitalize and lowercase characters in the string in a pattern */
	public boolean toggleCase(String salt, String phrase, String original) {
		/* variable declarations */
		String phraseCopyUpper = phrase.toUpperCase(), phraseCopyLower = phrase.toLowerCase(), phraseCopy = "", result = "";
		int count = 0, limit = phrase.length();

		/* loop to apply the correct string pattern to new string */
		while (count < limit) {
			if ((count % 2) == 1) {
				phraseCopy += "" + phraseCopyLower.charAt(count);
			}
			else {
				phraseCopy += "" + phraseCopyUpper.charAt(count);
			}
			count++;
		}
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* toggleCaseAlternate boolean function will capitalize and lowercase characters in the string in a pattern but alternate */
	public boolean toggleCaseAlternate(String salt, String phrase, String original) {
		/* variable declarations */
		String phraseCopyUpper = phrase.toUpperCase(), phraseCopyLower = phrase.toLowerCase(), phraseCopy = "", result = "";
		int count = 0, limit = phrase.length();

		/* loop to apply the correct string pattern to new string */
		while (count < limit) {
			if ((count % 2) == 1) {
				phraseCopy += "" + phraseCopyUpper.charAt(count);
			}
			else {
				phraseCopy += "" + phraseCopyLower.charAt(count);
			}
			count++;
		}
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* reflectionString boolean function will take a string and duplicate a reflection and then append it */
	public boolean reflectionString(String salt, String phrase, String original) {
		/* variable declarations */
		int count = phrase.length()-1;
		String phraseCopy = phrase, tempReverse = "", result = "";

		/* loop to copy phrase characters to a new string in reverse order and then check it */
		while (count > -1) {
			tempReverse += phrase.charAt(count) + "";
			count--;
		}
		phraseCopy = phraseCopy + tempReverse;
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

		/* reflectionReverseString boolean function will take a string and duplicate a reflection and then prepend it in opposite order */
	public boolean reflectionReverseString(String salt, String phrase, String original) {
		/* variable declarations */
		int count = phrase.length()-1;
		String phraseCopy = phrase, tempReverse = "", result = "";

		/* loop to copy phrase characters to a new string in reverse order and then check it */
		while (count > -1) {
			tempReverse += phrase.charAt(count) + "";
			count--;
		}
		phraseCopy = tempReverse + phraseCopy;
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}


	/* reflectionStringUpper boolean function will take a string and duplicate a reflection and then append it, all capitalized */
	public boolean reflectionStringUpper(String salt, String phrase, String original) {
		/* variable declarations */
		int count = phrase.length()-1;
		String phraseCopy = phrase, tempReverse = "", result = "";

		/* loop to copy phrase characters to a new string in reverse order and then check it */
		while (count > -1) {
			tempReverse += phrase.charAt(count) + "";
			count--;
		}
		phraseCopy = phraseCopy + tempReverse;
		phraseCopy = phraseCopy.toUpperCase();
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* reflectionStringLower boolean function will take a string and duplicate a reflection and then append it, all lowercase */
	public boolean reflectionStringLower(String salt, String phrase, String original) {
		/* variable declarations */
		int count = phrase.length()-1;
		String phraseCopy = phrase, tempReverse = "", result = "";

		/* loop to copy phrase characters to a new string in reverse order and then check it */
		while (count > -1) {
			tempReverse += phrase.charAt(count) + "";
			count--;
		}
		phraseCopy = phraseCopy + tempReverse;
		phraseCopy = phraseCopy.toLowerCase();
		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* reflectionStringHalfUpper boolean function will take a string and duplicate a reflection and then append it, first is all uppercase */
	public boolean reflectionStringHalfUpper(String salt, String phrase, String original) {
		/* variable declarations */
		int count = phrase.length()-1;
		String phraseCopy = phrase, tempReverse = "", result = "";

		/* loop to copy phrase characters to a new string in reverse order and then check it */
		while (count > -1) {
			tempReverse += phrase.charAt(count) + "";
			count--;
		}
		phraseCopy = phraseCopy.toUpperCase() + tempReverse.toLowerCase();

		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* reflectionStringHalfUpper2 boolean function will take a string and duplicate a reflection and then append it, second half is all uppercase */
	public boolean reflectionStringHalfUpper2(String salt, String phrase, String original) {
		/* variable declarations */
		int count = phrase.length()-1;
		String phraseCopy = phrase, tempReverse = "", result = "";

		/* loop to copy phrase characters to a new string in reverse order and then check it */
		while (count > -1) {
			tempReverse += phrase.charAt(count) + "";
			count--;
		}
		phraseCopy = phraseCopy.toLowerCase() + tempReverse.toUpperCase();

		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

	/* removeLastChar boolean function will get the substring minus last char and append */
	public boolean removeLastCharAppend(String salt, String phrase, String original) {
		/* variable declarations */
		int count = 33;
		String phraseCopy = phrase.substring(0, phrase.length()-1);
		String temp = phraseCopy, result = "";

		/* loop through special ascii characters and append them to the end of the string */
		while (count > 32 && count < 123) {
			phraseCopy = phraseCopy + "" + (char) count;
			result = jc.crypt(salt, phraseCopy);

			if (validate.crackPassword(original, result)) {
				password = phraseCopy;
				return true;
			}
			else {
				phraseCopy = temp;
			}
			count++;
		}		
		return false;
	}

	/* lowerAllUpperFirstCharRemoveLastRev boolean function will lowercase all characters and upper the first char in the string and removes last char */
	public boolean lowerAllUpperFirstCharRemoveLastRev(String salt, String phrase, String original) {
		/* variable declarations */
		int count = phrase.length()-2;
		String phraseCopy = Character.toUpperCase(phrase.charAt(0)) + "" + phrase.substring(1, phrase.length()).toLowerCase(), result = "";
		String tempReverse = "";

		/* loop to copy phrase characters to a new string in reverse order and then check it */
		while (count > -1) {
			tempReverse += phraseCopy.charAt(count) + "";
			count--;
		}
		phraseCopy = tempReverse;

		/* check to see if comparison is true or not */
		result = jc.crypt(salt, phraseCopy);
		if (validate.crackPassword(original, result)) {
			password = phraseCopy;
			return true;
		}
		return false;
	}

}