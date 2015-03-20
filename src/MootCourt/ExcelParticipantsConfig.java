package MootCourt;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import DataAction.ExcelDataAction;


public class ExcelParticipantsConfig extends ExcelDataAction {

	@Override
	protected void performConfiguration() {
		Sheet sheet = workbook.getSheetAt(0);
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
			team.player1 = new Player(r.getCell(2).getStringCellValue());
			team.player2 = new Player(r.getCell(3).getStringCellValue());
			db.getEntityManager().persist(team.player1);
			db.getEntityManager().persist(team.player2);
			db.getEntityManager().persist(team);
			db.getEntityManager().getTransaction().commit();
		}
	}

	@Override
	public String description() {
		return "Configure Teams from Spreadsheet";
	}

}
