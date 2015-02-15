package MockTrial;

import Base.Record;
import Data.TournamentDataStore;
import DataAction.DefaultOptionsModel;

import java.lang.reflect.Field;
import java.util.*;

import javax.persistence.criteria.*;

public class MatchOptionsModel extends DefaultOptionsModel {

	private Set<String> rankingFields;
	
	public MatchOptionsModel(Class<? extends Record> recordType,
			TournamentDataStore db) {
		super(recordType, db);
		rankingFields = new HashSet<String>();
		rankingFields.add("Att1");
		rankingFields.add("Att2");
		rankingFields.add("Att3");
		rankingFields.add("Att4");
		rankingFields.add("Wit1");
		rankingFields.add("Wit2");
		rankingFields.add("Wit3");
		rankingFields.add("Wit4");
	}
	
	@Override 
	public List<?> options(String fieldName, Record instance){
		Field f = null;
		try {
			f = recordType.getField(fieldName);
		} catch (Exception e) {
			throw new IllegalArgumentException(fieldName + " does not exit in " + recordType.toString());
		}
		Class<?> fieldType = f.getType();
		if (fieldType == int.class) {
			List<Object> options = new ArrayList<Object>();
			for (int i = 0; i <= 10; i++) {
				options.add(i);
			}
			return options;
		}
		else if (defaultTypes.contains(fieldType)) {
			return super.options(fieldName, instance);
		}
		else if (rankingFields.contains(fieldName)) {
			Match currentMatch = (Match)instance;
			List<Player> options = new ArrayList<Player>();
			for (Player p : currentMatch.PTeam.players) {
				options.add(p);
			}
			for (Player p : currentMatch.DTeam.players) {
				options.add(p);
			}
			return options;
		}
		else {
			return super.options(fieldName, instance);
		}
		
	}
	
	@Override
	public String toString() {
		return "MatchOptionsModel";
	}

}
