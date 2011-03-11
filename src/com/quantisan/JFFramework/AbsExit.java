package com.quantisan.JFFramework;

import com.dukascopy.api.IBar;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.Period;

/**
 * Exit manager (i.e. exit strategy) using Chain of Responsibility pattern.
 * 
 * @author plam
 *
 */
public abstract class AbsExit implements ITag{
	private AbsExit next;
	
	public void setNext(AbsExit successor) {
		this.next = successor;
	}
	
	public abstract void checkPositions(Instrument instrument, 
									Period period, IBar askBar,
									IBar bidBar);
	
	@Override public abstract String toString();

}
