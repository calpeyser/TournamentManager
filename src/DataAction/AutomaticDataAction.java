package DataAction;

import Data.TournamentDataStore;

/** 
 * 
 * Occur automatically, without any interaction from the user.  
 * Constructor calls execute.
 *
 */
public abstract class AutomaticDataAction extends DataAction {

	public abstract void execute();
	
}
