UTEID: kdn433;
FIRSTNAME: Kevin;
LASTNAME: Nguyen;
CSACCOUNT: kxnguyen;
EMAIL: kxnguyen60@gmail.com;


[Program 5]
[Description]
There are a total of 5 java files. The main file is PasswordCrack.java which mainly takes in arguments and makes sure the argument length is valid. If valid, the main class passes to the FileHandler class that handles the file operations and actual cracking. The FileHandler class has to manage two lists of inputs and they are the dictionary list and the encrypted password list. The readFile function will read into the encrypted password list and for each line, it will be passed into the crackerPassword function. This function performs the process to crack the password by extracting the given information and calling the tryMangling function to edit the possible phrases that can crack the password. TryMangling function merely tries to perform every possible mangle and returns true if success (it will skip the rest of the calls) otherwise try a new mangling method. FileHandler class also has the print methods to print the current activity of the run and then print the overall results at the end of the program. Timing for each result is also kept and it is printed in microseconds. MangleString class is a function that implements a wide variety of different ways to mangle or modify a string. When a string has been modified, the modified string, phrase, will be passed into the jcrypt class (along with the salt), crypt function. The crypt function will return a new encryption and then a comparison is made to ensure that the encryptions match and if they do, return true, otherwise return false. ValidateGuess.java is a class with one function which returns true or false if the strings are equal. The goal here is to brute force the encryption cracking method by applying different mangling methods and printing a summary of the results for the user to see.

The jcrypt.java class was given in the project page and it was not created by me. The credit goes to the original creator of the algorithm. I modified it to take a public constructor so I can use it for this assignment. The only function that was used was the crypt function. However, I did not fully looked into the implementation, but I do know the output will be a new encryption string. That new string is returned and I used the new encryption string and compare with the original string to see if they match.

SOURCE: http://www.vulcanware.com/java_jcrypt/jcrypt.java

To run the program, make sure the files and you are in the current working directory. In the command line, type in, "javac *.java" to compile all java files. Then type in, "java PasswordCrack inputFile1 inputFile2" to run the program. The inputFile1 will always be the dictionary. The inputFile2 will be the encrypted password list and this inputFile2 name might change depending on the file name for testing.

[Finish]
The program is mostly finished and there were some passwords that were too difficult to crack. There may have been more mangling options for strings that I haven't tried; as a result, not all passwords was cracked. There could also be unoptimized algorithms and hidden bugs in the program that I haven't identified yet. Overall, the program should run without any serious issues. The program may also run slow due to the exponential complexity time. The average time is 10-15 minutes for completion.


[Test Case 1]
Name: inputFile2

