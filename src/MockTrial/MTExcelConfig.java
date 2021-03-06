package MockTrial;

import org.apache.poi.ss.usermodel.*;
import java.util.*;
import DataAction.ExcelDataAction;

public class MTExcelConfig extends ExcelDataAction {

	
	@Override
	protected void performConfiguration() {
		Sheet sheet = workbook.getSheetAt(0);
		// iterate in order to get teams and players
		for (Row r : sheet) {
			db.getEntityManager().getTransaction().begin();
			Team team = new Team();
			int designationType = r.getCell(0).getCellType();
			if (designationType == Cell.CELL_TYPE_NUMERIC) {
				Double value = r.getCell(0).getNumericCellValue();
				if (value == Math.round(value)) {
					team.designation = Integer.toString(value.intValue());
				}
				else {
					team.designation = Double.toString(r.getCell(0).getNumericCellValue());
				}
			}
			else {
				team.designation = r.getCell(0).getStringCellValue();
			}
			team.schoolName = r.getCell(1).getStringCellValue();
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
		return "Configure Players and Teams from Spreadsheet";
	}
}
