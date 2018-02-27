import java.util.*;
import java.io.*;

public class GenerateCharacters {

	/* getTextCharacters String function will get the string of characters needed for random text */
	public String getTextCharacters(ArrayList<Double> freqList, String[] alphabet, Random rand, int k, double totalFreq) {
		/* Variable declaration */
		double value = 0, counter = 0;
		String letter = "";
		int size = freqList.size();

		/* loop to get the proper text characters as a string */
		while (counter < k) {
			value = rand.nextInt(size+1);
			letter += parseRandom(freqList, alphabet, value, totalFreq);
			letter += " ";
			counter ++;
		}
		System.out.println("Symbol String: " + letter);
		return letter;
	}

	/* getTextCharacters String function will get the string of characters needed for random text */
	public String getTextCharactersPair(ArrayList<Double> freqList, String[] alphabet, Random rand, int k, double totalFreq) {
		/* Variable declaration */
		double value = 0, counter = 0;
		String letter = "";
		int size = freqList.size();

		/* loop to get the proper text characters as a string */
		while (counter < k) {
			value = rand.nextInt(size);
			letter += parseRandom(freqList, alphabet, value, totalFreq);
			value = rand.nextInt(size);
			letter += parseRandom(freqList, alphabet, value, totalFreq);
			letter += " ";
			counter ++;
		}
		System.out.println("Symbol String: " + letter);
		return letter;
	}

	/* parseRandom String function will parse the random value and determine which char to print */
	public String parseRandom(ArrayList<Double> freqList, String[] alphabet, double value, double totalFreq) {
		/* Variable declaration and need temp copy of arrays */
		int counter = 0, size = freqList.size();
		double tempValue = 0.0;
		String result = "";
		ArrayList<Double> freqListTemp = getCopyArrayList(freqList, totalFreq);
		Double tempArray[] = new Double[freqListTemp.size()];
   		tempArray = freqListTemp.toArray(tempArray);
   		Arrays.sort(tempArray, 0, freqList.size());

		/* iterate through arrays to get correct char to print and return it */
		while (counter < tempArray.length) {
			if (value <= tempArray[counter]) {
				tempValue = (tempArray[counter]);
				break;
			}
			counter++;
		}
		counter = 0;
		while (counter < size) {
			if (tempValue == freqListTemp.get(counter)) {
				tempValue = counter;
				break;
			}
			counter++;
		}
		result = alphabet[counter];
		return result;
	}

	/* getCopyArrayList arraylist function will create a hard copy of the specified array */
	public ArrayList<Double> getCopyArrayList(ArrayList<Double> freqList, double totalFreq) {
		/* Variable declarations */
		int counter = 0;
		ArrayList<Double> tempList = new ArrayList<Double>();

		/* Loop to copy each value in the frequency to a temp list and return it */
		while (counter < freqList.size()) {
			tempList.add(totalFreq * freqList.get(counter));
			counter++;
		}
		return tempList;
	}
}