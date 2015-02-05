package Model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Base.Record;
import DataAction.UIConfigAction;

public class ConfigureMatch extends UIConfigAction {

	@Override
	protected Record getRecord() {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MockMatch> cq = cb.createQuery(MockMatch.class);
		Root<MockMatch> root = cq.from(MockMatch.class);
		cq.select(root);
		return db.getEntityManager().createQuery(cq).getResultList().get(0);
	}

	@Override
	protected Class<?> getRecordType() {
		return MockMatch.class;
	}

	@Override
	protected String[] getAttributesToConfigure() {
		String[] out = {"name", "player1", "player2"};
		return out;
	}

	@Override
	public String description() {
		return "Configure some MockMatch";
	}

}
