package com.quantisan.JFFramework;

import com.dukascopy.api.*;

/**
 * Risk manager template
 * 
 * @author plam
 *
 */
public abstract class AbsEmergency implements IName, ITag {
	// NOTE: this abs class seem to be made to check DD only...
	// TODO remove DD focused algo and use CofR, read max DD val from file
	private double maxDD;
	private AbsEmergency next;
	
	/**
	 * Check risk
	 * 
	 * @param strategy
	 * @param instrument
	 * @param period
	 */
	public abstract void checkEmergency(AbstractSemiStrat strategy, Instrument instrument, Period period);

	public void setNext(AbsEmergency successor) {
		this.next = successor;
	}

	public double getMaxDD() {
		return maxDD;
	}

	public void setMaxDD(double maxDD) {
		this.maxDD = maxDD;
	}
}
