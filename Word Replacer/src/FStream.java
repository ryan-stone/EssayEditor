import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/* TODO: Find a way to track '\n' endline characters
 * so that we can keep track of paragraphs when we eventually
 * return the final product as a new text document*/

public class FStream {
	private File file;
	private Scanner sc;
	private ArrayList<String> text = new ArrayList<String>();
	private String inputPath;
	
	FStream(String p){
		setInputPath(p); // Saves the input path string
		openFile(inputPath);
	}
	
	// Opens and parses a text file
	public void openFile(String path) {
		file = new File(path);
		// Open a scanner
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Parse document
		parse();
	}
	
	// Parses a text file by inserting words and into an ArrayList. 
	// Also counts number of words and number of sentences.
	public void parse() {
		while (sc.hasNext()) {
			String word;
			word = sc.next();
			
			// Check sentences
			int periodIndex = word.indexOf('.');
			int questionIndex = word.indexOf('?');
			int exclamationIndex = word.indexOf('!');
			if (periodIndex != -1 && periodIndex == word.length() - 1) {
				word = word.substring(0, word.length()-1);
				text.add(word);
				text.add(".");
			}
			else if (exclamationIndex != -1 && exclamationIndex == word.length() - 1) {
				word = word.substring(0, word.length()-1);
				text.add(word);
				text.add("!");
			}
			else if (questionIndex != -1 && questionIndex == word.length() - 1) {
				word = word.substring(0, word.length()-1);
				text.add(word);
				text.add("?");
			}
			else {
				text.add(word);
			}
		}
	}
	
	// Saves new file as a new text document
	public void saveFile() {
		FileWriter fr = null;
		try {
			fr = new FileWriter(getOutputPath());
			fr.write(getTextString());
		} catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fr.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void overwrite(ArrayList<String> newText) {
		text.clear();
		for (int i = 0; i < newText.size(); i++) {
			text.add(newText.get(i));
		}
	}
	
	private void printText() {
		for (int i = 0; i < text.size(); i++) {
			System.out.print(text.get(i) + " ");
		}
	}
	

	// Returns the ArrayList of words
	public ArrayList<String> getText(){
		return text;
	}
	
	
	// Input File Path
	private void setInputPath(String p) {
		inputPath = p;
	}
	
	// Output File Path
	private String getOutputPath() {
		String newPath = inputPath.replace(".txt", ""); // Does this edit the input path?
		return newPath + "_NEW.txt";
	}
	

	// Get the full text as a single string
	public String getTextString() {
		String textString = "";
		for (int i = 0; i < text.size(); i++) {
			textString += text.get(i);
			if ((i < text.size()-1) && !isPunctuation(text.get(i+1))) {
				textString += " ";
			}
		}
		return textString;
	}

	// Checks if a string is a sentence ending punctuation mark
	private boolean isPunctuation(String c) {
		if (c == "." || c == "!" || c == "?") 
			return true;
		return false;
	}
	
}