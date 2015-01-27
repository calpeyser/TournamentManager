package Base;

public class Tournament implements Configurable {
	@ConfigField(name = "Tournament Name", type = ConfigType.PRECONFIG)
	public String name;
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return ("Tournament " + name);
	}
}
