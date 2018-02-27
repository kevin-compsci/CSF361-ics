UTEID: kdn433;
FIRSTNAME: Kevin;
LASTNAME: Nguyen;
CSACCOUNT: kxnguyen;
EMAIL: kxnguyen60@gmail.com;

[Program 4]
[Description]
The program has a total of 6 files that acts as small components to complete the AES algorithm. AES.java is the main file that starts the program up and parses the arguments to ensure that they are correctly formatted. FileHandler.java then reads into the arguments (and to the files) and sets up the encryption or decryption process. FileHandler.java not only reads into the files, but also fills in the initial arrays to be passed into the encode or decode processes respectively. As a result, only the actual encryption and decryption process happen later. We want to encapsulate as much of the trivial code as possible to minimize debugging and runtime errors. EncryptionProcess.java will run the encryption including expanding the key, subBytes, shiftRows, MixColumns, and AddRoundKeys for a number of rounds for encryption. The data is then written to a string to be outputed to a text file with ".enc" extension. Likewise, the DecryptionProcess.java is similar. However, the operations are in reverse order of the Encryption Process. The decryption should yield the original plaintext when finished with a ".dec" extension at the end of the outputted file name. Both of these .java files can be complex and hard to debug; as a result, runtime errors can often occur here. Most of the bugs were null exceptions or indexing into a null element. The data structures were mostly arrays and 2D arrays of integers since java can't support unsigned types. StaticInfo.java is merely a reference file to hold most of the arrays to implement AES such as s-box, inverse s-box, and more. The data in StaticInfo.java should never be modified at all. AES will depend on these files so the expected output remains true. Timing is also kept at the start of encryption or decyrption until the end to see how long the algorithm takes to finish its task for AES.

MixColumns.java was given by Dr. Young for CS361f assignment 4; this file and algorithm was not created by me. The file was modified to use integers instead of bytes and returned the state when it was finished modifiying. The purpose of MixColumns is to mix the state matrix columns with the key to get an encyrption.

SOURCE: https://www.cs.utexas.edu/~byoung/cs361/mixColumns-cheat-sheet

To run the program, make sure you are in the correct working directory with the files. Type into the command console, "javac *.java" to compile all java files. Then, type in "java AES OPTION KEYFILE INPUTFILE" where OPTION is either "d" (decryption) or "e" (encryption). KEYFILE is the name of the keyfile that will be inputed as the key and INPUTFILE is the name of the inputfile (plaintext) to use.


[Finish]
I managed to finish most of the program, but some of the implementations are still not fully integrated. The algorithm for AES was confusing and needed prior knowledge and research to be done before code was written. As a result, the output may or may not be correct. The big issue was handling the encryption and decryption processes since most of the actions are determined there. However, I think the issue is in the key expansion because the algorithm was referenced in the AES book by Rijndael on pg.46. As a result, it wasn't understood very well and it may have produced different results from my expectations. This program does not support padding of zeros because it wasn't implemented yet. However, this implementation would be to check the length with the expected value and pad as necessary. ShiftRows may be shifting in the wrong direction for decryption as well.



[Test Case 1]
[Command line]
Encryption: java AES e CipherKey Plaintext
Decryption: java AES d CipherKey Plaintext.enc

[Timing Information]
Encryption Time: 0.002879842 seconds
Decryption Time: 0.003495923 seconds

[Input Filenames]
Keyfile: CipherKey
Inputfile: Plaintext

[Output Filenames]
Encryption: Plaintext.enc
Decryption: Plaintext.enc.dec



[Test Case 2]
[Command line]
Encryption: java AES e CipherKey2 Plaintext2
Decryption: java AES d CipherKey2 Plaintext2.enc

[Timing Information]
Encryption Time: 0.00444485 seconds
Decryption Time: 0.005231584 seconds

[Input Filenames]
Keyfile: CipherKey2
Inputfile: Plaintext2

[Output Filenames]
Encryption: Plaintext2.enc
Decryption: Plaintext2.enc.dec



[Test Case 3]
[Command line]
Encryption: java AES e CipherKey3 Plaintext3
Decryption: java AES d CipherKey3 Plaintext3.enc

[Timing Information]
Encryption Time: 0.004433672 seconds
Decryption Time: 0.004429616 seconds

[Input Filenames]
Keyfile: CipherKey3
Inputfile: Plaintext3

[Output Filenames]
Encryption: Plaintext3.enc
Decryption: Plaintext3.enc.dec


[Test Case 4]
[Command line]
Encryption: java AES e CipherKey4 Plaintext4
Decryption: java AES d CipherKey4 Plaintext4.enc

[Timing Information]
Encryption Time: 0.00440212 seconds
Decryption Time: 0.004350999 seconds

[Input Filenames]
Keyfile: CipherKey4
Inputfile: Plaintext4

[Output Filenames]
Encryption: Plaintext4.enc
Decryption: Plaintext4.enc.dec