package Utils;

import java.lang.annotation.Annotation;
import java.util.*;

import javax.persistence.OneToMany;

public class MiscUtils {

	public static String[] listToArray(List<String> a) {
		String[] out = new String[a.size()];
		for (int i = 0; i < a.size(); i++) {
			out[i] = a.get(i);
		}
		return out;
	}

	public static int[] listToArrayInt(List<Integer> a) {
		int[] out = new int[a.size()];
		for (int i = 0; i < a.size(); i++) {
			out[i] = a.get(i);
		}
		return out;
	}
	
	public static Object[] listToArrayObject(List<Object> a) {
		Object[] out = new String[a.size()];
		for (int i = 0; i < a.size(); i++) {
			out[i] = a.get(i);
		}
		return out;
	}
	
	public static boolean isOneToMany(Annotation[] annotations) {
		for (Annotation a : annotations) {
			if (a.annotationType().equals(OneToMany.class)) {
				return true;
			}
		}
		return false;
	}

	public static Class<?> getInnerType(Annotation[] annotations) {
		for (Annotation a : annotations) {
			if (a.annotationType().equals(OneToMany.class)) {
				OneToMany o = (OneToMany) a;
				return o.targetEntity();
			}
		}
		throw new RuntimeException("Could not get target Entity");
	}
	
	// found this on StackOverflow: http://stackoverflow.com/questions/2152742/java-swing-multiline-labels
	public static String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>");
	}
	
	
}
