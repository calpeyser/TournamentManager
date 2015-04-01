package Checkers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import DataAction.ExcelDataAction;


public class ConfigurePlayersFromSpreadsheet extends ExcelDataAction {

	@Override
	protected void performConfiguration() {
		Sheet sheet = workbook.getSheetAt(0);
		for (Row r : sheet) {
			db.getEntityManager().getTransaction().begin();
			Player player = new Player(r.getCell(0).getStringCellValue());
			db.getEntityManager().persist(player);
			db.getEntityManager().getTransaction().commit();
		}
	}

	@Override
	public String description() {
		return "Configure Players from Spreadsheet";
	}

}
