package Data;

import Base.*;

import java.awt.Window;
import java.util.List;

import javax.persistence.EntityManager;

public interface TournamentDataStore {
	
	public EntityManager getEntityManager();
	
	public void wipeDataStore();

	public TournamentContext getContext();
	
	public void setContext(TournamentContext context);
	
	public void save();
	
	public void save(String filename, Window callingWindow);
	
	public void pivot();
	
	public void cleanFiles();

}
