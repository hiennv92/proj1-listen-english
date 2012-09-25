package model;

public class User {
	public int userID;
	public String userPass;
	public String userName;
	
	public User(int userID, String userPass, String userName){
		this.userID = userID;
		this.userPass = userPass;
		this.userName = userName;
	}
	
	public void display(){
		System.out.println("userID : " + userID + " userName : " + userName + " userPass : " + userPass);
	}
}
