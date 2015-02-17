package Visibles;

import javax.swing.*;

public abstract class TableVisible extends Visible {

	public abstract Object[] getColumnNames();
	public abstract Object[][] getData();
	
	@Override
	public JComponent getComponent() {
		JTable table = new JTable(getData(), getColumnNames()) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			}
		};
		JScrollPane tableScrollPane = new JScrollPane(table);
		return tableScrollPane;

	}

}
