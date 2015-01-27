package DataAction;

import Data.*;

public abstract class DataAction {
	
	protected TournamentDataStore db;
	protected boolean bound;
	
	public DataAction() {
		bound = false;
	}
	
	public void bind(TournamentDataStore db) {
		this.db = db;
		this.bound = true;
	}
	
	public abstract void execute();
	
	protected void assertBound() {
		if (!bound) {
			throw new RuntimeException("DataAction not bound!");
		}
	}
	
}
