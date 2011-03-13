package com.quantisan.JFFramework;

import com.dukascopy.api.*;

/**
 * Emergency handling template
 * 
 * @author plam
 *
 */
public abstract class AbsEmergency implements ITag {
	// NOTE: this abs class seem to be made to check DD only...
	// TODO remove DD focused algo and use CofR, read max DD val from file
	private double maxDD;
	private Period checkPeriod;
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
	
	public AbsEmergency(double maxDD, Period checkPeriod) {
		this.maxDD = maxDD;
		this.checkPeriod = checkPeriod;		
	}
	
	public double getMaxDD() {
		return maxDD;
	}

	public Period getCheckPeriod() {
		return checkPeriod;
	}

	@Override public abstract String toString();
}
