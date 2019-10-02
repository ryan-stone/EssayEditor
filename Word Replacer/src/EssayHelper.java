import javax.swing.*;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Good java swing basics tutorial: http://www.lsv.fr/~sankur/java/first.html


// Combines the editor with the GUI
public class EssayHelper extends JFrame {
	Editor editor;
	JFrame frame;
	TextArea textArea;
	
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
		panel.setToolTipText("Essay Helper tooltip"); // Hevering tooltip
		
		// Add an exit button (just for practice)
		JButton exitButton = new JButton("Exit");
		exitButton.setToolTipText("Exit Button");
		// ( x, y, width, height) * note y = 0 is the top
		exitButton.setBounds(WINDOW_WIDTH-110, WINDOW_HEIGHT-80, 80, 30);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		panel.add(exitButton);
		
		// Add a text area
		textArea = new TextArea();
		textArea.setBounds(30, 30, WINDOW_WIDTH-80 , WINDOW_HEIGHT-200);
		textArea.setEditable(true);
		//textArea.setText(editor.getTextString()); //not currently working
		//textArea.append(editor.getTextString());
		panel.add(textArea);
		
		// Set the JFrame attributes
		setTitle("Essay Helper");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
