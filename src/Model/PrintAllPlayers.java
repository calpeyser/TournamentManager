package Model;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Data.ObjectDBDataStore;
import Data.TournamentDataStore;
import DataAction.*;

public class PrintAllPlayers extends AutomaticDataAction {


	@Override
	public void execute() {
		assertBound();
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MockPlayer> cq = cb.createQuery(MockPlayer.class);
		Root<MockPlayer> root = cq.from(MockPlayer.class);
		cq.select(root);
		
		TypedQuery<MockPlayer> query = db.getEntityManager().createQuery(cq);
		List<MockPlayer> out = query.getResultList();
		
		for (MockPlayer p : out) {
			System.out.println(p);
		}
	}

	public static void main(String[] args) {
		TournamentDataStore db = new ObjectDBDataStore("$objectdb/db/DTest.odb");
		DataAction make = new MakePlayers(db);
		DataAction add = new AddPointToAllPlayers(db);
		DataAction print = new PrintAllPlayers(db);
		
		make.execute();
		add.execute();
		add.execute();
		print.execute();
		
		db.wipeDataStore();
	}
	
}
