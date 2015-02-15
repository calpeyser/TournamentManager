package Ruleset;

import java.util.*;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Transient;

import DataAction.*;

@Entity
public class State {
	@Basic private String name;
	@Transient private List<AutomaticDataAction> entryConfig;
	@Transient private List<UIDataAction> duringConfig;
	@Transient private List<AutomaticDataAction> exitConfig;
	
	public State(String name, 
			     List<AutomaticDataAction> entryConfig,
			     List<UIDataAction> duringConfig,
			     List<AutomaticDataAction> exitConfig) {
		this.name = name;
		this.entryConfig = (entryConfig != null) ? entryConfig : new LinkedList<AutomaticDataAction>();
		this.duringConfig = (duringConfig != null) ? duringConfig : new LinkedList<UIDataAction>();
		this.exitConfig = (exitConfig != null) ? exitConfig : new LinkedList<AutomaticDataAction>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<AutomaticDataAction> getEntryConfig() {
		return this.entryConfig;
	}
	
	public List<UIDataAction> getDuringConfig() {
		return this.duringConfig;
	}
	
	public List<AutomaticDataAction> getExitConfig() {
		return this.exitConfig;
	}
}
