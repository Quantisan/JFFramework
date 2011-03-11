package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.Instrument;
import com.dukascopy.api.JFException;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.*;

public interface ICondition {
	public Sentiment calculate(Instrument instrument, Period period) throws JFException;
	
	/**
	 * Override in last state with trade entry logic.  Otherwise it's empty.
	 * @param ticket order information for trade entry
	 * 
	 */
	//public void enterPosition(ITicket ticket);
	
	@Override public String toString();
}
