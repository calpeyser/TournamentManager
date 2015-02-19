package Ruleset;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Data.ObjectDBDataStore;
import Data.TournamentContext;
import Data.TournamentDataStore;

/**
 * This implementation of TournamentFactory only exposes the method for getting a ruleset.  Otherwise
 * it just uses a vanilla ObjectDB database for the context.
 */
public abstract class DefaultTournamentFactory extends TournamentFactory {

	@Override
	public TournamentDataStore makeNewDB(Ruleset r, String directoryPath) {
		TournamentDataStore db = new ObjectDBDataStore(directoryPath + "/Database.odb");
		db.wipeDataStore();
		db.setContext(new TournamentContext(r.getStartState()));	
				
		return db;
	}

	@Override
	public TournamentDataStore recoverDB(Ruleset r, String filepath) {
		TournamentDataStore db = new ObjectDBDataStore(filepath);
		
		// set up the current state
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<State> cq = cb.createQuery(State.class);
		Root<State> root = cq.from(State.class);
		cq.select(root);
		TypedQuery<State> q = db.getEntityManager().createQuery(cq);
		List<State> states = q.getResultList();
		if (states.size() != 1) {
			throw new RuntimeException("States is of size " + states.size());
		}
		State s = states.get(0);
		TournamentContext context = new TournamentContext(r.stateByName(s.getName()));
		db.setContext(context);
		
		
		
		return db;
	}

}
