UTEID: kdn433;
FIRSTNAME: Kevin;
LASTNAME: Nguyen;
CSACCOUNT: kxnguyen;
EMAIL: kxnguyen60@gmail.com;

[Program 2]
[Description]
The project is a modification of my Program 1 algorithm. There are a total of 4 files. The main file is CovertChannel.java, the security file SecureSystem.java, the instruction analyzer ObjectInstruction.java, and the mapping file RefMonitor.java. These files are implemented together so that we can achieve a simulated covert channel structure. CovertChannel.java merely starts the program and checks whether or not verbose mode should be considered for security. The SecureSystem class has securityStart method that initializes all security basics for the program. It also has intializers for both subject and object if they need to be created at the beginning (or possible later). There is also the dominationRule method that determines the domination rule between subject and objects. SecureSystem also calls the ObjectInstruction class to parse each line in the file. The covert simulation happens in ObjectInstruction; as a result, readFile function gets each line and then the bytes from each line. The bytes are iterated over and when we have a byte to work with, we parse each of the bits. The bits will determine how our simulation will work; as a result, bit of 0 will cause object of high to be created. We can parse the bit by running the executeInstructions method that runs a series of instruction from small subject, lyle. When all of the instructions have been executed through the parseStringTokens method, then we go on to the next bit. We store every bit we get after the simulation and after 8 bits, we have a byte. As a result, during the executeRun method, we check if we have a byte or not and if yes, we can write to the file. Otherwise, our logic keeps moving. The ObjectInstruction class makes heavy calls to the RefMonitor class. RefMonitor maintains the mapping between subjects, objects, and their security levels. It also provides the execute functions for create, read, write, destroy, and run. Objects can be created with executeCreate, values can be read with executeRead, values can be written with executeWrite, objects can be destroyed with executeDestroy, and files can be written with executeRun. The RefMonitor also has a local class that maintains the updating to the object with the updateObject function and create new objects when needed with the newObject function. If the user specifies verbose mode or v in the command line then a log.out will be outputed to the current working directory. Verbose mode will call printInfo method that calls handlerString method and the printState method. The handlerString function will determine what instruction is currently executing and the printState method will print the state of the subject and object after each execution of instruction. These combination of functions and classes will simulate an artificial covert channel.

To run the program, be sure to be in the current working directory of where the files are. In the commmand line, type in "javac *.java" to compile all java files. Then type in "java CovertChannel TESTFILE.txt" or "java CovertChannel v TESTFILE.txt" where TESTFILE is the name of the test file. You must include the .txt extension in the name or the program doesn't know how to read an unknown file type. The v command turns on verbose mode. It should be noted that the bandwidth calculation should be in the readFile function and it is commented out.

[Machine Information]
The machine type is the x86_64 architecture type. The company is Intel and the clock speed is about 1600 MHz. This specification is from the UT CS lab machine. 

[Source Description]
The test files: testFiles.txt, testFiles2.txt, testFiles3.txt, and testFiles4.txt were created by me. However, the Pride_and_Prejudice.txt file was obtained from another website, http://www.kellynch.com/e-texts/PrideandPrejudice.php, and it was not created by me. The Metamorphosis.txt was obtained from the website, https://www.gutenberg.org/files/5200/5200-h/5200-h.htm and it was also not created by me. These files were used to test the output of my program and to test the overall bandwidth.

"Pride and Prejudice" original author by Jane Austen
"Metamorphosis" original author by Franz Kafka

[Finish]
The program is mostly finished. The output should be identical to the input file. However, the log output might be slow because the overall program might be unoptimized. There are better implementations for this program and there could be bugs that hasn't been resolved yet. This program is not perfect, but it is functioning. I also don't think I calculated the bandwidth correctly as well because the values are small, but these were computed using the UT CS Lab machines.

[Results Summary]
[No.]	[DocumentName] 		[Size] 	 	[Bandwidth]
1	testFile.txt	402 Bytes	0.023 bits/ms
2	testFile2.txt	54 Bytes	0.011 bits/ms
3	testFile3.txt	80 Bytes	0.015 bits/ms
4	testFile4.txt	119 Bytes	0.018 bits/ms
5	Pride_and_Prejudice.txt	677 Kilobytes	0.32 bits/ms
6	Metamorphosis.txt	136 Kilobytes	0.29 bits/ms