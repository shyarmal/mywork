package airwell.rpn.calculator.util;

import java.util.regex.Pattern;

public class Util {

	private static final Pattern EMPTY_DECIMAL = Pattern.compile("-?[0-9]*\\.0*$");
	
	public static String stipFractionsIfOk(Double number) {
		String value = Double.toString(number);
		if (EMPTY_DECIMAL.matcher(value).matches())
			return value.substring(0, value.indexOf("."));
		return value;
	}
	
}
