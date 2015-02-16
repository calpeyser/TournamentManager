package DataAction;

import Data.TournamentDataStore;

public abstract class DynamicDataAction extends DataAction {
	
	public abstract String description();
	
	public abstract boolean isComplete();
	
	// attach the data action to some component in the GUI
	// TODO: Write some kind of abstract method for this
	
	@Override
	public String toString() {
		return description();
	}
}
