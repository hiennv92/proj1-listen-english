package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	private Connection connect;
	private Statement state;
	private Statement state_2;
	private ResultSet result;
	private ResultSet result_2;
	
	private String url = "jdbc:mysql://127.0.0.1:3306/mysql";
	private String userDB = "root";
	private String passDB = "12345";
	
	public DBManager(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, userDB, passDB);
			state = connect.createStatement();
			state.executeQuery("USE listeningenglish");
			state_2 = connect.createStatement();
			state_2.executeQuery("USE listeningenglish");
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	// tra ve danh sach cac user
	public User[] getListUser(){
		User[] list = null;
		try {
			result = state.executeQuery("SELECT COUNT(*) FROM user");
			result.next();
			list = new User[result.getInt(1)];
			
			result = state.executeQuery("SELECT * FROM user");
			int i = 0;
			while(result.next()){
				list[i] = new User(result.getInt(1), result.getString(2), result.getString(3));
				i++;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	// tra ve xem mot user co trong database khong
	// neu co tra ve mot doi tuong user luu tru thong tin : userID, pass, nam
	// neu khong ton tai thi tra ve mot null
	public User userAvaiable(String userName){
		User user = null;
		try{
			result = state.executeQuery("SELECT * FROM user WHERE username = '" + userName + "'");
			while(result.next()){
				user = new User(result.getInt(1), result.getString(2), result.getString(3));
			}
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		return user;
	}
	
	// Lay ve danh sach cac Lession theo level
	public Lession[] getLessionByLevel(int level){
		Lession[] listLession = null;
		int i, j;
		
		try{
			result = state.executeQuery("SELECT COUNT(*) FROM Lession WHERE level = '" + level +"'");
			result.next();
			listLession = new Lession[result.getInt(1)];
			
			result = state.executeQuery("SELECT * FROM Lession WHERE level = '" + level +"'");
			
			i = 0;
			while(result.next()){
				result_2 = state_2.executeQuery("SELECT COUNT(*) FROM Track WHERE lessID = '" + result.getInt(1) + "'");
				result_2.next();
				Track[] listTrack = null;
				listTrack = new Track[result_2.getInt(1)];
				
				result_2 = state_2.executeQuery("SELECT * FROM Track WHERE lessID = '" + result.getInt(1) + "'");
				
				j = 0;
				while(result_2.next()){
					listTrack[j] = new Track(result_2.getInt(1), result_2.getInt(2), result_2.getString(3), result_2.getString(4), result_2.getString(5));
					j++;
				}
				
				listLession[i] = new Lession(result.getInt(1), result.getString(2), result.getInt(3), listTrack);
				i++;
			}
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return listLession;
	}																													

	// Lay ve danh sach diem so cua nguoi choi
	public String[] getTopScoreByUser(int userId){
		String[] top = null;
		
		try {
			result = state.executeQuery("SELECT COUNT(*) " +
										"FROM listen " +
										"WHERE userID = '" + userId + "'");
			
			result.next();
			if(result.getInt(1) != 0){
				top = new String[result.getInt(1)];
				top = new String[result.getInt(1) + 2];
				top[0] = String.format("  %-22s %s", "Date",  "Score");
				top[1] = "------------------------------";
				
				result = state.executeQuery("SELECT * " +
											"FROM listen " +
											"WHERE userID = ' " + userId + "' " +
											"ORDER BY Score DESC");
				
				int i = 2;
				while(result.next()){
					top[i] = String.format("  %-22s %s",result.getString(4), result.getString(5));
					i++;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return top;
	}
	
	public String[] getTopScore(){
		String[] top = null;
		
		try {
			result = state.executeQuery("SELECT COUNT(*) " +
										"FROM listen");
			result.next();
			if(result.getInt(1) != 0){
				top = new String[result.getInt(1) + 2];
				top[0] = String.format("  %-10s %-12s %s", "User",  "Date",  "Score");
				top[1] = "--------------------------------";
				result = state.executeQuery("SELECT user.username, listen.Time, listen.Score " + 
											"FROM user " +
											"INNER JOIN listen " +
											"ON user.id = listen.userID " +
											"ORDER BY listen.Score DESC");
				
				int i = 2;
				while(result.next()){
					top[i] = String.format("  %-10s %-12s %s", result.getString(1),  result.getString(2),  result.getString(3));
					i++;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return top;
	}

	public void insertUser(String userName, String pass){
		try{
			state.executeUpdate("INSERT INTO user (username, password) " +
								"VALUES ('" + userName + "','" + pass + "')");
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void insertScore(int userID, int lessID, int score){
		try{
			state.executeUpdate("INSERT INTO listen (userID, lessID, Time, Score) " +
								"VALUES ('" + userID + "','" + lessID + "', CURRENT_DATE(), '" + score +"')");
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
