package Data;


import java.awt.Window;
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
import javax.swing.JOptionPane;

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
		
	private String activeLocation() {
		String p = emf.getProperties().get("objectdb.connection.path").toString();
		return p;
	}
	
	private String activeLocationWithoutSuffix() {
		String p = activeLocation();
		return p.substring(0, p.lastIndexOf('.'));

	}
	
	public ObjectDBDataStore(String location) {
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
		
		String newPath = activeLocation();
		newPath = newPath.substring(0, newPath.lastIndexOf('.'));
		// find the next number
		int i = 1;
		while(true) {
			String canditateLocation = newPath + "_" + compressString(context.getCurrentState().getName())+ "_" + i + ".odb";
			File f = new File(canditateLocation);
			if (f.exists()) {
				i++;
				continue;
			}
			else {
				Path source = Paths.get(activeLocation());
				Path destination = Paths.get(canditateLocation);
				try {
					emf.close();
					Files.copy(source, destination);
					emf = Persistence.createEntityManagerFactory(activeLocation());
					em = emf.createEntityManager();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	@Override 
	public void save(String filename, Window callingWindow) {
				
		// make sure this isn't a duplicate name
		if (new File(activeLocationWithoutSuffix() + filename + ".odb").exists()) {
			JOptionPane.showMessageDialog(callingWindow, "Save corresponding to the name " + filename + " already exists, please choose another.");
			return;
		}
		
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
		
		// find the next number
		
		String newlocation = activeLocationWithoutSuffix() + filename + ".odb";
		Path source = Paths.get(activeLocation());
		Path destination = Paths.get(newlocation);

		try {
			emf.close();
			Files.copy(source, destination);
			emf = Persistence.createEntityManagerFactory(activeLocation());
			em = emf.createEntityManager();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	// Save the current database, and use a copy called "Database.odb"
	@Override 
	public void pivot() {
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
		
		String newPath = activeLocation();
		newPath = newPath.substring(0, newPath.lastIndexOf('\\'));
		
		String newLocation =  newPath + "\\Database.odb";
		Path source = Paths.get(activeLocation());
		Path destination = Paths.get(newLocation);
		try {
			emf.close();
			Files.copy(source, destination);
			emf = Persistence.createEntityManagerFactory(newLocation);
			em = emf.createEntityManager();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	@Override 
	public void cleanFiles() {
		emf.close();
		String fileString = (String) emf.getProperties().get("objectdb.connection.path");
		Path filePath = Paths.get(fileString);
		try {
			Files.delete(filePath);
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
	
	private static String compressString(String s) {
		StringBuilder out = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (c != ' ') {
				out.append(c);
			}
		}
		return out.toString();
	}
}
