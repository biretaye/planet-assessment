# planet-assessment

Run the code after specifying an input file and a sorting attribute when invoking the program in the main method. 


The program works by reading the specified input file and processing it according to the file format. It first checks the format of the file by checking the first line of text in the files to call the appropriate methods based on the file format. 

For comma separated files, it uses the comma as a delimeter to find all the elements. For space filled files it uses the size of the elements as way to identify all the elements. 

The program then stores these elements as key,value pairs in a hashmap then stores these pairs in a list such that each record on the list has all the elements for one record. This list will have all the records of a single file. This list is then used to sort the file based on the specified attribute. 

Output file is written by iterating througb these files and rearranging the formatting as specified. When writing the output file, default values are assigned for missing element vaues. 


