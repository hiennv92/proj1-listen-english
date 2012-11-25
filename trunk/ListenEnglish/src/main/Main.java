package main;

import javax.swing.SwingUtilities;

import model.db.ConnectDB;
import view.ConfigDlg;
import view.MainUI;


public class Main {

	/**
	 * @param args
	 */	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ConnectDB.readConfig();
				if(!ConnectDB.connect())
					(new ConfigDlg()).setVisible(true);
				else 
					(new MainUI()).setVisible(true);
			}
		});
		
	}

}