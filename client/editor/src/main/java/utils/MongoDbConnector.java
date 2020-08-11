package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

public class MongoDbConnector {

	public boolean connectToDatabase() {
		MongoClientURI uri = null;

		try {
			 uri = new MongoClientURI(DataSingleton.getInstance().getConnectionString());
		}
		catch(IllegalArgumentException exc)
		{
			displayErrorWindow();
			exc.printStackTrace();
			
		}

		try {
			DataSingleton.getInstance().setMongoClient(new MongoClient(uri));	
		}
		catch(MongoException e)
		{
			displayErrorWindow();
			e.printStackTrace();
			
		}
		
		try {
			DataSingleton.getInstance().setDatabase(DataSingleton.getInstance().getMongoClient().getDatabase("teomongotext"));
		}
		catch(IllegalArgumentException ex)
		{
			displayErrorWindow();
			ex.printStackTrace();
		}

		return true;
	}
	
	void displayErrorWindow()
	{
		JFrame frame = new JFrame("Error!");
		JButton buttonClose = new JButton("Close");
		buttonClose.setBounds(170, 70, 140, 40);
		
		JLabel label = new JLabel();
		label.setText("Looks like we couldn't connect to your specified database :(");
		label.setBounds(80, 70, 400, 100);
		
		frame.add(label);
		frame.add(buttonClose);
		frame.setSize(500, 200);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		buttonClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
	}

}
