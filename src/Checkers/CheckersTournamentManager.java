package Checkers;

import Controller.Control;
import MootCourt.MootCourtTournamentFactory;

public class CheckersTournamentManager {
	public static void main(String[] args) {
		Control.run(new CheckersTournamentFactory());
	}
}
