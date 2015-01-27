package Data;


import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.*;

import Model.*;

public class DataTest {

	public static void main(String[] args) {

		TournamentDataStore data = new ObjectDBDataStore("$objectdb/db/DTestEclipse.odb");

		// lets make some records
		MockPlayer[] players = {
				new MockPlayer("Cal"),
				new MockPlayer("Gabe"),
				new MockPlayer("Rebecca"),
				new MockPlayer("Mom"),
				new MockPlayer("Dad"),
		};
	
		for (MockPlayer p : players) {
			data.getEntityManager().getTransaction().begin();
			data.getEntityManager().persist(p);
			data.getEntityManager().getTransaction().commit();
		}

		CriteriaBuilder cb = data.getEntityManager().getCriteriaBuilder();
		
		// ----- Trying some update stuff -------
		CriteriaQuery<MockPlayer> cq2 = cb.createQuery(MockPlayer.class);
		Root<MockPlayer> root2 = cq2.from(MockPlayer.class);
		cq2.select(root2);
		cq2.where(cb.equal(root2.get("name"), "Cal"));
		MockPlayer cal = data.getEntityManager().createQuery(cq2).getResultList().get(0);
		
		data.getEntityManager().getTransaction().begin();
		cal.score = 10;
		data.getEntityManager().getTransaction().commit();
		
		// lets get those records
		CriteriaQuery<MockPlayer> cq = cb.createQuery(MockPlayer.class);
		Root<MockPlayer> root = cq.from(MockPlayer.class);
		cq.select(root);
		
		TypedQuery<MockPlayer> query = data.getEntityManager().createQuery(cq);
		List<MockPlayer> out = query.getResultList();
		
		for (MockPlayer p : out) {
			System.out.println(p);
		}
				
		data.wipeDataStore();
		System.out.println("Done!");
	}
}
