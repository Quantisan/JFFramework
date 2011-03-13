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
	private IStop stop;
	public AbsEntry(IStop stop) {
		this.stop = stop;
	}
	private IStop getStop() {
		return this.stop;
	}
	
	protected abstract IOrder enterPosition(Instrument instrument, Sentiment sentiment, double riskPct, String label);
	
	@Override public abstract String toString();
}
