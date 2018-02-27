import java.util.*;
import java.io.*;

public class EncryptionProcess {
	StaticInfo data = new StaticInfo();
	MixColumns mixCol = new MixColumns();

	/* startEncryption void function will initiate runs to encrypt the file */
	public void startEncryption(int[][] plaintext, int[][] key, String fileName) throws IOException {
		int rounds = 0;
		int[][] eKey = new int[4][(8*15)];
		eKey = keyExpand(key, eKey);

		/* number of rounds for 256 is 14 rounds */
		while (rounds < 13) {
			plaintext = executeSubBytes(plaintext);
			plaintext = executeShiftRows(plaintext);
			plaintext = executeMixColumns(plaintext);
			plaintext = executeAddRoundKeys(plaintext, eKey, rounds);
			rounds++;
		}
		/* last rounds; 14th */
		plaintext = executeSubBytes(plaintext);
		plaintext = executeShiftRows(plaintext);
		plaintext = executeAddRoundKeys(plaintext, eKey, rounds);
		printState(plaintext, fileName);
	}

	/* executeSubBytes String[][] function will substitute each byte in the array with the key */ 
	public int[][] executeSubBytes(int[][] plaintext) {
		/* variable declarations */
		int rows = 0, columns = 0;	

		/* loops to iterate over 2d array of hex and replacing each block with some key */
		while (rows < plaintext.length) {
			while (columns < plaintext[rows].length) {
				plaintext[rows][columns] = subBytes(plaintext[rows][columns]);
				columns++;
			}
			columns = 0;
			rows++;
		}
		return plaintext;
	}

	/* subBytes int function (helper) will return the substituted item from sBox */
	public int subBytes(int item) {
		/* get lower and higher byte order nibbles to index into sBox for substitution */
		int lowerNibble = (item << 4), higherNibble = ((item >> 4) & 0x0F);
		lowerNibble = ((item >> 4) & 0x0F);
		return data.sBox[higherNibble][lowerNibble];		
	}


	/* executeShiftRows void function will shift rows accordingly  */
	public int[][] executeShiftRows(int[][] plaintext) {
		/* variable declarations */
		int rows = 0, tempCount = 0, count = 0, temp1 = 0, temp2 = 0, temp3 = 0;

		/* loop through each row and shift as needed; number of shifts depends on the current row */
		while (rows < 4) {
			if (rows == 1) {
				tempCount = 0; count = 0; temp1 = plaintext[rows][count]; temp2 = 0; temp3 = 0;
				count = rows;
				while (count < 4) {
					plaintext[rows][tempCount] = plaintext[rows][count];
					tempCount++;
					count++;
				}	
				plaintext[rows][tempCount] = temp1;
			}
			else if (rows == 2) {
				tempCount = 0; count = 0; temp1 = plaintext[rows][count]; temp2 = plaintext[rows][count + 1]; temp3 = 0;
				count = rows;
				while (count < 4) {
					plaintext[rows][tempCount] = plaintext[rows][count];
					tempCount++;
					count++;
				}
				plaintext[rows][tempCount] = temp1;
				plaintext[rows][tempCount + 1] = temp2;
			}
			else if (rows == 3) {
				tempCount = 0; count = 0; temp1 = plaintext[rows][count]; temp2 = plaintext[rows][count + 1]; temp3 = plaintext[rows][count + 2];
				count = rows;
				while (count < 4) {
					plaintext[rows][tempCount] = plaintext[rows][count];
					tempCount++;
					count++;
				}
				plaintext[rows][tempCount] = temp1;
				plaintext[rows][tempCount + 1] = temp2;
				plaintext[rows][tempCount + 2] = temp3;
			}
			rows++;
		}
		return plaintext;
	}

	/* executeMixColumns int[][] function will initiate the mix columns algorithm and modify the plaintext and return it */
	public int[][] executeMixColumns(int[][] plaintext) {
		/* variable declarations */
		int columns = 0;

		/* loop to iterate through each column and call the MixColumn class for the algorithm */
		while (columns < 4) {
			plaintext = mixCol.mixColumn2(columns, plaintext);
			columns++;
		}
		return plaintext;
	}

	/* executeAddRoundKeys int[][] function will apply the expanded keys to every state of the plaintext */
	public int[][] executeAddRoundKeys (int[][] plaintext, int[][] eKey, int round) {
		/* variable declarations */
		int row = 0, column = 0, columnKey = (8 + (4 * round));
		int limit = columnKey + 4;

		/* Loop through expanded keys and place replace the keys back into the plaintext */
		while (columnKey < limit) {
			while (row < 4) {
				plaintext[row][column] = (eKey[row][columnKey] ^ plaintext[row][column]);
				row++;
			}
			columnKey++;
			column++;
			row = 0;
		}
		return plaintext;
	}

	/* keyExpand int[][] function will expand the cipher key for 256 */
	public int[][] keyExpand(int[][] key, int[][] eKey) {
		int nk = 8, rowCount = 0, colCount = 0;

		/* fill in the first two blocks as original cipher key */
		while (colCount < nk) {
			while (rowCount < 4) {
				eKey[rowCount][colCount] = key[rowCount][colCount];
				rowCount++;
			}
			rowCount = 0;
			colCount++;
		};

		/* loop through and expand the key here. Algorithm obtained from Rijndael book on key expansion, pg.46 */
		colCount = nk; rowCount = 0;
		while (colCount < (8 * 14)) {
			if ((colCount % nk) == 0) {
				eKey[0][colCount] = eKey[0][colCount - nk] ^ subBytes(eKey[1][colCount - 1]) ^ data.rcon[colCount/nk];
				rowCount = 1;
				while (rowCount < 4) {
					eKey[rowCount][colCount] = eKey[rowCount][colCount - nk] ^ subBytes(eKey[((rowCount + 1) % 4)][colCount - 1]);
					rowCount++;
				}
			}
			else if ((colCount % nk) == 4) {
				rowCount = 0;
				while (rowCount < 4) {
					eKey[rowCount][colCount] = eKey[rowCount][colCount - nk] ^ subBytes(eKey[rowCount][colCount - 1]);
					rowCount++;
				}
			}
			else {
				rowCount = 0;
				while (rowCount < 4) {
					eKey[rowCount][colCount] = eKey[rowCount][colCount - nk] ^ eKey[rowCount][colCount - 1];
					rowCount++;
				}
			}
			rowCount = 0;
			colCount++;
		}
		return eKey;
	}

	/* printState void function will output the encrypted text */
	public void printState(int[][] plaintext, String fileName) throws IOException {
		/* variable declarations */
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, false)));
		String encryptedMessage = "";
		int rows = 0, columns = 0;

		while (rows < 4) {
			while (columns < 4) {
				if (plaintext[rows][columns] >= 1 && plaintext[rows][columns] <= 15) {
					encryptedMessage += "0" + Integer.toHexString(plaintext[rows][columns]) + "";
				}
				else {
					encryptedMessage += Integer.toHexString(plaintext[rows][columns]) + "";
				}
				columns++;
			}
			columns = 0;
			rows++;
			if (rows < 4) {
				encryptedMessage += "\r\n";
			}
		}

		if (encryptedMessage.length() < 32) {
			while (true) {
				encryptedMessage += "0";
				if (encryptedMessage.length() >= 32) {
					break;
				}
			}
		}
		out.println(encryptedMessage);
		out.close();
	}

}