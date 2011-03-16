package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.Instrument;
import com.quantisan.JFUtil.JForexContext;

public class LabelMaker {
	private LabelMaker() {}
	
	public static String getLabel (Instrument instrument) 
	{
		String iname = instrument.toString();
		iname = iname.substring(0, 3) + iname.substring(4, 7);
		String label = iname;

//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
//		calendar.setTimeInMillis(JForexContext.getTime(instrument));
		label += JForexContext.getTime(instrument);
//			calendar.get(Calendar.MONTH) + "m" +
//							calendar.get(Calendar.DAY_OF_MONTH) + "d" +
//							calendar.get(Calendar.HOUR_OF_DAY) + "h" +
//							calendar.get(Calendar.MINUTE) + "m";
	
		label.toLowerCase();
		return label;
	}
}
