/****************************************************************
 * THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING *
 * CODE WRITTEN BY OTHER STUDENTS OR SOURCES OUTSIDE OF THOSE   *
 * PROVIDED BY THE INSTRUCTOR. _Zigeng_Zhu_                     *
 ****************************************************************/

import java.util.Scanner;
import java.io.* ;
import java.util.ArrayList;

public class PatternMatch {

	/*================================================================
	 * METHOD searchPattern(String filename, String search)
	 * Calling the Automata Constructor to construct an dfa 
	 * For every line in the file; Then search for matching 
	 * Patterns in the designated line. If found, print the 
	 * result.
	 *
	 * @param filename : the name of the txt file (without extension)
	 * @param search   : the pattern desired to be searched
	 *================================================================*/

	private static void searchPattern(String filename, String search) {
		long startTime = System.currentTimeMillis();
		ArrayList<String> content = txtReader(filename);        // Using an arraylist of Strings to store each line in the txt file
		int lineCount = content.size();                         // Total #lines in the txt file
		int matchCount = 0;                                     // Initializes the var matchCount that counts #matches found
		for (int i = 0; i < lineCount; i++) {                   // Every line creates a new Automata to do the search
			setText(content.get(i));                            // Set text class variable in Automata prior the constructor is called 
			Automata a = new Automata(search);                  // For each line in the txt file, create a DFA to search for matching patterns in that line
			int lineMatchCount = 0;
			boolean flag = false;
			for (int j = 0; j < a.dfa.length; j++) {
				a.state = a.dfa[j];
				if (a.isFinal()) {                              // Whenever the final state is reached, a match of the pattern is found 
					matchCount++;                               // Increment #matches found whenever a match is found
					lineMatchCount++;                           // Increment #matches found in ONE line whenever a match in that line is found
					flag = true;                                // flag = true whenever a match is found
				}
			} if (flag == true && lineMatchCount == 1) {        // If there is only 1 match found in the line, print "A match ..."
				System.out.println("A match of " + a.pattern + " is found at: ");
				System.out.println("[Line " + (i + 1) + "] " + content.get(i)); //Because i starts from 0, but people normally count line from 1, so use i + 1 
			} else if (flag == true && lineMatchCount > 1) {    // If there are multiple matches found in the line, print "#found matches ..."
				System.out.println("" + lineMatchCount + " matches of " + a.pattern + " is found at: ");
				System.out.println("[Line " + (i + 1) + "] " + content.get(i));	//Because i starts from 0, but people normally count line from 1, so use i + 1			
			}
		}
		long endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		System.out.println("Total matches found: " + matchCount + "\nTotal time spent: " + time + " ms");
	}

	/*================================================================
	 * HELPER METHOD txtReader(String filename)
	 * Finds the txt file with a specified name; Read the file and 
	 * put each line of the text into an Arraylist of Strings
	 *
	 * @param filename : the name of the txt file (without extension)
	 * @return         : an ArrayList that put each line of the 
	 *                   txt file into an string element
	 *================================================================*/

	private static ArrayList<String> txtReader(String filename) {
		ArrayList<String> content = new ArrayList<String>();    // Initializes an ArrayList of Strings           
		try {                                                   // Using try catch for FileNotFoundException
			Scanner reader = new Scanner(new FileReader(filename)); // Initializes a Scanner to read the txt file given its name
			while(reader.hasNextLine()) {                       // When there is a line in the txt file:
				StringBuilder sb = new StringBuilder();         // 1. Initializes a new StringBuilder
				sb.append(reader.nextLine());                   // 2. Appends the line to the StringBuilder
				content.add(sb.toString());                     // 3. Output the line as a String from the StringBuilder, Add it to the ArrayList
			}
			reader.close();                                     // Close the scanner                                             
		} catch (FileNotFoundException exception) {
			System.out.println("File with this name doesn't exist!"); // Catches FileNotFoundException, print error message
		}
		return content;                                         // Return the filled ArrayList
	}

	/*================================================================
	 * HELPER METHOD setText(String line)
	 * Because the homework requires that the Automata constructor 
	 * can only take in pattern, but my constructor need the static
	 * var text to create the dfa, so I use this method to set the 
	 * text variable in the Automata class before the constructor is 
	 * called. It calls an overriding constructor that does nothing, 
	 * but gains access to text. Then It will set text equals to the 
	 * parameter, which is a single line in the text file.
	 *
	 * @param line : the string that sets var text in class Automata
	 *               before the real constructor is called.
	 *================================================================*/

	private static void setText(String line) {
		Automata a = new Automata();
		a.text = line;
	}

	/*================================================================
	 * METHOD main(String []args)
	 * Main method
	 *================================================================*/

	public static void main(String []args) {
		Scanner s = new Scanner(System.in);
		File f = new File(args[0]);
		String filename = f.getName();
		System.out.println("Enter the String you want to search in " + filename + " : ");
		String search = s.nextLine();
		searchPattern(filename, search);
		s.close();
	}
}