package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import utils.DataSingleton;

public class CharacterWindow {

	String characterName, characterDetails;
	boolean editing;

	private final PropertyChangeSupport support = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	public CharacterWindow() {
		setCharacterName("");
		setCharacterDetails("");
		setWindow();
		editing = false;
	}

	public CharacterWindow(String chName, String chDetails) {
		setCharacterName(chName);
		setCharacterDetails(chDetails);
		setWindow();
		editing = true;
	}

	public void setWindow() {
		JFrame frame = new JFrame("Create or edit a character file! ");

		String chDet = "", chPhDet = "";

		if (characterDetails.length() != 0) {
			System.out.println(characterDetails);
			String[] details = characterDetails.split("█");
			if(details.length == 2)
			{
				chPhDet = details[0].replace("Physical description :" , "");
				chDet = details[1].replace("Character description :" , "");
			}
			else
			{
				if(details[0].contains("Character"))
				{
					chDet = details[0];
				}
				else if (details[0].contains("Physical"))
				{
					chPhDet = details[0];
					chDet = "";
				}
			}
		}

		JLabel labelName = new JLabel();
		labelName.setText("Enter character name here :");
		labelName.setBounds(10, 10, 300, 100);

		JTextArea chName = new JTextArea();
		chName.setBounds(210, 50, 250, 30);
		chName.setText(characterName);
		chName.setLineWrap(true);
		chName.setWrapStyleWord(true);

		JLabel labelPhDetails = new JLabel();
		labelPhDetails.setText("Enter physical description here :");
		labelPhDetails.setBounds(10, 90, 300, 30);

		JTextArea chPhDetails = new JTextArea();
		chPhDetails.setBounds(210, 90, 250, 200);
		chPhDetails.setText(chPhDet);
		chPhDetails.setLineWrap(true);
		chPhDetails.setWrapStyleWord(true);

		JLabel labelDetails = new JLabel();
		labelDetails.setText("Enter character description here :");
		labelDetails.setBounds(10, 300, 300, 30);

		JTextArea chDetails = new JTextArea();
		chDetails.setBounds(210, 310, 250, 300);
		chDetails.setText(chDet);
		chDetails.setLineWrap(true);
		chDetails.setWrapStyleWord(true);

		JButton buttonSubmit = new JButton("Submit");
		buttonSubmit.setBounds(80, 630, 140, 40);

		JButton buttonClose = new JButton("Close");
		buttonClose.setBounds(230, 630, 140, 40);

		frame.add(chName);
		frame.add(labelName);
		frame.add(chDetails);
		frame.add(labelDetails);
		frame.add(chPhDetails);
		frame.add(labelPhDetails);
		frame.add(buttonSubmit);
		frame.add(buttonClose);
		frame.setSize(500, 730);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				characterName = chName.getText();
				characterDetails = "Physical description :" + chPhDetails.getText() + "█Character description :" + chDetails.getText();
				if (!editing) {
					DataSingleton.getInstance().getCrudObj().newCharacter(characterName, characterDetails);
				} else {
					DataSingleton.getInstance().getCrudObj().updateCharacter(characterName, characterDetails);
				}

			}
		});

		buttonClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				support.firePropertyChange("refresh", true, false);
				frame.dispose();
			}
		});
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getCharacterDetails() {
		return characterDetails;
	}

	public void setCharacterDetails(String characterDetails) {
		this.characterDetails = characterDetails.replace("\\\n", "\\n");
	}
}
