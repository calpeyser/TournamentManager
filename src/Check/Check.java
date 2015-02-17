package Check;

import Data.TournamentDataStore;

public abstract class Check {
	protected TournamentDataStore db;
	protected boolean bound;

	public Check() {
		bound = false;
	}
	
	public void bind(TournamentDataStore db) {
		this.db = db;
		this.bound = true;
	}

	protected void assertBound() {
		if (!bound) {
			throw new RuntimeException("Check not bound!");
		}
	}
	
	public abstract void performCheck() throws CheckFailedException;
	
}
