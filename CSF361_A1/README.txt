UTEID: kdn433;
FIRSTNAME: Kevin;
LASTNAME: Nguyen;
CSACCOUNT: kxnguyen;
EMAIL: kxnguyen60@gmail.com;

[Program 1]
[Description]
There are 3 java files called SecureSystem.java, ObjectInstruction.java, and RefMonitor.java. Each of these files will complete a set of tasks in order to implement the security policy, star property. The star property is basically defined as subject and objects may or may not read or write based on the security levels. The main file is SecureSystem.java and it will initialize the entire program for use. However, SecureSystem.java also obtains the arguments from the command line and checks if we have the components for file reading. SecureSystem.java will also set up the subjects and objects (not dynammic yet) and store the state as an object. The functions initializeSubject, secureSubject, initializeObject, and secureObject will create the subject or object state and secure them by a mapping in RefMonitor class.  ObjectInstruction.java will then take the command line components and parse them through the readFile and parseStringTokens methods. ReadFile function will read the file line by line and we need parse each line. ParseStringTokens function will take each line from the file and split them into an array called tokens. We then check each token to decide if it is a valid instruction otherwise it will be a bad instruction. If it is a good instruction then we can pass information to our RefMonitor class which maintains the mapping of subjects, objects, and their security levels through a hashtable. There is also a printState function that merely prints the output of the state after each line of execution. The RefMonitor.java has checkRequest, handlerString, executeRead, and executeWrite functions. When information is sent to the RefMonitor class, it is likely to be passed into the executeRead or executeWrite functions. At this point, we need to check with checkRequest function if the subject and object exists in the hashtable. If it is, then we can perform the update. The handlerString function will output the activity that was specified in the file so the user knows what happened. Interestingly, the RefMonitor class has a nested inner class called ObjectManager class. This class is responsible for creation of objects and updating values in the object structure. It has methods called objectUpdate and newObject. ObjectUpdate will update the value of the state of objects based on what the subject has requested. NewObject function is the main component for creating objects for the system. Altogether these files implement the star property security.

Overall, the main point of the algorithm is check valid strings and security levels. Then, execute and update values as necessary. 

To compile the program, make sure you're in the right directory. Type in javac *.java then type in java SecureSystem TESTFILE.txt where TESTFILE.txt is the name of your test file. All of this should be done in the command line.

[Finish]
I have completed most of the assignment. The bugs are design bugs that the subjects and objects aren't flexible on creation and sometimes the naming is strict. Subject names and object names that are specified in the test file should not contain typos or errors if proper output is expected.

[Test Cases]
[Input of test 1]
write Hal HObj 
read Hal 
write Lyle LObj 10
read Hal LObj 
write Lyle HObj 20
write Hal LObj 200
read Hal HObj
read Lyle LObj
read Lyle HObj
foo Lyle LObj
Hi Lyle,This is Hal
The missile launch code is 1234567

[Output of test 1]
Reading from file: instructionList1.txt

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 0
        lyle has recently read: 0
        hal has recently read: 0

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 0
        lyle has recently read: 0
        hal has recently read: 0

lyle writes value 10 to lobj
The current state is:
        lobj has value: 10
        hobj has value: 0
        lyle has recently read: 0
        hal has recently read: 0

hal reads lobj
The current state is:
        lobj has value: 10
        hobj has value: 0
        lyle has recently read: 0
        hal has recently read: 10

lyle writes value 20 to hobj
The current state is:
        lobj has value: 10
        hobj has value: 20
        lyle has recently read: 0
        hal has recently read: 10

hal writes value 200 to lobj
The current state is:
        lobj has value: 10
        hobj has value: 20
        lyle has recently read: 0
        hal has recently read: 10

hal reads hobj
The current state is:
        lobj has value: 10
        hobj has value: 20
        lyle has recently read: 0
        hal has recently read: 20

lyle reads lobj
The current state is:
        lobj has value: 10
        hobj has value: 20
        lyle has recently read: 10
        hal has recently read: 20

lyle reads hobj
The current state is:
        lobj has value: 10
        hobj has value: 20
        lyle has recently read: 0
        hal has recently read: 20

Bad Instruction
The current state is:
        lobj has value: 10
        hobj has value: 20
        lyle has recently read: 0
        hal has recently read: 20

Bad Instruction
The current state is:
        lobj has value: 10
        hobj has value: 20
        lyle has recently read: 0
        hal has recently read: 20

Bad Instruction
The current state is:
        lobj has value: 10
        hobj has value: 20
        lyle has recently read: 0
        hal has recently read: 20



[Input of test 2]
wrte Hal Hobj 
rad Hal 
te Lyle Lobj 10
read Lyle obj
I like candy
read
write

[Output of test 2]
Reading from file: instructionList2.txt

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 0
        lyle has recently read: 0
        hal has recently read: 0

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 0
        lyle has recently read: 0
        hal has recently read: 0

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 0
        lyle has recently read: 0
        hal has recently read: 0

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 0
        lyle has recently read: 0
        hal has recently read: 0

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 0
        lyle has recently read: 0
        hal has recently read: 0

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 0
        lyle has recently read: 0
        hal has recently read: 0

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 0
        lyle has recently read: 0
        hal has recently read: 0


[Input of test 3]
write Lyle Hobj 100
write Hal Lobj 100
read Lyle Hobj
read Lyle Lobj
read Hal Hobj
read Lyle Lobj

[Output of test 3]
Reading from file: instructionList3.txt

lyle writes value 100 to hobj
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 0

hal writes value 100 to lobj
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 0

lyle reads hobj
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 0

lyle reads lobj
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 0

hal reads hobj
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 100

lyle reads lobj
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 100


[Input of test 4]
write lily susan 20
write Lyle Hobj 100
read Lyle Lobj

write Hal Hobj 100
read Hal Lobj

read Hal Hobj
read Lyle Lobj
read susan lily

[Output of test 4]
Reading from file: instructionList4.txt

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 0
        lyle has recently read: 0
        hal has recently read: 0

lyle writes value 100 to hobj
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 0

lyle reads lobj
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 0

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 0

hal writes value 100 to hobj
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 0

hal reads lobj
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 0

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 0

hal reads hobj
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 100

lyle reads lobj
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 100

Bad Instruction
The current state is:
        lobj has value: 0
        hobj has value: 100
        lyle has recently read: 0
        hal has recently read: 100
