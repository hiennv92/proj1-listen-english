package model.db;

import java.sql.ResultSet;
import java.sql.Statement;

import model.Lesson;
import model.Track;

public class LessonDB {
	public static Lesson getLessonByID(int id){
		Lesson lesson = null;
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			String query = String.format("SELECT * FROM lesson WHERE id = %d", id);
			ResultSet results = state.executeQuery(query);
			
			if(results.next()){
				
				int _id = results.getInt(1);
				String _name = results.getString(2);
				int _level = results.getInt(3);
				Track[] _tracks = TrackDB.getTrackByLessonID(id);
				String _preview = results.getString(4);
				lesson = new Lesson(_id, _name, _level, _tracks, _preview);
			}
		} catch (Exception e){
			//e.printStackTrace();
			// return null
			return null;
		}
		
		return lesson;
	}
	
	public static Lesson[] getLessonByLevel(int level){
		Lesson lessons[] = null;
		Lesson lesson = null;
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			String qCount = String.format("SELECT COUNT(*) FROM lesson WHERE level = %d", level);
			String query =  String.format("SELECT * FROM lesson WHERE level = %d", level);
			ResultSet results = state.executeQuery(qCount);
			
			results.next();
			int count = results.getInt(1);
			
			if(count == 0)
				return null;
			lessons = new Lesson[count];
			
			results = state.executeQuery(query);
			
			int i = 0;
			while(results.next()){
				
				int _id = results.getInt(1);
				String _name = results.getString(2);
				int _level = results.getInt(3);
				Track[] _tracks = TrackDB.getTrackByLessonID(_id);
				String _preview = results.getString(4);
				lesson = new Lesson(_id, _name, _level, _tracks, _preview);
				
				lessons[i] = lesson;
				i++;
			}
		} catch (Exception e){
			
			// return null
			return null;
		}
		
		return lessons;
	}
}
