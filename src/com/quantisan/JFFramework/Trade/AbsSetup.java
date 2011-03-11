package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.IBar;
import com.dukascopy.api.IStrategy;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.IName;
import com.quantisan.JFFramework.ITag;
import com.quantisan.JFFramework.Sentiment;

/**
 * Chain of Responsibilities pattern.
 * 
 * 
 * 
 * @author Paul Lam
 */
public abstract class AbsSetup implements IName, ITag {
	private AbsSetup next;
	
	public void setNext (AbsSetup successor) {
		this.next = successor;
	}
	
	/**
	 * Initialize the {@link ICondition}, called in {@link IStrategy#onStart(com.dukascopy.api.IContext) onStart}
	 */
	public abstract void initialize(Instrument instrument);
	
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
	public abstract Sentiment calculate(Instrument instrument, Period period, 
						IBar askBar, IBar bidBar);	
	
}
