package com.quantisan.JFFramework;

import com.dukascopy.api.IBar;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.Period;

/**
 * Position manager (i.e. exit strategy) using Chain of Responsibility pattern.
 * 
 * @author plam
 *
 */
public abstract class AbsPositionManager implements IName {
	private AbsPositionManager next;
	
	public void setNext(AbsPositionManager successor) {
		this.next = successor;
	}
	
	public abstract void checkPositions(Instrument instrument, 
									Period period, IBar askBar,
									IBar bidBar);

}
