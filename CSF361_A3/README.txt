UTEID: kdn433;
FIRSTNAME: Kevin;
LASTNAME: Nguyen;
CSACCOUNT: kxnguyen;
EMAIL: kxnguyen60@gmail.com;

[Program 3]
[Description]
There are 6 java files Encoder.java, FileHandler.java, EntropyCalculate.java, RandomFile.java, GenerateCharacters.java, and HuffmanCode.java (not from me). Encoder starts the program to be check the initial set up and run the file. FileHandler.java does all the file handling; as a result, it acts as the brains of the program. There are functions dedicated to obtain probabilities, frequencies, encode strings, and decode strings. However, FileHandler also needs to rely on other classes as well. RandomFile.java will generate the random text based on the given frequencies from the initial file. New text is generated and we are to encode that and decode that text later on. RandomFile handles the random values, but GenerateCharacters.java handles the actual character to print. We must be careful because we want to keep our data structures in tact so GenerateCharacters.java has a function to make a deep copy if needed to operate on. There are also functions like parseRandom and getText. ParseRandom decides which character to print based on the random value and getText processes the character obtained from parseRandom. Once we have the proper text we can encode it with HuffmanCode.java. The decoding is also handled in the fileHandler and it uses the key mapping to decrypt the encoded message. Overall, this program puts emphasis on components, where each component algorithm can be put together to make a large program complete. However, not all bugs have been resolved and there may be some I haven't caught yet. This program also utilizes the entire 26 letter english alphabet.

The HuffmanCode.java file was not created by me; I take no credit for that source code. The HuffmanCode.java was provided by Rosetta.org and I have modified the source code in my implementation for CS361f assignment 3. The HuffmanCode.java merely sets up a binary tree and based on the character in the string we may see how many bits per symbol there is. I had to modify the HuffmanCode to take in my data structures so I could store the frequencies, characters, and bits to a mapping. That way, I have an easier time managing encoding and decoding processes later. I also enabled writing the results to a file so the results can be viewed anytime. The main algorithm design remained the same and should still work fine.

SOURCE: https://rosettacode.org/wiki/Huffman_coding#Java

To run the program, you must be in the correct working directory of where the files are. Go to the command line and type in "javac *.java" to compile the java files. Then type in "java Encoder FILENAME.txt k" where FILENAME is the name of the file and k is any integer from 50 - 500. Remember to add the .txt extension as well. As the program finishes execution for each test file, there should be an output file in the working directory to see the individual results or just view the summary.txt.

[Finish]
I finished most of the project details; however, the code is mostly unoptimized and the pair-symbol may be incorrect. The output and input should be clear enough to understand. I had too many assignments due this week, but I've gotten most of the implementations completed to the best of my ability. Entropies were also quite small, but I double checked the math and it looks ok, but I could be wrong. There is a bug that I don't time to fix and some of the entropies couldn't be calculated properly which gives NaN. An overly large k may also give errors, but I didn't have time to check over that.


[Test Case 1]
Filename: frequenciesFile.txt

[Command line]
java Encoder frequenciesFile 50

[Input]
4
3
1
2
7
9
10

[Output]
snickers$ java Encoder frequenciesFile.txt 50

Symbol String: E A E C D E B E E C C E C C C E C C E E B B B A E D C C E E E C E B D D E C E B E A D E C C A C D B
SYMBOL  WEIGHT  HUFFMAN CODE
        49      0
E       18      10
C       15      110
B       7       1110
A       4       11110
D       6       11111
Entropy (one-symbol): 2.1073908213983334

Symbol String: BC CE ED BB CB CC EA BB ED EE EB BE CD ED CD EE AC ED CA CA BE EE BC AE EC EA EC BC BC CE CE EE AE CE EA EC BD BE DD EC EC CC BD EB ED AC BA CA CE EC
SYMBOL  WEIGHT  HUFFMAN CODE
C       17      00
E       24      01
B       12      100
D       6       1010
A       7       1011
        33      11
Entropy (two-symbol): 2.2957546147836854





[Test Case 2]
Filename: frequenciesFile2.txt

[Command line]
java Encoder frequenciesFile2.txt 50

[Input]
10
9
8
7
6
5
4
3
2
1
0

[Output]
snickers$ java Encoder frequenciesFile2.txt 50

Symbol String: E G F I E B A B J B A C B A K E H K H H C K F I I F A A G K I C F B G G A E I H F C C I K I G J C J
SYMBOL  WEIGHT  HUFFMAN CODE
        49      0
K       5       1000
F       5       1001
G       5       1010
C       6       1011
A       6       1100
I       7       1101
J       3       11100
E       4       11101
H       4       11110
B       5       11111
Entropy (one-symbol): 1.0663272948615927

Symbol String: BK BF EG FB FC FC GI EC CE JK CH CB IB JJ HC GB HA AF GC BC JK BF FG GK CC JC BH HK BC HJ IK BH EG II BA EC CB CA JJ JG IK AI CG EG GG FC GC CG CB FE
SYMBOL  WEIGHT  HUFFMAN CODE
G       7       000
E       4       0010
A       2       00110
I       3       00111
B       11      010
J       6       0110
K       6       0111
C       13      100
H       7       1010
F       7       1011
        33      11
Entropy (two-symbol): 1.1716159358659675





[Test Case 3]
Filename: frequenciesFile3.txt

[Command line]
java Encoder frequenciesFile3.txt 50

[Input]
10
10
10
10
1
1
1
1
1
300
548
111
38
84
3
2
1

[Output]
snickers$ java Encoder frequenciesFile3.txt 50

Symbol String: O P M E A M M E E A A M P A E E A M M M A M M A A M P A P E M M P A A M A M M P A E A M A M E A A O
SYMBOL  WEIGHT  HUFFMAN CODE
        49      0
A       17      10
E       8       1100
O       2       11010
P       6       11011
M       17      111
Entropy (one-symbol): 0.529173738498291

Symbol String: AE MM EA PP AA EA MM MP OM MA EA AA MP AM MM AE OE MA MM MA MA MM MM EM AA EO EE OA OM AA MM AE MA MM AO AM ME MM AA EA PM MA MA MO MA ME EA AM PE AM
SYMBOL  WEIGHT  HUFFMAN CODE
P       4       0000
O       5       0001
E       11      001
A       21      01
M       25      10
        33      11
Entropy (two-symbol): 0.525646282138305







[Test Case 4]
Filename: frequenciesFile4.txt

[Command line]
java Encoder frequenciesFile4.txt 50

[Input]
2
4
6
8
10
12
14
16
18
20

[Output]
snickers$ java Encoder freqTest4.txt 50

Symbol String: D A A C A E E A C E C A B A A C E C E A E D A D C D D A B E D A A D A D E A D A A D A D C A C C D C
SYMBOL  WEIGHT  HUFFMAN CODE
        49      0
C       10      100
B       2       1010
E       8       1011
D       12      110
A       18      111
Entropy (one-symbol): 2.097906570104942

Symbol String: CA AD EC CC CA BA BB CA AD AB AB BA CB CB BE BC AC CC EB BC DA EE AD BD CB AA ED AA AC AC CB AD EC AB DA DB AD AB AA EE CE AA EB BA AE AE CA AA DA CD
SYMBOL  WEIGHT  HUFFMAN CODE
C       18      00
A       19      01
D       7       1000
E       7       1001
B       15      101
        33      11
Entropy (two-symbol): 2.376378158016157