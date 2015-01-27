package DataAction;

import Data.TournamentDataStore;

public abstract class DynamicDataAction extends DataAction {
	public abstract String description();
	
	@Override
	public String toString() {
		return description();
	}
}
