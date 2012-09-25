package model;

public class Track {
	public int id;
	public int length;
	public String audioFile;
	public String scriptFile;
	public String suggest;
	
	public Track(int id, int length, String audioFile, String scriptFile, String suggest){
		this.id =id;
		this.length = length;
		this.audioFile = audioFile;
		this.scriptFile = scriptFile;
		this.suggest = suggest;
	}
	
	public void display(){
		System.out.println("id : " + id + " length : " + length + " audioFile :  "
					       + audioFile + " script : " + scriptFile + " suggest : " + suggest);
	}
}
