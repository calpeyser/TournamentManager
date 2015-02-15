package DataAction;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Query;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;

import Utils.MiscUtils;

import com.objectdb.o.HMP.F;

import javax.persistence.metamodel.*;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Base.Record;
import Data.TournamentDataStore;

public class DefaultOptionsModel extends OptionsModel {

	protected final Set<Class<?>> defaultTypes;
	
	public DefaultOptionsModel(Class<? extends Record> recordType, TournamentDataStore db) {
		super(recordType, db);
		defaultTypes = new HashSet<Class<?>>();
		defaultTypes.add(String.class);
		defaultTypes.add(boolean.class);
		defaultTypes.add(int.class);
		defaultTypes.add(double.class);
		defaultTypes.add(float.class);
	}

	@Override
	public List<?> options(String fieldName, Record instance) {
		Field f = null;
		try {
			f = recordType.getField(fieldName);
		} catch (Exception e) {
			throw new IllegalArgumentException(fieldName + " does not exit in " + recordType.toString());
		}
		Class<?> fieldType = f.getType();
		if (defaultTypes.contains(fieldType)) {
			return null;
		}
		else if (fieldType.isAnnotationPresent(Entity.class)) {
			Query q = db.getEntityManager().createQuery("Select c FROM " + fieldType.getSimpleName() + " c");
			List<?> optionsList = q.getResultList();
			return optionsList;
		}
		else if (MiscUtils.isOneToMany(f.getAnnotations())) {
			Class<?> targetEntity = MiscUtils.getInnerType(f.getAnnotations());
			Query q = db.getEntityManager().createQuery("Select c FROM " + targetEntity.getSimpleName() + " c");
			List<?> optionsList = q.getResultList();
			return optionsList;
		}

		else {
			throw new RuntimeException("Cannot parse attribute " + fieldName);
		}

	}
	
	@Override
	public String toString() {
		return "DefaultOptionModel";
	}
}
