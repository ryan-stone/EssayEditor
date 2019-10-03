
public class Editor_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Editor editor = new Editor();
		editor.openFile("C:\\Users\\Ryan\\git\\EssayEditor\\Word Replacer\\src\\hello.txt");
		editor.printText();
		editor.replaceWord(0, "Goodbye");
		editor.printText();
		editor.saveFile();
		editor.insertWord(1, "Goodbye");
		editor.saveFile();

		editor.printOccurrences();
		
		editor.refresh();
		editor.printOccurrences();
		
		editor.newText("Replacing new text with this new string");
		editor.saveFile();
		editor.printOccurrences();
		System.out.println(editor.getTextString());
		
		
	}

}