[Input]
michael:atbWfKL4etk4U:500:500:Michael Ferris:/home/michael:/bin/bash		
abigail:&i4KZ5wmac566:501:501:Abigail Smith:/home/abigail:/bin/tcsh
samantha:(bUx9LiAcW8As:502:502:Samantha Connelly:/home/samantha:/bin/bash
tyler:<qt0.GlIrXuKs:503:503:Tyler Jones:/home/tyler:/bin/tcsh
alexander:feohQuHOnMKGE:504:504:Alexander Brown:/home/alexander:
james:{ztmy9azKzZgU:505:505:James Dover:/home/james:/bin/bash
benjamin:%xPBzF/TclHvg:506:506:Benjamin Ewing:/home/benjamin:/bin/bash
morgan:khnVjlJN3Lyh2:507:507:Morgan Simmons:/home/morgan:/bin/bash
jennifer:e4DBHapAtnjGk:508:508:Jennifer Elmer:/home/jennifer:/bin/bash
nicole:7we09tBSVT76o:509:509:Nicole Rizzo:/home/nicole:/bin/tcsh
evan:3dIJJXzELzcRE:510:510:Evan Whitney:/home/evan:/bin/bash
jack:jsQGVbJ.IiEvE:511:511:Jack Gibson:/home/jack:/bin/bash
victor:w@EbBlXGLTue6:512:512:Victor Esperanza:/home/victor:
caleb:8joIBJaXJvTd2:513:513:Caleb Patterson:/home/caleb:/bin/bash
nathan:nxsr/UAKmKnvo:514:514:Nathan Moore:/home/nathan:/bin/ksh
connor:gwjT8yTnSCVQo:515:515:Connor Larson:/home/connor:/bin/bash
rachel:KelgNcBOZdHmA:516:516:Rachel Saxon:/home/rachel:/bin/bash
dustin:5WW698tSZJE9I:517:517:Dustin Hart:/home/dustin:/bin/csh
maria:!cI6tOT/9D2r6:518:518:Maia Salizar:/home/maria:/bin/zsh
paige:T8jwuve9rQBo.:519:519:Paige Reiser:/home/paige:/bin/bash

[Output]
:SUCCESS: The password for Michael Ferris is michael : [Time = 10293.813 ms]
:SUCCESS: The password for Abigail Smith is liagiba : [Time = 2660.198 ms]
:SUCCESS: The password for Samantha Connelly is amazing : [Time = 4749613.51 ms]
:SUCCESS: The password for Tyler Jones is eeffoc : [Time = 3.0886375445E7 ms]
:SUCCESS: The password for Alexander Brown is squadro : [Time = 1.55673542073E8 ms]
:SUCCESS: The password for James Dover is icious : [Time = 1.78876654201E8 ms]
:SUCCESS: The password for Benjamin Ewing is abort6 : [Time = 443369.908 ms]
:SUCCESS: The password for Morgan Simmons is rdoctor : [Time = 4.6644291785E7 ms]
:SUCCESS: The password for Jennifer Elmer is doorrood : [Time = 4.7154970005E7 ms]
:SUCCESS: The password for Nicole Rizzo is keyskeys : [Time = 8.7163586613E7 ms]
:SUCCESS: The password for Evan Whitney is Impact : [Time = 7.9066700955E7 ms]
:SUCCESS: The password for Jack Gibson is sATCHEL : [Time = 1.43183813976E8 ms]
:SUCCESS: The password for Victor Esperanza is THIRTY : [Time = 1.65783443179E8 ms]
:SUCCESS: The password for Caleb Patterson is teserP : [Time = 1.25535604659E8 ms]
:SUCCESS: The password for Nathan Moore is sHREWDq : [Time = 1.48407519346E8 ms]
:SUCCESS: The password for Connor Larson is enoggone : [Time = 6.8597904198E7 ms]
:SUCCESS: The password for Rachel Saxon is obliqu3 : [Time = 1.08890731402E8 ms]
:SUCCESS: The password for Dustin Hart is litpeR : [Time = 1.36976525081E8 ms]
:SUCCESS: The password for Maia Salizar is Salizar : [Time = 11379.898 ms]
:FAILURE: Unable to crack password for Paige Reiser : [Time = 1.86586262278E8 ms]

In total, I can crack 19/20 password. However, I can not crack 1/20 password, the list is...
Paige


[Test Case 2]
name: inputFile3

[Input]
michael:atQhiiJLsT6cs:500:500:Michael Ferris:/home/michael:/bin/bash		
abigail:&ileDTgJtzCRo:501:501:Abigail Smith:/home/abigail:/bin/tcsh
samantha:(bt0xiAwCf7nA:502:502:Samantha Connelly:/home/samantha:/bin/bash
tyler:<qf.L9z1/tZkA:503:503:Tyler Jones:/home/tyler:/bin/tcsh
alexander:fe8PnYhq6WoOQ:504:504:Alexander Brown:/home/alexander:
james:{zQOjvJcHMs7w:505:505:James Dover:/home/james:/bin/bash
benjamin:%xqFrM5RVA6t6:506:506:Benjamin Ewing:/home/benjamin:/bin/bash
morgan:kh/1uC5W6nDKc:507:507:Morgan Simmons:/home/morgan:/bin/bash
jennifer:e4EyEMhNzYLJU:508:508:Jennifer Elmer:/home/jennifer:/bin/bash
nicole:7wKTWsgNJj6ac:509:509:Nicole Rizzo:/home/nicole:/bin/tcsh
evan:3d1OgBYfvEtfg:510:510:Evan Whitney:/home/evan:/bin/bash
jack:js5ctN1leUABo:511:511:Jack Gibson:/home/jack:/bin/bash
victor:w@FxBZv.d0y/U:512:512:Victor Esperanza:/home/victor:
caleb:8jGWbU0xbKz06:513:513:Caleb Patterson:/home/caleb:/bin/bash
nathan:nxr9OOqvZjbGs:514:514:Nathan Moore:/home/nathan:/bin/ksh
connor:gw9oXmw1L08RM:515:515:Connor Larson:/home/connor:/bin/bash
rachel:KenK1CTDGr/7k:516:516:Rachel Saxon:/home/rachel:/bin/bash
dustin:5Wb2Uqxhoyqfg:517:517:Dustin Hart:/home/dustin:/bin/csh
maria:!cSaQELm/EBV.:518:518:Maia Salizar:/home/maria:/bin/zsh
paige:T8U5jQaDVv/o2:519:519:Paige Reiser:/home/paige:/bin/bash

[Output]
:SUCCESS: The password for Michael Ferris is tremors : [Time = 1.72782796432E8 ms]
:SUCCESS: The password for Abigail Smith is Saxon : [Time = 1.0866385022E7 ms]
:SUCCESS: The password for Samantha Connelly is cOnNeLlY : [Time = 3580.617 ms]
:SUCCESS: The password for Tyler Jones is eltneg : [Time = 6.7717735184E7 ms]
:FAILURE: Unable to crack password for Alexander Brown : [Time = 1.89945300845E8 ms]
:SUCCESS: The password for James Dover is enchant$ : [Time = 5.2847085229E7 ms]
:SUCCESS: The password for Benjamin Ewing is soozzoos : [Time = 1.89559871369E8 ms]
:SUCCESS: The password for Morgan Simmons is dIAMETER : [Time = 4.4268367119E7 ms]
:SUCCESS: The password for Jennifer Elmer is ElmerJ : [Time = 5892.152 ms]
:SUCCESS: The password for Nicole Rizzo is INDIGNITIES : [Time = 8.1977172903E7 ms]
:SUCCESS: The password for Evan Whitney is ^bribed : [Time = 2.0019968575E7 ms]
:SUCCESS: The password for Jack Gibson is ellows : [Time = 1.4428737591E7 ms]
:FAILURE: Unable to crack password for Victor Esperanza : [Time = 1.89622413093E8 ms]
:SUCCESS: The password for Caleb Patterson is zoossooz : [Time = 1.89297360729E8 ms]
:FAILURE: Unable to crack password for Nathan Moore : [Time = 1.86586262278E8 ms]
:FAILURE: Unable to crack password for Connor Larson : [Time = 1.86586262278E8 ms]
:FAILURE: Unable to crack password for Rachel Saxon : [Time = 1.86586262278E8 ms]
:FAILURE: Unable to crack password for Dustin Hart : [Time = 1.86586262278E8 ms]
:FAILURE: Unable to crack password for Maria Salizar : [Time = 1.86586262278E8 ms]
:FAILURE: Unable to crack password for Paige Reiser : [Time = 1.86586262278E8 ms]


In total, I can crack 12/20 password. However, I can not crack 8/20 password, the list is...
Alexander
Victor
Nathan
Connor
Rachel
Dustin
Maria
Paige
