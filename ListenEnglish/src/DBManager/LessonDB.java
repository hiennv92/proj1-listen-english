package DBManager;

import java.sql.ResultSet;
import java.sql.Statement;

import model.ConnectDB;
import model.Lesson;
import model.Track;

public class LessonDB {
	public static Lesson getLessonByID(int id){
		Lesson lession = null;
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			String query = String.format("SELECT * FROM lession WHERE id = %d", id);
			ResultSet results = state.executeQuery(query);
			
			if(results.next()){
				
				int _id = results.getInt(1);
				String _name = results.getString(2);
				int _level = results.getInt(3);
				Track[] _tracks = TrackDB.getTrackByLessionID(id);
				
				lession = new Lesson(_id, _name, _level, _tracks);
			}
		} catch (Exception e){
			e.printStackTrace();
			// return null
			return null;
		}
		
		return lession;
	}
	
	public static Lesson[] getLessonByLevel(int level){
		Lesson lessions[] = null;
		Lesson lession = null;
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			String qCount = String.format("SELECT COUNT(*) FROM lession WHERE level = %d", level);
			String query =  String.format("SELECT * FROM lession WHERE level = %d", level);
			ResultSet results = state.executeQuery(qCount);
			
			results.next();
			int count = results.getInt(1);
			
			if(count == 0)
				return null;
			lessions = new Lesson[count];
			
			results = state.executeQuery(query);
			
			int i = 0;
			while(results.next()){
				
				int _id = results.getInt(1);
				String _name = results.getString(2);
				int _level = results.getInt(3);
				Track[] _tracks = TrackDB.getTrackByLessionID(_id);
				
				lession = new Lesson(_id, _name, _level, _tracks);
				
				lessions[i] = lession;
				i++;
			}
		} catch (Exception e){
			e.printStackTrace();
			// return null
			return null;
		}
		
		return lessions;
	}
}
