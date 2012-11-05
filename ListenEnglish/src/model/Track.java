package model;

public class Track {
	private int id;
	private int length;
	private String audioFile;
	private String scriptFile;
	private String suggest;
	
	public Track(int id, int length, String audioFile, String scriptFile, String suggest){
		this.id =id;
		this.length = length;
		this.audioFile = audioFile;
		this.scriptFile = scriptFile;
		this.suggest = suggest;
	}
	
	public int getID(){
		return id;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getLength(){
		return length;
	}
	
	public void setLength(int length){
		this.length = length;
	}
	
	public String getAudioFile(){
		return this.audioFile;
	}
	
	public void setAudioFile(String audioFile){
		this.audioFile = audioFile;
	}
	
	public String getScriptFile(){
		return this.scriptFile;
	}
	
	public void setScriptFile(String scriptFile){
		this.scriptFile = scriptFile;
	}
	
	public String getSuggest(){
		return this.suggest;
	}
	
	public void setSuggest(String suggest){
		this.suggest = suggest;
	}
	
	public String toString(){
		return "id : " + id + " length : " + length + " audioFile :  "
					       + audioFile + " script : " + scriptFile + " suggest : " + suggest;
	}
}
