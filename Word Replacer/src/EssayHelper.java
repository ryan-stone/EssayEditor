import javax.swing.*;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Good java swing basics tutorial: http://www.lsv.fr/~sankur/java/first.html


// Combines the editor with the GUI
public class EssayHelper extends JFrame {
	Editor editor;
	JFrame frame;
	JTextArea textArea;
	JLabel numSentencesLbl;
	JLabel numWordsLbl;
	JButton userEditTextBtn;
	JButton finishUserEditBtn;
	JTextArea suggestionLbl;
	JButton suggestionBtn;
	JButton saveBtn;
	JButton openBtn;
	String path;
	
	final int WINDOW_WIDTH = 600;
	final int WINDOW_HEIGHT = 600;
	
	public EssayHelper(){
		editor = new Editor();
		
		initUI();
	}
	
	// Initialize the user interface
	private void initUI() {
		// Initialize the panel where buttons go
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		// Set the JFrame attributes
		setTitle("Essay Helper");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Add a text area
		textArea = new JTextArea();
		textArea.setBounds(30, 60, WINDOW_WIDTH-80 , WINDOW_HEIGHT-230);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setText(editor.getTextString());
		panel.add(textArea);
		
		// Add Open file button
		openBtn = new JButton("Open");		// Practice: C:\Users\rston\git\EssayEditor\Word Replacer\src\hello.txt
		openBtn.setBounds(WINDOW_WIDTH-570, WINDOW_HEIGHT-580, 70, 30);
		openBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				path = JOptionPane.showInputDialog("URL:");
				path = convertPath(path);
				editor.openFile(path);
				textArea.setText(editor.getTextString());
				refresh();
			}
		});
		panel.add(openBtn);
		
		// Add a save button
		saveBtn = new JButton("Save");
		saveBtn.setBounds(WINDOW_WIDTH-225, WINDOW_HEIGHT-80, 85, 30);
		saveBtn.setToolTipText("Click here to save your file");
		panel.add(saveBtn);
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (finishUserEditBtn.isVisible())
					JOptionPane.showMessageDialog(null, "Please finish editing before saving");
				else editor.saveFile();
			}
		});
		
		
		// Add a button that allows the user to edit text
		userEditTextBtn = new JButton("User Edit");
		userEditTextBtn.setToolTipText("Click here to enable user editing");
		userEditTextBtn.setBounds(WINDOW_WIDTH-570, WINDOW_HEIGHT-80, 90, 30);
		userEditTextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				userEditText();
			}
		});
		panel.add(userEditTextBtn);
		
		// Add a button that ends user editing
		finishUserEditBtn = new JButton("Finish Edit");
		finishUserEditBtn.setToolTipText("Click here to finish user editing");
		finishUserEditBtn.setBounds(WINDOW_WIDTH-470, WINDOW_HEIGHT-80, 95, 30);
		finishUserEditBtn.setVisible(false);
		finishUserEditBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				finishUserEdit();
			}
		});
		panel.add(finishUserEditBtn);
		
		// Label for Number of Words
		numWordsLbl = new JLabel("Words: " + Integer.toString(editor.getNumWords()));
		numWordsLbl.setBounds(WINDOW_WIDTH-570, WINDOW_HEIGHT-170, 100, 30);
		panel.add(numWordsLbl);
		
		// Add a Label to show Number of sentences
		numSentencesLbl = new JLabel("Sentences: " + Integer.toString(editor.getNumSentences()));
		numSentencesLbl.setBounds(WINDOW_WIDTH-470, WINDOW_HEIGHT-170, 200, 30);
		panel.add(numSentencesLbl);		
		
		// Add a button to get a suggestion
		suggestionBtn = new JButton("New Suggestion");
		suggestionBtn.setToolTipText("Click here for a suggestion");
		suggestionBtn.setBounds(WINDOW_WIDTH-365, WINDOW_HEIGHT-80, 130, 30);
		suggestionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				suggestionLbl.setText(editor.getSuggestion());
				suggestionLbl.setVisible(true);
			}
		});
		panel.add(suggestionBtn);
		
		// Add a label to display a suggestion
		suggestionLbl = new JTextArea();
		suggestionLbl.setBounds(WINDOW_WIDTH-570, WINDOW_HEIGHT-140, 520, 50);
		suggestionLbl.setLineWrap(true);
		suggestionLbl.setVisible(false);
		panel.add(suggestionLbl);
		
		// Add an exit button that saves before editing
		JButton exitButton = new JButton("Exit");
		exitButton.setToolTipText("Save & Exit");
		// ( x, y, width, height) * note y = 0 is the top
		exitButton.setBounds(WINDOW_WIDTH-130, WINDOW_HEIGHT-80, 80, 30);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				editor.saveFile();
				System.exit(0);
			}
		});
		panel.add(exitButton);
		
	}
	
	private void userEditText() {
		textArea.setEditable(true);
		finishUserEditBtn.setVisible(true);
	}
	
	private void finishUserEdit() {
		textArea.setEditable(false);
		finishUserEditBtn.setVisible(false);
		editor.newText(textArea.getText());
		refresh();
	}
	
	private void refresh() {
		numWordsLbl.setText("Words: " + Integer.toString(editor.getNumWords()));
		numSentencesLbl.setText("Sentences: " + Integer.toString(editor.getNumSentences()));
	}
	
	private String convertPath(String p) {
		String temp = "";
		for (int i = 0; i < p.length(); i++) {
			temp += p.charAt(i);
			if ((p.charAt(i) == '\\') && (i < p.length() - 1) && (p.charAt(i+1) != '\\') && (p.charAt(i-1) != '\\')) {
				temp += '\\';
			}
		}
		System.out.println(temp);
		return temp;
	}
	
	
}
