package com.quantisan.JFFramework.Trade;

import java.util.concurrent.Future;

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
public abstract class AbstractSetup implements ITag {
	//private AbsSetup next;	
	private AbstractEntry entry;
	private IStop stop;
	private AbstractExit exit;	
	
	public AbstractSetup(AbstractEntry entry, IStop stop, AbstractExit exit) {
		this.entry = entry;
		this.stop = stop;
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
	 * Initialize the {@link AbstractCondition}, called in {@link IStrategy#onStart(com.dukascopy.api.IContext) onStart}
	 */
	public abstract void initializeConditions(Instrument instrument) throws JFException;
		
	/**
	 * It is suggested to use State Pattern with {@link AbstractCondition}.
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
	
	public Future<IOrder> enterPosition(Instrument instrument, Sentiment sentiment, double riskPct) throws JFException 
	{
		String comment = OrderCommentMaker.getComment(this, entry, stop, exit);	
		return entry.enterPosition(instrument, sentiment, riskPct, comment);
	}
	
	/**
	 * Delegated to {@link AbstractExit#managePositions(Instrument, Period, IBar, IBar)}
	 * 
	 * @param instrument
	 * @param period
	 * @param askBar
	 * @param bidBar
	 * @throws JFException
	 */
	public void checkPositions(Instrument instrument, Period period, IBar askBar,
										IBar bidBar) throws JFException 
	{
		exit.managePositions(instrument, period, askBar, bidBar);
	}
			
}