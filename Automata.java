/****************************************************************
 * THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING *
 * CODE WRITTEN BY OTHER STUDENTS OR SOURCES OUTSIDE OF THOSE   *
 * PROVIDED BY THE INSTRUCTOR. _Zigeng_Zhu_                     *
 ****************************************************************/

class Automata {

	protected static String      text;               // The Text String
	protected static String      pattern;            // The Pattern String
	protected static int[]       dfa;                // The array that contains Determininstic Finite Automaton (DFA)
	protected static int         state;              // The current state
	

	/*========================================================
	 * CONSTRUCTOR (OVERRIDING) Automata()
	 * Let the SetText method in PatternMatch.java to gain
	 * access to the class variables
	 *========================================================*/

	protected Automata() {}

	/*========================================================
	 * CONSTRUCTOR Automata(String p)
	 * Initializes class variables and builds the Automata
	 *
	 * @param p : input to set the pattern String
	 *========================================================*/

	protected Automata(String p) {
		pattern = p;                              // Initializes var pattern = p
		state = 0;                                // Initializes var state = 0 (P0)
		dfa = new int[text.length() + 1];         // Integer array that stores the transition functions from current state with each input char from the text string
		dfa[0] = 0;                               // Always start at zero state, so dfa[0] = 0
		for (int i = 1; i < dfa.length; i++) {    // Building the Automaton 
			dfa[i] = transition(state, text.charAt(i - 1));
			state = dfa[i];
		}
	}

	/*========================================================
	 * METHOD transition(String t, String p)
	 * Transition Function: Return the next state given the
	 * current state and a character input
	 * 
	 * @param q : the current state 
	 * @param c : the next character input 
	 * @return  : δ(q, c)
	 *========================================================*/

	private int transition(int q, char c) {
		if (q < pattern.length() && c == pattern.charAt(q)) { // If the character c is equal to the next character of the pattern
			return q + 1;                          // Increment the current state by 1
		} 
		int k;
		for (k = q; k > 0; k--) {
			if (isSuffix(pattern.substring(0, k), pattern.substring(0, q) + c)) { 
				return k;                          // If Pk is a suffix of Pq + c, δ(q, c) = k
			}
		}
		return 0;                                  // Return to initial state P0 if for every value of k, Pk is not a suffix of Pq + c 
	}

	/*========================================================
	 * METHOD isFinal()
	 *
	 * @return : whether the current state is at final state
	 *========================================================*/

	protected boolean isFinal() {
		return state == pattern.length();          // The #states in a DFA = #characters in a pattern
	}

	/*========================================================
	 * HELPER METHOD isSuffix(String a, String b)
	 * 
	 * @param a : the first input String
	 * @param b : the second input String
	 * @return  : whether String a is a suffix of String b
	 * OR String b is a suffix of String a (Either way works!)
	 *========================================================*/

	private boolean isSuffix(String a, String b) {
		int a_len = a.length();
		int b_len = b.length();
		if (a_len == 0 || b_len == 0) {             // Case I   : Either String a or b is empty
			return false;                           //            Neither is a suffix of the other
		} 
		if (a_len >= b_len) {                       // Case II  : If String a is longer than b 
			return a.endsWith(b);                   //            Return whether b is a suffix of a
		}
		if (a_len <= b_len) {                       // Case III : If String b is longer than a
			return b.endsWith(a);                   //            Return whether a is a suffix of b  
		} else {
			return false;                           //            Return false if none of the cases were satisfied
		}
	}
}
	