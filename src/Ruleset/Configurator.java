package Ruleset;

import Base.*;

import java.util.*;
import java.lang.reflect.*;

public abstract class Configurator {
	
	protected Object conf;

	/** Reflectively determines all fields in conf which are of given ConfigType */
	protected List<Field> getFieldsOfType(ConfigType type) {
		Field[] fields = conf.getClass().getFields();
		List<Field> fieldsInQuestion = new LinkedList<Field>();
		
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isAnnotationPresent(ConfigField.class)) {
				ConfigField annotation = fields[i].getAnnotation(ConfigField.class);
				if (annotation.type() == type) {
					fieldsInQuestion.add(fields[i]);
				}
			}
		}
		return fieldsInQuestion;
	}
	
	/** Overload by specifying getFieldsOfType */
	protected abstract List<Field> getFields();
	
	public abstract void configure();
	
	public Configurator(Object conf) {
		this.conf = conf;
	}
}
