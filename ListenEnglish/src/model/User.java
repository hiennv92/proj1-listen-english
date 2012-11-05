package model;

public class User {
	private int id;
	private String password;
	private String username;
	
	public User(int userID, String userPass, String userName){
		this.id = userID;
		this.password = userPass;
		this.username = userName;
	}
	
	public int getID(){
		return id;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String toString(){
		return "userID : " + id + " userName : " + username + " userPass : " + password;
	}
}
