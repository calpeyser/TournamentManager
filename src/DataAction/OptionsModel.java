package DataAction;

import Base.Record;
import Data.*;

import java.util.*;
/**
 * Encapsulates data for the options that various database fields may contain
 * @author Cal Peyser
 *
 */
public abstract class OptionsModel {

	protected Class<? extends Record> recordType;
	protected TournamentDataStore db;
	
	public OptionsModel(Class<? extends Record> recordType, TournamentDataStore db) {
		this.recordType = recordType;
		this.db = db;
	}
	
	/**
	 * Gives the possibilities for this attribute.  A null output gives will be interpreted as 
	 * the default set of possibilities.
	 * @param attributeName
	 * @return
	 */
	public abstract List<?> options(String attributeName, Record instance); 
	
}
