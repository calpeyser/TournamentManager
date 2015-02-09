package Ruleset;

import Data.TournamentDataStore;

public abstract class TournamentFactory {
	public abstract Ruleset makeRuleset();
	public abstract TournamentDataStore makeDB(Ruleset r);
	public abstract TournamentDataStore makeDB(Ruleset r, String filepath);
}
