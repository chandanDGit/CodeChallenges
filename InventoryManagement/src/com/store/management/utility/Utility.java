package com.store.management.utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Utility {

	static DecimalFormat df = new DecimalFormat(Constants._twoDecimal);
	
	public static Double convertToTwoDecimal(String str) {
		BigDecimal bd = new BigDecimal(str);
		bd.setScale(2,BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	
	public static Double convertToTwoDecimal(Double value) {
		BigDecimal bd = new BigDecimal(value);
		bd.setScale(2,BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
}
