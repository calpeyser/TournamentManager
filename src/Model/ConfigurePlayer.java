package Model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;
import javax.persistence.criteria.*;

import Base.Record;
import DataAction.UIConfigAction;

public class ConfigurePlayer extends UIConfigAction {

	@Override
	protected Record getRecord() {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MockPlayer> cq = cb.createQuery(MockPlayer.class);
		Root<MockPlayer> root = cq.from(MockPlayer.class);
		cq.select(root);
		return db.getEntityManager().createQuery(cq).getResultList().get(0);
	}
	
	@Override
	protected Class<?> getRecordType() {
		return MockPlayer.class;
	}
	
	@Override
	protected String[] getAttributesToConfigure() {
		String[] out = {"name", "score"};
		
		return out;
	}

	// unclear if this even needs to be in this class
	@Override
	public void execute() {
		return;  
	}

	@Override
	public String description() {
		return "Configure some arbitrary player instance";
	}


}
