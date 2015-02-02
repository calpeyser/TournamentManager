package Utils;

import java.util.*;

public class MiscUtils {

	public static String[] listToArray(List<String> a) {
		String[] out = new String[a.size()];
		for (int i = 0; i < a.size(); i++) {
			out[i] = a.get(i);
		}
		return out;
	}
	
}
