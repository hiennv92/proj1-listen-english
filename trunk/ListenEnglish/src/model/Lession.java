package model;

public class Lession {
	public int id;
	public String name;
	public int level;
	public Track[] listTrack;
	
	public Lession(int id, String name, int level, Track[] listTrack){
		this.id = id;
		this.name = name;
		this.level = level;
		this.listTrack = listTrack;
	}
	
	public void display(){
		System.out.println("id : " + id + " name : " + name + " level : " + level);
		System.out.println("    Co cac track la : ");
		for(int i = 0; i < listTrack.length; i++){
			System.out.print("        ");
			listTrack[i].display();
		}
	}
	
	public String toString(){
		// tinh thoi gian toan bai nghe
		int length = 0;
		for(int i =0; i < listTrack.length; i++)
			length += listTrack[i].length;

		return String.format("   %-50s%-3d (s)", name, length);
	}
}
