import java.util.ArrayList;
import java.util.HashMap;

public class Editor {
	FStream file;
	ArrayList<String> text;
	int numWords, numSentences;
	HashMap<String, Integer> wordMap;
	
	public Editor(){
		text = new ArrayList<String>();
		wordMap = new HashMap<String, Integer>();
	}
	
	public void openFile(String path) {
		file = new FStream(path);
		ArrayList<String> tempText = file.getText();
		for (int i = 0; i < tempText.size(); i++) {
			text.add(tempText.get(i));
		}
		refresh();
	}
	
	public void saveFile() {
		file.overwrite(text);
		file.saveFile();
	}
	
	// Edit a Word in the ArrayList
	public void replaceWord(int pos, String newWord) {
		if (pos < text.size() && pos >= 0) {
			text.set(pos, newWord);
		}
	}
	
	// Delete a word from the ArrayList
	public void deleteWord(int pos) {
		if (pos < text.size() && pos >= 0) {
			text.remove(pos);
			numWords--;
		}
	}
	
	// Inserts a word into the ArrayList
	public void insertWord(int pos, String word) {
		if (pos <= text.size() && pos >= 0) {
			text.add(pos, word);
			numWords++;
		}
	}
	
	// Prints the text normally
	public void printText() {
		for (int i = 0; i < text.size(); i++) {
			String word = text.get(i);
			String nextWord;
			if (i != text.size()-1) {
				nextWord = text.get(i+1);
				if (!(nextWord.length() == 1 && isPunctuation(nextWord))){
					System.out.print(word + " ");	
				}
				else {
					System.out.print(word);
				}
			}
			else System.out.print(text.get(i));			
		}
		System.out.println("\nWords: " + getNumWords() + " Sentences: " + getNumSentences());
	}
	
	// Prints the text with . / ? spaced out
	public void printTextSpaced() {
		for (int i = 0; i < text.size(); i++) {
			String word = text.get(i);
			System.out.print(word + " ");
		}
		System.out.println("\nWords: " + getNumWords() + " Sentences: " + getNumSentences());
	}
	
	// Checks if a string is a sentence ending punctuation mark
	private boolean isPunctuation(String c) {
		if (c == "." || c == "!" || c == "?") 
			return true;
		return false;
	}
	
	// Updates number of sentences and words in text
	private void parseText() {
		numSentences = 0;
		numWords = 0;
		for (int i = 0; i < text.size(); i++) {
			String word = text.get(i);
			if (word == "." || word == "!" || word == "?") {
				numSentences++;
			}
			else numWords++;
		}
	}
	
	// Counts occurrences of each unique word, stores in HashMap
	// HashMap allows for O(n) run time
	private void parseWordOccurrences() {
		wordMap.clear();
		for (int i = 0; i < text.size(); i++) {
			String word = text.get(i);
			if (word != "." && word != "?" && word != "!") { // can maybe remove this line?
				Integer count = wordMap.get(word);
				if (count == null) {
					count = 0;
				}
				wordMap.put(word,  count + 1);
			}
		}
	}
	
	// Refreshes the word & sentence counts
	public void refresh() {
		parseText();
		parseWordOccurrences();
	}
	
	public int getNumWords() {
		return numWords;
	}
	
	public int getNumSentences() {
		return numSentences;
	}
	
	// Issue: currently prints each time a word appears
	// even if the word is a duplicate
	public void printOccurrences() {
		for (int i = 0; i < text.size(); i++) {
			String word = text.get(i);
			System.out.print(word + " - " + wordMap.get(word) + " | ");
		}
		System.out.println();
	}
}
