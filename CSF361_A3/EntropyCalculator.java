import java.util.*;
import java.io.*;

public class EntropyCalculator {

	/* ComputeEntropy double function will assist in computing the entropy and return it */
	public double computeEntropy(Hashtable<String, Double> list, String[] alphabet, int size) {
		/* Variable declaration */
		int counter = 0;
		double result = 0, value = 0;
		
		/* loop through each element and compute the sum of all probabilities to get the entorpy and return it */
		while (counter < size) {
			if (list.get(alphabet[counter]) != null) {
				value = list.get(alphabet[counter]);
				result += value * (Math.log(value)/Math.log(2.0));
			}
			else {
				break;
			}
			counter++;
		}
		return result * -1.0;
	}

	/* compareEntropy void function will compare the entropies and output to a file */
	public void compareEntropy(double entropy1, double entropy2, double entropy3) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("summary.txt", true)));
		/* Print the comparisons to the file */
		out.println("ENTROPY COMPARISON");
		out.println("Entropy: " + entropy1);
		out.println("Entropy (one symbol): " + entropy2);
		out.println("Entropy (pair symbol): " + entropy3);
		out.println("");
		out.close();
	}	
}