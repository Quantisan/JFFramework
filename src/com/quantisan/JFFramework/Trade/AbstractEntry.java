package com.quantisan.JFFramework.Trade;

import java.util.List;
import java.util.concurrent.Future;

import com.dukascopy.api.IOrder;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.JFException;
import com.quantisan.JFFramework.*;

/**
 * Position entry module.
 * 
 * @author plam
 *
 */
public abstract class AbstractEntry implements ITag {
	private IStop stop;
	public AbstractEntry(IStop stop) {
		this.stop = stop;
	}
		
	protected IStop getStop() { return this.stop; }
	
	protected abstract List<Future<IOrder>> enterPosition(Instrument instrument, 
													Sentiment sentiment, 
													double riskPct, 
													String comment) throws JFException;
	
	@Override public abstract String toString();
}
