package Model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Base.Record;
import DataAction.UIConfigAction;

public class ConfigureTeam extends UIConfigAction {

	@Override
	protected Record getRecord() {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MockTeam> cq = cb.createQuery(MockTeam.class);
		Root<MockTeam> root = cq.from(MockTeam.class);
		cq.select(root);
		return db.getEntityManager().createQuery(cq).getResultList().get(0);
	}

	@Override
	protected Class<?> getRecordType() {
		return MockTeam.class;
	}

	@Override
	protected String[] getAttributesToConfigure() {
		String[] out = {"name", "players"};
		return out;
	}

	@Override
	public String description() {
		return "Configure some MockTeam instance";
	}

}
