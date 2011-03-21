package com.quantisan.JFFramework;

import java.util.*;
import com.dukascopy.api.*;
import com.quantisan.JFFramework.Trade.*;
import com.quantisan.JFUtil.*;

/**
 * @author Paul Lam
 *
 */
public abstract class AbstractSemiStrat implements IStrategy, ITag {	
	@Configurable("Instrument")
		public Instrument defInst;	
	@Configurable("Sentiment")
		public Sentiment defSentiment;
	@Configurable("Risk Pct per Trade (0.0, 1.0]") 
		public double riskPct = 0.002;
	@Configurable("Max. Drawdown (0.0, 1.0]") 
		public double maxDD = 0.02;
	@Configurable("Record post-hoc trade data on exit")
		public boolean posthoc = false;
	
	private AbstractSetup setup;
	private AbstractEmergency emergency;
	private AbstractExposure exposure;

	@Override
	public final void onBar(Instrument instrument, Period period, IBar askBar,
			IBar bidBar) throws JFException 
	{
		if (instrument != this.defInst)	{ return; }
		
		// TODO multi-thread emergency and exit
		emergency.checkEmergency(this, instrument, period);
		setup.checkPositions(instrument, period, askBar, bidBar);
		
		Sentiment sentiment = setup.calculate(instrument, period, askBar, bidBar);
		
		if (sentiment == this.defSentiment && exposure.isNewPositionAllowed(instrument)) 
		{			
			setup.enterPosition(instrument, sentiment, this.riskPct);
		}		
	}

	@Override
	public final void onStart(IContext context) throws JFException {
		JForexContext.setContext(context);
		JForexAccount.setAccount(context.getAccount());
		
		Set<Instrument> instSet = new HashSet<Instrument>();
		instSet.add(this.defInst);
		Pairer.subscribeTransitionalInstruments(instSet);
		
		Printer.println("-- " + this.toString() + " --");
		Printer.println("-- Disclaimer: use at your own risk --");	// TODO add full disclaimer		
		
		// TODO move validation check into class and expand on functionality
		if (JForexContext.getEngine().getType() == IEngine.Type.DEMO ||
				JForexContext.getEngine().getType() == IEngine.Type.TEST) {
			// TODO add OR passed validation in IF statement
			this.initialize();
			this.setup.initializeConditions(this.defInst);
		} else {
			Printer.println("Failed validation, bailing program...");
		}
		
		
		
		// TODO initialize entry higher time frames
	}

	@Override
	public void onTick(Instrument instrument, ITick tick) throws JFException {
	}

	@Override
	public void onMessage(IMessage message) throws JFException {
	}

	@Override
	public final void onAccount(IAccount account) throws JFException {
		JForexAccount.setAccount(account);
	}

	/**
	 * @param setup the setup to set
	 */
	public void setSetup(AbstractSetup setup) {
		this.setup = setup;
		Printer.println("Trading setup: " + this.setup.toString());
	}

//	/**
//	 * @param entry the enter to set
//	 */
//	public void setEntry(AbsEntry entry) {
//		this.entry = entry;
//		Printer.println("Entry: " + this.entry.toString());
//	}
//
//	/**
//	 * @param orderManager the orderManager to set
//	 */
//	public void setExit(AbsExit exit) {
//		this.exit = exit;
//		Printer.println("Exit: " + this.exit.toString());
//	}

	/**
	 * @param emergency the riskManager to set
	 */
	public void setEmergency(AbstractEmergency emergency) {
		this.emergency = emergency;
		Printer.println("Emergency exit: " + this.emergency.toString());
	}

	public void setExposure(AbstractExposure exposure) {
		this.exposure = exposure;
		Printer.println("Exposure check: " + this.exposure.toString());
	}

	/**
	 * Call in onStart() to set the various components.
	 * @see {@link #setSetup(AbstractSetup)}, 
	 * {@link #setOrderManager(AbsExit)}, 
	 * {@link #setEmergency(AbstractEmergency)},
	 * {@link IStrategy#onStart(IContext)},
	 */
	public abstract void initialize();

	protected double getRiskPct() {
		return riskPct;
	}

	protected double getMaxDD() {
		return maxDD;
	}
	
	/**
	 * @return the posthoc
	 */
	protected boolean isPosthoc() {
		return posthoc;
	}

	@Override public abstract String toString();

}