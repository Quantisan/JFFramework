package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.Instrument;
import com.dukascopy.api.JFException;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.*;

public abstract class AbstractCondition {
	private AbstractCondition next;

	/**
	 * @param instrument
	 * @param period
	 * @return the resulting sentiment calculated from the next condition;
	 * null if no next is set
	 * @throws JFException
	 */
	protected Sentiment getNextSentiment(Instrument instrument, Period period) throws JFException
	{
		if (next == null)	return null;
		else return next.calculate(instrument, period);
	}
	
	/**
	 * @param next the next to set
	 */
	public void setNext(AbstractCondition next) {
		this.next = next;
	}

	public abstract Sentiment calculate(Instrument instrument, Period period) throws JFException;
	
	@Override public abstract String toString();
}
