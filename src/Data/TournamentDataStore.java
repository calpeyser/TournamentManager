package Data;

import Base.*;

import java.util.List;

import javax.persistence.EntityManager;

public interface TournamentDataStore {
	
	public EntityManager getEntityManager();
	
	public void wipeDataStore();

	public TournamentContext getContext();
	
	public void setContext(TournamentContext context);

	/*
	 * One idea is to have the DataStore expose query methods.  For now,
	 * we're going to go with the DataStore exposing the entire EM
	public void add(Record e);
	
	public void delete(Record e);
		
	public <T> List<T> getAll(Class<T> type);
	
	
	*/
}
