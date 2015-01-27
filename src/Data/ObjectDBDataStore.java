package Data;


import javax.persistence.*;

/**
 * 
 * What seems to be a textbook implementation of TournamentDataStore.  
 * Uses java's basic Persistence facilities.  ObjectDB is something
 * I found on the internet. 
 * 
 * NOTE: ObjectDB dosen't seem to support JPA 2.1, which I need.  That's a problem.
 *
 */
public class ObjectDBDataStore implements TournamentDataStore {
	private EntityManagerFactory emf;
	private EntityManager em;
	private TournamentContext context;
	
	public ObjectDBDataStore(String location) {
		emf = Persistence.createEntityManagerFactory(location);
		em = emf.createEntityManager();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
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
