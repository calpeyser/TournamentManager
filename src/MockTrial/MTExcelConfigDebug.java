package MockTrial;

import java.util.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import DataAction.ExcelDataAction;

/**
 * This class is for test cases.  It populates a team, with all of it's optional
 * fields, from an excel spreadsheet.
 * @author Cal Peyser
 *
 */
public class MTExcelConfigDebug extends ExcelDataAction {

	private void clearEverything() {
		List<Team> teams = Utils.getAllTeams(db);
		List<Player> players = Utils.getAllPlayers(db);
		List<Match> matches = Utils.getAllMatches(db);
		for (Team t : teams) {
			db.getEntityManager().getTransaction().begin();
			db.getEntityManager().remove(t);
			db.getEntityManager().getTransaction().commit();
		}
		for (Player p : players) {
			db.getEntityManager().getTransaction().begin();
			db.getEntityManager().remove(p);
			db.getEntityManager().getTransaction().commit();
		}
		for (Match m : matches) {
			db.getEntityManager().getTransaction().begin();
			db.getEntityManager().remove(m);
			db.getEntityManager().getTransaction().commit();
		}
	}
	
	@Override
	protected void performConfiguration() {

		clearEverything();
		
		Sheet sheet = workbook.getSheetAt(0);
		// iterate in order to get teams and players
		for (Row r : sheet) {
			db.getEntityManager().getTransaction().begin();
			Team team = new Team();
			team.schoolName = r.getCell(1).getStringCellValue();
			team.designation = r.getCell(0).getStringCellValue();
			team.score = r.getCell(3).getNumericCellValue();
			team.CombinedStrength = (int) r.getCell(4).getNumericCellValue();
			team.PointDifferential = (int) r.getCell(5).getNumericCellValue();
			team.wentP = r.getCell(6).getBooleanCellValue();
			
			String[] playerNames = r.getCell(2).getStringCellValue().split(",");
			for (String playerName : playerNames) {
				Player p = new Player(playerName); 
				db.getEntityManager().persist(p);
				team.players.add(p);
			}
			db.getEntityManager().persist(team);
			db.getEntityManager().getTransaction().commit();
		}
	}

	@Override
	public String description() {
		return "Populate teams with test case";
	}

}
