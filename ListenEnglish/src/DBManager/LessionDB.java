package DBManager;

import java.sql.ResultSet;
import java.sql.Statement;

import model.ConnectDB;
import model.Lessions;
import model.Tracks;

public class LessionDB {
	public static Lessions getLessionByID(int id){
		Lessions lession = null;
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			String query = String.format("SELECT * FROM lession WHERE id = %d", id);
			ResultSet results = state.executeQuery(query);
			
			if(results.next()){
				
				int _id = results.getInt(1);
				String _name = results.getString(2);
				int _level = results.getInt(3);
				Tracks[] _tracks = TrackDB.getTrackByLessionID(id);
				
				lession = new Lessions(_id, _name, _level, _tracks);
			}
		} catch (Exception e){
			e.printStackTrace();
			// return null
			return null;
		}
		
		return lession;
	}
	
	public static Lessions[] getLessionByLevel(int level){
		Lessions lessions[] = null;
		Lessions lession = null;
		try{
			Statement state = ConnectDB.getConnect().createStatement();
			String qCount = String.format("SELECT COUNT(*) FROM lession WHERE level = %d", level);
			String query =  String.format("SELECT * FROM lession WHERE level = %d", level);
			ResultSet results = state.executeQuery(qCount);
			
			results.next();
			int count = results.getInt(1);
			
			if(count == 0)
				return null;
			lessions = new Lessions[count];
			
			results = state.executeQuery(query);
			
			int i = 0;
			while(results.next()){
				
				int _id = results.getInt(1);
				String _name = results.getString(2);
				int _level = results.getInt(3);
				Tracks[] _tracks = TrackDB.getTrackByLessionID(_id);
				
				lession = new Lessions(_id, _name, _level, _tracks);
				
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
