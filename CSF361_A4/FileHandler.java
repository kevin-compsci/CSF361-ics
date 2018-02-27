import java.util.*;
import java.io.*;

public class FileHandler {
	
	/* readFile void function will take the two input files set up the encryption or decryption process */
	public void readFile(Scanner inputFile, Scanner keyFile, String type, String fileName) throws IOException {
		/* variable declarations */
		int[][] plaintext = new int[4][4];
		int[][] key = new int[4][8];
		
		/* fill in the arrays */
		plaintext = fillText (inputFile, plaintext);
		key = fillKey (keyFile, key);
		
		/* Timing starts here */
		double startTime = System.nanoTime();

		/* depending on type, the logic will go either for encryption or decryption processes */
		if (type.equalsIgnoreCase("E")) {
			fileName += ".enc";
			EncryptionProcess encryptFile = new EncryptionProcess();
			encryptFile.startEncryption(plaintext, key, fileName);
		}
		else if (type.equalsIgnoreCase("D")) {
			fileName += ".dec";
			DecryptionProcess decryptFile = new DecryptionProcess();
			decryptFile.startDecryption(plaintext, key, fileName);
		}

		/* Timing ends here */
		double endTime = ((System.nanoTime() - startTime)/1000000000.0);
		System.out.println(endTime + " seconds");
	}

	/* fillText int[][] function will fill in the array from data from the text file */
	public int[][] fillText(Scanner inputFile, int[][] plaintext) {
		/* variable declarations */
		String line = "", byteWord = "0x00";
		int count = 0, row = 0, column = 0;

		/* loop to fill in the plaintext array and return final result */
		while (inputFile.hasNextLine()) {
			line = inputFile.nextLine();
			while (column < 4) {
				byteWord = "0x" + line.charAt(count) + "" + line.charAt(count + 1);
				plaintext[row][column] = Integer.decode(byteWord);
				count+=2;
				column++;
			}
			column = 0;
			count = 0;
			row++;
		}
		return plaintext;
	}

	/* fillKey int[][] function will fill in the key array from data from the key file */
	public int[][] fillKey(Scanner keyFile, int[][] key) {
		/* variable declarations */
		String line = keyFile.nextLine(), byteWord = "0x00";
		int count = 0, row = 0, column = 0;

		/* loop to fill in the key and return final result */
		while (column < 8) {
			while (row < 4) {
				byteWord = "0x" + line.charAt(count) + "" + line.charAt(count + 1);
				key[row][column] = Integer.decode(byteWord);  
				row++;
				count+=2;
			}
			row = 0;
			column++;
		}
		return key;
	}
}