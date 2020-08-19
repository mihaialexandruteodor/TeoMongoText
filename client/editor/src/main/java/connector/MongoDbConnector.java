package connector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClients;

import utils.DataSingleton;

import com.mongodb.client.MongoClient;

public class MongoDbConnector {

	public boolean connectToDatabase() {
		
		boolean success = true;
		
		MongoClient mongoClient = null;
		

		try {
			ConnectionString str = new ConnectionString(DataSingleton.getInstance().getConnectionString());
			 mongoClient = MongoClients.create(str);
		}
		catch(IllegalArgumentException exc)
		{
			displayErrorWindow();
			exc.printStackTrace();
			success = false;
			
		}
		
		if(success) {

		try {
			DataSingleton.getInstance().setMongoClient(mongoClient);	
		}
		catch(MongoException e)
		{
			displayErrorWindow();
			e.printStackTrace();
			success = false;
			
		}
		}
		
		if(success) {
		try {
			DataSingleton.getInstance().setDatabase(DataSingleton.getInstance().getMongoClient().getDatabase("teomongotext"));
		}
		catch(IllegalArgumentException ex)
		{
			displayErrorWindow();
			ex.printStackTrace();
			success = false;
		}
		}

		return success;
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

	public void init() {
		if(connectToDatabase())
		{
			DataSingleton.getInstance().getCrudObj().readDB();
			
			DataSingleton.getInstance().getGuiController().loadDataIntoLists();
		}
		
	}

}
