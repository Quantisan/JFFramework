package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.*;
import com.quantisan.JFFramework.ITag;
import com.quantisan.JFFramework.Sentiment;

/**
 * Chain of Responsibilities pattern.
 * 
 * 
 * 
 * @author Paul Lam
 */
public abstract class AbsSetup implements ITag {
	//private AbsSetup next;	
	private AbsEntry entry;
	private AbsExit exit;	
	
	public AbsSetup(AbsEntry entry, AbsExit exit) {
		this.entry = entry;
		this.exit = exit;
	}
	
//	public void setNext (AbsSetup successor) {
//		this.next = successor;
//	}
//	
//	public AbsSetup getNext() {
//		return this.next;
//	}
	
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
	
	public IOrder enterPosition(Instrument instrument, Sentiment sentiment, double riskPct) throws JFException 
	{
		String label = LabelMaker.getLabel(instrument, this, entry, exit);	
		return entry.enterPosition(instrument, sentiment, riskPct, label);
	}
	
	public void checkPositions(Instrument instrument, Period period, IBar askBar,
										IBar bidBar) throws JFException 
	{
		exit.managePositions(instrument, period, askBar, bidBar);
	}
			
}
