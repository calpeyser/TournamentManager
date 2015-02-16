package MockTrial;

import Base.Record;
import Data.TournamentDataStore;
import DataAction.DefaultOptionsModel;

import java.lang.reflect.Field;
import java.util.*;

import javax.persistence.criteria.*;

/**
 * Defines field options for a Mock Trial Match
 * @author Cal Peyser
 *
 */
public class MatchOptionsModel extends DefaultOptionsModel {

	private Set<String> rankingFields;
	
	public MatchOptionsModel(Class<? extends Record> recordType,
			TournamentDataStore db) {
		super(recordType, db);
		// these are some of the fields that I want to customize
		rankingFields = new HashSet<String>();
		rankingFields.add("Ballot1Att1");
		rankingFields.add("Ballot1Att2");
		rankingFields.add("Ballot1Att3");
		rankingFields.add("Ballot1Att4");
		rankingFields.add("Ballot1Wit1");
		rankingFields.add("Ballot1Wit2");
		rankingFields.add("Ballot1Wit3");
		rankingFields.add("Ballot1Wit4");
		rankingFields.add("Ballot2Att1");
		rankingFields.add("Ballot2Att2");
		rankingFields.add("Ballot2Att3");
		rankingFields.add("Ballot2Att4");
		rankingFields.add("Ballot2Wit1");
		rankingFields.add("Ballot2Wit2");
		rankingFields.add("Ballot2Wit3");
		rankingFields.add("Ballot2Wit4");
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
		// all integer fields need to be 0 - 10 instead of all integers
		if (fieldType == int.class) {
			List<Object> options = new ArrayList<Object>();
			for (int i = 0; i <= 10; i++) {
				options.add(i);
			}
			return options;
		}
		// defaultTypes is a protected field in the superclass.  It contains primative types.
		// For all defaultTypes other than integers, I want default behavior.
		else if (defaultTypes.contains(fieldType)) {
			return super.options(fieldName, instance);  // invoke default behavior
		}
		else if (rankingFields.contains(fieldName)) {
			// for these specific fields, I only want players that were in the match
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
			return super.options(fieldName, instance);  // invoke default behavior
		}
	}
	
	@Override
	public String toString() {
		return "MatchOptionsModel";
	}
}
