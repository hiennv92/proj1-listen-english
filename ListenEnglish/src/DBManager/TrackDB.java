package DBManager;

import java.sql.ResultSet;
import java.sql.Statement;

import model.ConnectDB;
import model.Track;

public class TrackDB {
	public static Track getTrackByID(int id){
		Track track = null;
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			String query = String.format("SELECT * FROM track WHERE id = %d", id);
			ResultSet results = state.executeQuery(query);
			
			if(results.next()){
				int _id = results.getInt(1);
				int _length = results.getInt(2);
				String _audioFile = results.getString(3);
				String _scriptFile = results.getString(4);
				String _suggest = results.getString(6);
				
				track = new Track(_id, _length, _audioFile, _scriptFile, _suggest);
			}
		} catch (Exception e){
			e.printStackTrace();
			// return null
			return track;
		}
		
		return track;
	}
	
	public static Track[] getTrackByLessionID(int lessID){
		Track[] tracks = null;
		Track track;
		
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			String qCount = String.format("SELECT COUNT(id) FROM track WHERE lessID = %d", lessID);
			
			ResultSet results = state.executeQuery(qCount);
			results.next();
			int count = results.getInt(1);
			
			if(count == 0)
				return null;
			
			tracks = new Track[count];
			
			String query = String.format("SELECT * FROM track WHERE lessID = %d", lessID);
			results = state.executeQuery(query);
			
			int i = 0;
			while(results.next()){
				
				int _id = results.getInt(1);
				int _length = results.getInt(2);
				String _audioFile = results.getString(3);
				String _scriptFile = results.getString(4);
				String _suggest = results.getString(6);
				
				track = new Track(_id, _length, _audioFile, _scriptFile, _suggest);
				tracks[i] = track;
				i++;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tracks;
	}
}
