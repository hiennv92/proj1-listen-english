package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ListenDB {
	public static String[] getTopScoreByUser(int userId){
		String[] top = null;
		
		try {
			Statement state = ConnectDB.getConnect().createStatement();
			ResultSet result;
			
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
			e.printStackTrace();
		}
		
		return top;
	}
	
	public static String[] getTopScore(){
		String[] top = null;
		
		try {
			Statement state = ConnectDB.getConnect().createStatement();
			ResultSet result;
			
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
			e.printStackTrace();
		}
		
		return top;
	}
	
	public static void InsertListenDB(int userId, int lessId, int score){

		try {
			Statement state = ConnectDB.getConnect().createStatement();
			
			String sql = String.format("INSERT INTO listen(userId, lessId, Time, Score) VALUES " +
									   "(%d, %d, CURRENT_DATE(), %d);", 
									   userId, lessId, score);
			
			state.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static int countLesson(int id, int userID){
		int count = 0;
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			
			String qCount = String.format("SELECT COUNT(*) FROM listen WHERE (lessId = %d and userID = %d)", id, userID);
			
			ResultSet result = state.executeQuery(qCount);
			result.next();
			count = result.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return count;
	}
}
