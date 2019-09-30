import java.util.ArrayList;
import java.util.HashMap;

public class Editor {
	FStream file;
	ArrayList<String> text;
	int numWords, numSentences;
	HashMap<Integer, String> wordMap;
	
	public Editor(){
		text = new ArrayList<String>();
	}
	
	public void openFile(String path) {
		file = new FStream(path);
		ArrayList<String> tempText = file.getText();
		for (int i = 0; i < tempText.size(); i++) {
			text.add(tempText.get(i));
		}
		parseText();
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
	public void parseText() {
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
	
	public int getNumWords() {
		return numWords;
	}
	
	public int getNumSentences() {
		return numSentences;
	}
}
