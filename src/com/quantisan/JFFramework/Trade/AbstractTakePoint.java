package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.Instrument;
import com.dukascopy.api.JFException;
import com.quantisan.JFFramework.Sentiment;

public abstract class AbstractTakePoint {
	@Override public abstract String toString();

	/**
	 * 
	 * @param instrument
	 * @param period
	 * @param askBar
	 * @param bidBar
	 * @return
	 */
	public abstract Sentiment calculate(Instrument instrument) throws JFException;
}
