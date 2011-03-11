package com.quantisan.JFFramework;

import com.dukascopy.api.IBar;
import com.dukascopy.api.*;
import com.quantisan.JFFramework.Trade.ICondition;

/**
 * Chain of Responsibilities pattern.
 * 
 * 
 * 
 * @author Paul Lam
 */
public abstract class AbsSetup implements ITag {
	private AbsSetup next;
	
	public void setNext (AbsSetup successor) {
		this.next = successor;
	}
	
	@Override public abstract String toString();
	
	/**
	 * Initialize the {@link ICondition}, called in {@link IStrategy#onStart(com.dukascopy.api.IContext) onStart}
	 */
	public abstract void initializeConditions(Instrument instrument) throws JFException;
	
	// TODO implement with simple conditional statements of a set of ICondition
	//		filter instrument and period here for each condition
	/**
	 * It is suggested to use State Pattern with {@link ICondition}.
	 * 
	 * @param instrument
	 * @param period
	 * @param askBar
	 * @param bidBar
	 * @return
	 */
	public abstract Sentiment calculate(Instrument instrument, 
										Period period, 
										IBar askBar, 
										IBar bidBar)  throws JFException;	
	
}
