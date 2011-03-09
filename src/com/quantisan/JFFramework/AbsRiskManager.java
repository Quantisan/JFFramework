package com.quantisan.JFFramework;

import com.dukascopy.api.*;

/**
 * Risk manager template
 * 
 * @author plam
 *
 */
public abstract class AbsRiskManager implements IName, ITag {
	// NOTE: this abs class seem to be made to check DD only...
	// TODO remove DD focused algo and use CofR, read max DD val from file
	private double maxDD;
	private AbsRiskManager next;
	
	public void setNext(AbsRiskManager successor) {
		this.next = successor;
	}

	public abstract boolean isNewPositionAllowed(Instrument instrument, Period period);

	/**
	 * Check risk
	 * 
	 * @param strategy
	 * @param instrument
	 * @param period
	 */
	public abstract void checkRisk(AbstractStrat strategy, Instrument instrument, Period period);
}
