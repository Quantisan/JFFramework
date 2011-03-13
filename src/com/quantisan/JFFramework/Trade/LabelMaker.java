package com.quantisan.JFFramework.Trade;

import java.util.Calendar;
import java.util.TimeZone;

import com.dukascopy.api.Instrument;
import com.quantisan.JFUtil.JForexContext;

public class LabelMaker {
	private LabelMaker() {}
	
	public static String getLabel(Instrument instrument, 
									AbsSetup setup, 
									AbsEntry entry, 
									AbsExit exit) 
	{
		String iname = instrument.toString();
		iname = iname.substring(0, 3) + iname.substring(3, 6);
		String label = iname.toLowerCase();
		
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.setTimeInMillis(JForexContext.getTime(instrument));
		label += ":" + calendar.get(Calendar.MONTH) + "-" +
							calendar.get(Calendar.DAY_OF_MONTH) + "-" +
							calendar.get(Calendar.HOUR_OF_DAY) + "-" +
							calendar.get(Calendar.MINUTE);
	
		
		
		label += ":" + setup.getTag();
		label += ":" + entry.getTag();
		label += ":" + exit.getTag();
		
		return label;
	}
}
