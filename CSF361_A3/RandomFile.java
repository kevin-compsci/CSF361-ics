import java.util.*;
import java.io.*;

public class RandomFile {

	/* writeRandomFile void function will output to a file of k-size with the random contents */
	public void writeRandomFile(ArrayList<Double> freqList, String[] alphabet, int k, double totalFreq, boolean pairSymbols) throws IOException {
		/* Variable declarations */
		String letters = null;
		Random rand = new Random();
		PrintWriter outText = new PrintWriter(new BufferedWriter(new FileWriter("testText.txt", false)));
		
		/* Get characters as string then write it to file */
		GenerateCharacters genChar = new GenerateCharacters();
		if (!pairSymbols) {
			letters = genChar.getTextCharacters(freqList, alphabet, rand, k, totalFreq);
			letters = letters.substring(0, (k*2)-1);
		}
		else {
			letters = genChar.getTextCharactersPair(freqList, alphabet, rand, k, totalFreq);
			letters = letters.substring(0, (k*2)-1);
		}
		outText.println(letters);
		outText.close();
	}
}