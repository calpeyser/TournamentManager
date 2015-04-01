package Visibles;

import javax.swing.JComponent;

import Data.TournamentDataStore;

public abstract class Visible {
	
	protected TournamentDataStore db;
	
	public abstract String getName();
	
	public abstract JComponent getComponent();
		
	public void bind(TournamentDataStore db) {
		this.db = db;
	}
}
