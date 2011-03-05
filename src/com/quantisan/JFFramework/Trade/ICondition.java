package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.IBar;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.Sentiment;

public interface ICondition {
	public Sentiment calculate(ISetup entry, Instrument instrument, 
						Period period, IBar askBar, IBar bidBar);
	
	/**
	 * Override in last state with trade entry logic.  Otherwise it's empty.
	 * @param ticket order information for trade entry
	 * 
	 */
	//public void enterPosition(ITicket ticket);
	
}
