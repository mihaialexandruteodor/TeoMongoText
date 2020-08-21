package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import utils.DataSingleton;

public class NewTextFileWindow {

	private final PropertyChangeSupport support = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	public NewTextFileWindow() {
		JFrame frame = new JFrame("Create a new file! ");

		JLabel labelName = new JLabel();
		labelName.setText("Enter file name here :");
		labelName.setBounds(10, 10, 200, 100);

		JTextField fileName = new JTextField();
		fileName.setBounds(150, 50, 230, 30);

		JButton buttonSubmit = new JButton("Submit");
		buttonSubmit.setBounds(50, 100, 140, 40);

		JButton buttonClose = new JButton("Close");
		buttonClose.setBounds(200, 100, 140, 40);

		frame.add(fileName);
		frame.add(labelName);
		frame.add(buttonSubmit);
		frame.add(buttonClose);
		frame.setSize(400, 200);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				DataSingleton.getInstance().getCrudObj().newTextFile(fileName.getText());

				try {
					DataSingleton.getInstance().getMongoDbConnector().connectToDatabase();

					DataSingleton.getInstance().getCrudObj().readDB();

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
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

}
