package Ruleset;

import Data.TournamentDataStore;

public abstract class TournamentFactory {
	public abstract Ruleset makeRuleset();
	public abstract TournamentDataStore makeNewDB(Ruleset r, String directoryPath);
	public abstract TournamentDataStore recoverDB(Ruleset r, String filepath);
}
