package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
	private static Connection conn;
	private static String user = null;
	private static String database = null;
	private static String password = null;
	private static String host = null;
	private static String port = null;
	
	// them phan initInfor
	public static void initInfor(String _user, String _password, String _host, String _port, String _database){
		user = _user;
		password = _password;
		host = _host;
		port = _port;
		database = _database;
	}
	
	public static boolean connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	public static boolean closeConnect(){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
	public static Connection getConnect(){
		return conn;
	}
	
	public static String getUser(){
		return user;
	}
	
	public static void setUser(String _user){
		user = _user;
	}
	
	public static String getDatabase(){
		return database;
	}
	
	public static void setDatabase(String _database){
		database = _database;
	}
	
	public static String getPassword(){
		return password;
	}
	
	public static void setPassword(String _password){
		password = _password;
	}
	
	public static String getHost(){
		return host;
	}
	
	public static void setHost(String _host){
		host = _host;
	}
	
	public static String getPort(){
		return port;
	}
	
	public static void setPort(String _port){
		port = _port;
	}
}
