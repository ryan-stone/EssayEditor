import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Editor {
	FStream file;
	ArrayList<String> text;
	int numWords, numSentences;
	HashMap<String, Integer> wordMap;
	ArrayList<String> commonWords;
	ArrayList<String> suggestions;
	int suggestionPosition;
	
	public Editor(){
		text = new ArrayList<String>();
		wordMap = new HashMap<String, Integer>();
		commonWords = new ArrayList<String>();
		suggestions = new ArrayList<String>();
		suggestionPosition = 0;
		populateCommonWordFromFile();
	}
	
	public void openFile(String path) {
		file = new FStream(path);
		ArrayList<String> tempText = file.getText();
		text.clear();
		for (int i = 0; i < tempText.size(); i++) {
			text.add(tempText.get(i));
		}
		refresh();
	}
	
	public void populateCommonWordFromFile() {
		String wordPath = "src\\common_words.txt";
		File commonWordsFile = new File(wordPath);
		Scanner sc;
		// Open a scanner
		try {
			sc = new Scanner(commonWordsFile);
			while (sc.hasNext()) {
				commonWords.add(sc.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public boolean checkCommonWord(String str) {
		for (int i = 0; i < commonWords.size(); i++) {
			if (str.equals(commonWords.get(i))) {
				return true;// common word
			}
		}
		return false; // not a common word
		
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
			if (word.equals(".") || word.equals("!") || word.equals("?")) {
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
			String word = text.get(i).toLowerCase();
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
	
	// If text is completely edited, refresh the array
	public void newText(String str) {
		text.clear();
		String newStr = "";
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			
			if (c == ' ') {
				System.out.println("Space at " + i); //////
				if (newStr.length() != 0) {
					text.add(newStr);
					newStr = "";					
				}
			}
			else if (c == '.' || c == '?' || c == '!') {
				System.out.println("Punctuation at " + i);
				text.add(newStr);
				text.add(Character.toString(c));
				newStr = "";
			}
			else {
				newStr += c;
				//Catch the last word in the string
				if (i == str.length() -1 ) {
					text.add(newStr);
				}
			}
		}		
		refresh();
	}
	
	// We will return suggestions from here, this is very incomplete
	public String getSuggestion() {
		if (suggestions.size() > 0 && suggestionPosition < suggestions.size()) {
			String str = suggestions.get(suggestionPosition);
			suggestionPosition = (suggestionPosition + 1) % suggestions.size();
			return str;
		}
		else {
			return "No more suggestions";
		}
	}
	
	public void populateSuggestions() {
		// calls the various populate functions
		suggestions.clear();
		populateSuggestionMostUsedWords();
		populateWordsToAvoid();
		
	}
	
	private void populateWordsToAvoid(){
		
	}
	
	private void populateSuggestionMostUsedWords() {
		ArrayList<String> mostUsedWords = getMostUsedWords();
		String word;
		
		String suggestion;
		
		for (int i = 0; i < mostUsedWords.size(); i++){
			word = mostUsedWords.get(i);

			if (!checkCommonWord(word)) {
				suggestion = "The word \"" + word + "\" is used " + wordMap.get(word) + " times.";
				suggestion += "\nConsider replacing this word with something else.";
				suggestions.add(suggestion);
			}
			
		}

	}
	
	private ArrayList<String> getMostUsedWords(){
		ArrayList<String> wordList = new ArrayList<String>();
		int rarityThreshold = (int) Math.floor(0.05d * text.size());	// 5% threshold for common words
		
		for (int i = 0; i < text.size(); i++) {
			String word = text.get(i);
			if (wordMap.get(word) != null && wordMap.get(word) > rarityThreshold) {
				if (wordList.contains(word)) {
					continue;
				}
				else {
					wordList.add(word);
				}
			}
		}

		
		return wordList;
	}
}
