package model;

import javax.sound.midi.Track;

import Utility.Utility;

public class Lesson {
	private int id;
	private String name;
	private int level;
	private Tracks[] listTrack;
	private int length;
	
	public Lesson(int id, String name, int level, Tracks[] listTrack){
		this.id = id;
		this.name = name;
		this.level = level;
		this.listTrack = listTrack;
		this.length = 0;
		
		for(int i =0; i < listTrack.length; i++)
			length += listTrack[i].getLength();
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public Tracks[] getTrack(){
		return this.listTrack;
	}
	
	public int getLength(){
		return listTrack.length;
	}
	
	public String toString(){
		// tinh thoi gian toan bai nghe
		int length = 0;
		for(int i =0; i < listTrack.length; i++)
			length += listTrack[i].getLength();

		return String.format("   %-50s%s", name, Utility.convertToTime(length / 1000));
	}
}
