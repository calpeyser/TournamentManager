package PreConfig;

import java.lang.reflect.Field;
import java.util.List;

import Base.ConfigType;
import Ruleset.*;

public abstract class PreConfigurator extends Configurator {

	public PreConfigurator(Object conf) {
		super(conf);
	}
	
	@Override
	protected List<Field> getFields() {
		return this.getFieldsOfType(ConfigType.PRECONFIG);
	}
}
