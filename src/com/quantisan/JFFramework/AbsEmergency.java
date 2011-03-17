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
	 * Check for emergency exit
	 * 
	 * @param strategy the running strategy
	 * @param instrument
	 * @param period current period
	 */
	public abstract void checkEmergency(AbstractSemiStrat strategy, Instrument instrument, Period period);

	public AbsEmergency(double maxDD, Period checkPeriod) {
		this.maxDD = maxDD;
		this.checkPeriod = checkPeriod;		
	}
	
	public double getMaxDD() { return maxDD; }

	/**
	 * @return period/frequency to check for emergency conditions
	 */
	public Period getCheckPeriod() { return checkPeriod; }

	public void setNext(AbsEmergency successor) { this.next = successor; }

	public AbsEmergency getNext() { return this.next; }

	@Override public abstract String toString();
}
