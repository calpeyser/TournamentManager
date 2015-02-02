package Visibles;

public class StubTableVisible extends TableVisible {

	@Override
	public Object[] getColumnNames() {
		String[] out = {
				"column 1",
				"column 2"
		};
		return out;
	}

	@Override
	public Object[][] getData() {
		String[][] out = {
				{"point 1a", "point 1b"},
				{"point 2a", "point 2b"},
				{"point 3a", "point 3b"}
		};
		return out;
	}

	@Override
	public String getName() {
		return "Mock Table Visible";
	}

}
