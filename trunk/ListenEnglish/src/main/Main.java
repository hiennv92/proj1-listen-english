package main;

import model.DBManager.ConnectDB;
import view.ConfigDlg;
import view.MainUI;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectDB.initInfor("root", "hn2221992", "127.0.0.1", "3306", "listeningenglish");
		if(!ConnectDB.connect())
			(new ConfigDlg()).setVisible(true);
		else 
			(new MainUI()).setVisible(true);
	}

} 
