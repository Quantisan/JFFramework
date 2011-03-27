package com.quantisan.JFFramework;

import com.dukascopy.api.*;

/**
 * Emergency handling template
 * 
 * @author plam
 *
 */
public abstract class AbstractEmergency implements ITag {
	// NOTE: this abs class seem to be made to check DD only...
	// TODO remove DD focused algo and use CofR, read max DD val from file
	private double maxDD;
	private Period checkPeriod;
	private AbstractEmergency next;
	
	/**
	 * Check for emergency exit
	 * 
	 * @param strategy the running strategy
	 * @param instrument
	 * @param period current period
	 */
	// TODO filter out the incorrect tag
	public abstract void checkEmergency(AbstractSemiStrat strategy, Instrument instrument, Period period);

	public AbstractEmergency(double maxDD, Period checkPeriod) {
		this.maxDD = maxDD;
		this.checkPeriod = checkPeriod;		
	}
	
	public double getMaxDD() { return maxDD; }

	/**
	 * @return period/frequency to check for emergency conditions
	 */
	public Period getCheckPeriod() { return checkPeriod; }

	public void setNext(AbstractEmergency successor) { this.next = successor; }

	public AbstractEmergency getNext() { return this.next; }

	@Override public abstract String toString();
}
