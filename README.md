# Pattern Matching with Finite Automata

Goal:
1. Given a String, build an automata for deciding whether the String is a substring in other texts.
2. Use the automata to implement a pattern matching algorithm to locate all matches of a substring.

The Automata class should have the following methods:
1. A constructor which takes in a String argument and builds the automata.
2. transition(int, char): int which takes the state (an integer) and a character from the alphabet and outputs the state which the automata transitions to.
3. isFinal(int): boolean which takes a state as an argument and returns whether or not that state is final.

To Compile: under the same directory  "javac PatternMatch.java"
To Run: "java PatternMatch <filename for matternmatch>", I've included a 'test' file for your convenience
Enter the String you want to search in <filename>: <Any String>
