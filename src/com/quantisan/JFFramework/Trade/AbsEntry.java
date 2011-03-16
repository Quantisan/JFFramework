package com.quantisan.JFFramework.Trade;

import java.util.concurrent.Future;

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
		
	protected IStop getStop() { return this.stop; }
	
	protected abstract Future<IOrder> enterPosition(Instrument instrument, Sentiment sentiment, double riskPct, String comment);
	
	@Override public abstract String toString();
}
