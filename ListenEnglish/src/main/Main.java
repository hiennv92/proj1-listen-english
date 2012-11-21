package Main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.DBManager.ConnectDB;


import view.ConfigDlg;
import view.MainUI;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectDB.initInfor("root", "12345", "127.0.0.1", "3306", "listeningenglish");
		if(!ConnectDB.connect())
			(new ConfigDlg()).show();
		else 
			(new MainUI()).setVisible(true);
	}

} 
