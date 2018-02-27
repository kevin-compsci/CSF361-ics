import java.util.*;
import java.io.*;

public class FileHandler {
	/* Global Declarations */
	String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	Hashtable<String, Double> statList = new Hashtable<String, Double>();
	Hashtable<String, String> keyCode = new Hashtable<String, String>();
	Hashtable<String, String> encryptCode = new Hashtable<String, String>();
	Hashtable<String, Double> symbolBits = new Hashtable<String, Double>();
	Hashtable<String, Double> symbolBits2 = new Hashtable<String, Double>();
	ArrayList<Double> freqList = new ArrayList<Double>();
	EntropyCalculator entropyCompute = new EntropyCalculator();
	GenerateCharacters genChar = new GenerateCharacters();
	RandomFile randFile = new RandomFile();
	HuffmanCode huffCode = new HuffmanCode();  //NOT CREATED BY ME; 3rd party source material see README
	double totalFreq = 0;
	String currentAlphabet = "";

	/* readFile void function will handle all file operations */
	public void readFile(Scanner file, int k) throws IOException {
		/* Variable declarations */
		String line = "";
		int size = 0;
		ArrayList<Double> tempList = new ArrayList<Double>();
		System.out.println("");

		/* Get total frequency and probability then compute Entropy */
		totalFreq = getTotalFreq(file, tempList);
		getProbability(totalFreq, tempList);
		mapFreq();
		size = freqList.size();
		if (size > 26) {
			size = 26;
		}
		double totalVal = (double) k;
		Double entropy1 = entropyCompute.computeEntropy(statList, alphabet, size);
		huffCode.huffmanStart(currentAlphabet, keyCode, encryptCode, symbolBits, totalVal, false, false, false);
		
		/* Process for the one-symbol and two-symbol encryption and decryption */
		randFile.writeRandomFile(freqList, alphabet, k, totalFreq, false);
		line = encodeString(false, totalVal);
		line = decodeString(false, line);
		Double entropy2 = entropyCompute.computeEntropy(symbolBits, alphabet, k);
		System.out.println("Entropy (one-symbol): " + entropy2);
		System.out.println("");
		randFile.writeRandomFile(freqList, alphabet, k, totalFreq, true);
		line = encodeString(true, totalVal);
		line = decodeString(true, line);
		Double entropy3 = entropyCompute.computeEntropy(symbolBits, alphabet, k);
		System.out.println("Entropy (two-symbol): " + entropy3);
		System.out.println("");
		entropyCompute.compareEntropy(entropy1, entropy2, entropy3);
	}

	/* getTotalFreq int function will return the total amount of frequencies */
	public double getTotalFreq(Scanner file, ArrayList<Double> tempList) {
		/* Variable declarations */
		double result = 0.0;
		String line = "";

		/* loop to get each line and add up all integers */
		while (file.hasNextLine()) {
			line = file.nextLine();
			tempList.add(Double.parseDouble(line));
			result += Double.parseDouble(line);
		}
		return result;
	}

	/* getProbability void function will put the frequencies in the list */
	public void getProbability(Double totalFreq, ArrayList<Double> tempList) {
		/* Variable declaration */
		String line = "";
		int counter = 0;

		/* loop to get each value for probability then add it to list */
		while (counter < tempList.size()) {
			freqList.add(tempList.get(counter)/totalFreq);
			currentAlphabet += alphabet[counter] + " ";
			counter++;
		}	
		currentAlphabet = currentAlphabet.substring(0, (counter*2)-1);
	}

	/* mapFreq void function will map the freqlist values with the letters of the alphabet, assuming ordering */
	public void mapFreq() {
		/* Variable declarations */
		int count = 0;
		
		/* loop to get values from lists and put them into a mapping for reference */
		while (count < freqList.size()) {
			statList.put(alphabet[count], freqList.get(count));
			count++;
		}
	}


	/* encodeString String function will encrypt the string specified with Huffmans code */
	public String encodeString (boolean pairSymbols, double totalVal) throws IOException {
		/* Variable declarations */
		int counter = 0;
		Scanner file = new Scanner(new File("testText.txt"));
		String line = file.nextLine(), result = "";
		PrintWriter out;
		if (!pairSymbols) {
			out = new PrintWriter(new BufferedWriter(new FileWriter("testText.enc1", false)));
		}
		else {
			out = new PrintWriter(new BufferedWriter(new FileWriter("testText.enc2", false)));
		}
		keyCode.clear();
		encryptCode.clear();
		symbolBits.clear();
		huffCode.huffmanStart(line, keyCode, encryptCode, symbolBits, totalVal, pairSymbols, true, true);

		/* get bit code encryption and print it to file */
		while (counter < line.length()) {
			if (line.charAt(counter) != 32) {
				result += encryptCode.get(Character.toString(line.charAt(counter)));
			}
			counter++;
		}
		out.println(result);
		out.close();
		return result;
	}

	/* decodeString string function will decode the encrypted string and saves it for output */
	public String decodeString (boolean pairSymbols, String encryptedString) throws IOException {
		/* Variable Declaration */
		String results = "";
		int lowLength = 0, maxLength =1;
		PrintWriter out;
		if (!pairSymbols) {
			out = new PrintWriter(new BufferedWriter(new FileWriter("testText.dec1", false)));
		}
		else {
			out = new PrintWriter(new BufferedWriter(new FileWriter("testText.dec2", false)));
		}

		/* Print in correct format for one symbol or pair symbols */
		if (!pairSymbols) {
			/* loop to get correct letter from decoding; complete decoded string is returned */
			while (maxLength <= encryptedString.length()) {
				String temp = encryptedString;
				if (keyCode.get(temp.substring(lowLength, maxLength)) != null) {
					results += keyCode.get(temp.substring(lowLength, maxLength)) + " ";
					lowLength = maxLength;
				}
				maxLength++;
			}
		}
		else {
			int count = 0;
			/* loop to get correct letter from decoding; complete decoded string is returned */
			while (maxLength <= encryptedString.length()) {
				String temp = encryptedString;
				if (keyCode.get(temp.substring(lowLength, maxLength)) != null) {
					results += keyCode.get(temp.substring(lowLength, maxLength));
					lowLength = maxLength;
					count++;
				}

				if (count >= 2) {
					results += " ";
					count = 0;
				}
				maxLength++;
			}
		}
		currentAlphabet = results;
		out.println(results);
		out.close();
		return results;
	}
}