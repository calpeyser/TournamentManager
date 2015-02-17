package DataAction;

public abstract class DynamicDataAction extends DataAction {
		
	public abstract String description();
	
	// attach the data action to some component in the GUI
	// TODO: Write some kind of abstract method for this
	
	@Override
	public String toString() {
		return description();
	}
}
