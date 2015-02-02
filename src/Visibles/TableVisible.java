package Visibles;

import javax.swing.*;

public abstract class TableVisible extends Visible {

	public abstract Object[] getColumnNames();
	public abstract Object[][] getData();
	
	@Override
	public JComponent getComponent() {
		JTable table = new JTable(getData(), getColumnNames());
		JScrollPane tableScrollPane = new JScrollPane(table);
		return tableScrollPane;
	}

}
