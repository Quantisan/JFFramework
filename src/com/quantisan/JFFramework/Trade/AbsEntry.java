package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.IOrder;
import com.dukascopy.api.Instrument;
import com.quantisan.JFFramework.*;

/**
 * Position entry module.
 * 
 * @author plam
 *
 */
public abstract class AbsEntry implements ITag {
	protected abstract IOrder enterPosition(Instrument instrument, Sentiment sentiment, double riskPct, String label);
	
	@Override public abstract String toString();
}
