package Ruleset;

import java.lang.reflect.Field;
import java.util.List;

import Base.ConfigType;
import Base.Configurable;

public abstract class AutomaticConfigurator extends Configurator {
	
	public AutomaticConfigurator(Configurable conf) {
		super(conf);
	}
	
	@Override
	protected List<Field> getFields() {
		return this.getFieldsOfType(ConfigType.AUTOMATIC);
	}
}
