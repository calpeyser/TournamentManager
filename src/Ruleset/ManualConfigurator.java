package Ruleset;

import Base.*;
import java.util.*;
import java.lang.reflect.*;

public abstract class ManualConfigurator extends Configurator {

	public ManualConfigurator(Configurable conf) {
		super(conf);
	}

	public abstract boolean isConfigured();
	
	@Override
	protected List<Field> getFields() {
		return this.getFieldsOfType(ConfigType.MANUAL);
	}
}
