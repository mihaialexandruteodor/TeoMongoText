package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.ini4j.Wini;

import utils.DataSingleton;

public class ConnectionStringInputWindow {

	private final PropertyChangeSupport support = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	public ConnectionStringInputWindow() {
		JFrame frame = new JFrame("MongoDB connection string");
		JButton buttonSubmit = new JButton("Submit");
		buttonSubmit.setBounds(100, 100, 140, 40);

		JButton buttonClose = new JButton("Close");
		buttonClose.setBounds(300, 100, 140, 40);

		JLabel label = new JLabel();
		label.setText("Enter string here :");
		label.setBounds(10, 10, 200, 100);
		JLabel label1 = new JLabel();
		label1.setBounds(10, 110, 400, 100);
		JTextField textfield = new JTextField();
		textfield.setBounds(130, 50, 300, 30);

		frame.add(label1);
		frame.add(textfield);
		frame.add(label);
		frame.add(buttonSubmit);
		frame.add(buttonClose);
		frame.setSize(500, 250);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DataSingleton.getInstance().setConnectionString(textfield.getText());

				Wini ini = null;
				try {
					ini = new Wini(new File(DataSingleton.getInstance().getIniFilePath()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ini.put("database", "connstr", DataSingleton.getInstance().getConnectionString());
				try {
					ini.store();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				label1.setText("String has been submitted and saved inside the Settings.ini file");

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
