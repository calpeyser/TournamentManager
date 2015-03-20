package MootCourt;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import DataAction.ExcelDataAction;

public class ExcelJudgesConfig extends ExcelDataAction {

	@Override
	protected void performConfiguration() {
		Sheet sheet = workbook.getSheetAt(0);
		for (Row r : sheet) {
			db.getEntityManager().getTransaction().begin();
			Judge judge = new Judge();
			judge.name = r.getCell(0).getStringCellValue();
			db.getEntityManager().persist(judge);
			db.getEntityManager().getTransaction().commit();
		}

	}

	@Override
	public String description() {
		return "Configue Judges from Spreadsheet";
	}

}
