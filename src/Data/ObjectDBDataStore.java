package Data;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Ruleset.State;

/**
 * 
 * What seems to be a textbook implementation of TournamentDataStore.  
 * Uses java's basic Persistence facilities.  ObjectDB is something
 * I found on the internet. 
 * 
 *
 */
public class ObjectDBDataStore implements TournamentDataStore {
	private EntityManagerFactory emf;
	private EntityManager em;
	private TournamentContext context;
	
	private String location;
	
	public ObjectDBDataStore(String location) {
		this.location = location;
		emf = Persistence.createEntityManagerFactory(location);
		em = emf.createEntityManager();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public void save() {
		// tell the database that we are in a new state
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<State> cq = cb.createQuery(State.class);
		Root<State> r = cq.from(State.class);
		cq.select(r);
		TypedQuery<State> q = em.createQuery(cq);
				
		List<State> oldStates = q.getResultList();
		em.getTransaction().begin();
		for (State s : oldStates) {
			em.remove(s);
		}
		em.persist(context.getCurrentState());
		em.getTransaction().commit();
		
		String newPath = emf.getProperties().get("objectdb.connection.path").toString();
		newPath = newPath.substring(0, newPath.lastIndexOf('.'));
		//newPath += "_" + context.getCurrentState().getName();
		// find the next number
		int i = 1;
		while(true) {
			String canditateLocation = newPath + "_" + i + ".odb";
			File f = new File(canditateLocation);
			if (f.exists()) {
				i++;
				continue;
			}
			else {
				Path source = Paths.get((String)emf.getProperties().get("objectdb.connection.path"));
				Path destination = Paths.get(canditateLocation);
				try {
					emf.close();
					Files.copy(source, destination);
					emf = Persistence.createEntityManagerFactory(location);
					em = emf.createEntityManager();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	@Override 
	public void cleanFiles() {
		String fileString = (String) emf.getProperties().get("objectdb.connection.path");
		Path filePath = Paths.get(fileString);
		try {
			for (Path p : Files.newDirectoryStream(filePath.getParent())) {
				if ((!p.toString().equals(fileString)) && (!p.toString().equals((fileString + "$")))) {
					Files.delete(p);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void wipeDataStore() {
		em.getTransaction().begin();
		Query allQuery = em.createQuery("DELETE FROM Object");
		allQuery.executeUpdate();
		em.getTransaction().commit();
	}
	
	public TournamentContext getContext() {
		return this.context;
	}

	public void setContext(TournamentContext context) {
		this.context = context;
	}

	/*
	public void add(Record e) {
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
	}
	
	public void delete(Record e) {
		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();
	}

	
	public <T> List<T> getAll(Class<T> type) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(type);
		Root<T> c = q.from(type);
		q.select(c);
		TypedQuery<T> query = em.createQuery(q);
		return query.getResultList();
	}
	
	public static void main(String[] args) {
		TournamentDataStore db = new ObjectDBDataStore("$objectdb/db/test1.odb");
		List<MockPlayer> players = db.getAll(MockPlayer.class);
		for (MockPlayer p : players) {
			System.out.println(p);
		}
		
	}

	}
	*/

}
