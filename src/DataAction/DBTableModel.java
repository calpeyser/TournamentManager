package DataAction;

import java.lang.reflect.Field;
import java.util.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.*;
import javax.swing.table.AbstractTableModel;

import Base.*;
import Data.*;

/**
 * 
 * This class is meant to populate a JTable with a database table
 *
 */
public class DBTableModel extends AbstractTableModel {

	private TournamentDataStore db;
	private Class<? extends Record> recordType;
	
	public List<?> data;
	
	public DBTableModel(TournamentDataStore db, Class<? extends Record> recordType) {
		this.db = db;
		this.recordType = recordType;
		refresh();
	}
	
	public void refresh() {
		TypedQuery<?> q = db.getEntityManager().createQuery(
				"SELECT c FROM " + recordType.getSimpleName() + " c", recordType);
		data = q.getResultList();
	}
	
	@Override
	public int getColumnCount() {
		return recordType.getFields().length;
	}

	@Override
	public int getRowCount() {
		CriteriaBuilder cb = db.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(recordType)));
		return db.getEntityManager().createQuery(cq).getSingleResult().intValue();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object row = data.get(rowIndex);
		Field f = recordType.getFields()[columnIndex];
		try {
			return f.get(row);
		} catch (Exception e) {
			throw new RuntimeException("Table population failed");
		}
	}
	
	@Override
	public String getColumnName(int index) {
		return recordType.getFields()[index].getName();
	}
	
	@Override
	public void fireTableDataChanged() {
		refresh();
		super.fireTableDataChanged();
	}

}
