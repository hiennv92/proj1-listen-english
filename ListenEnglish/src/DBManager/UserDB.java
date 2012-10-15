package DBManager;

import java.sql.ResultSet;
import java.sql.Statement;

import model.ConnectDB;
import model.Tracks;
import model.Users;

public class UserDB {
	public static Users getUserByID(int id){
		Users user = null;
		
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			String query = String.format("SELECT * FROM user WHERE id = %d", id);
			ResultSet results = state.executeQuery(query);
			
			if(results.next()){
				int _id = results.getInt(1);
				String _password = results.getString(2);
				String _username = results.getString(3);
				
				user = new Users(_id, _password, _username);
			}
		} catch (Exception e){
			e.printStackTrace();
			// return null
			return user;
		}
		
		return user;
	}
	
	public static Users getUserByName(String name){
		Users user = null;
		
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			String query = String.format("SELECT * FROM user WHERE username = \'%s\'", name);
			ResultSet results = state.executeQuery(query);
			
			if(results.next()){				
				int _id = results.getInt(1);
				String _password = results.getString(2);
				String _username = results.getString(3);
				
				user = new Users(_id, _password, _username);
			}
		} catch (Exception e){
			e.printStackTrace();
			// return null
			return user;
		}
		
		return user;
	}
	
	public static boolean insertUser(Users user){
		boolean ok = false;
		
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			String insert = String.format("INSERT INTO user (password, username) " +
										  "VALUES (\'%s\', \'%s\')",
										  user.getPassword(), user.getUsername());
			
			int num = state.executeUpdate(insert);
			
			ok = num > 0 ? true : false;
		} catch (Exception e){
			e.printStackTrace();
			
		}
		
		return ok;
	}
	
	public static boolean updateUser(Users old, Users last){
		boolean ok = false;
		
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			String update = String.format("UPDATE user " +
										  "SET password = \'%s\', username = \'%s\' " +
										  "WHERE id = %d",
										  last.getPassword(), last.getUsername(), old.getID());
			
			int num = state.executeUpdate(update);
			
			ok = num > 0 ? true : false;
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return ok;
	}
}
