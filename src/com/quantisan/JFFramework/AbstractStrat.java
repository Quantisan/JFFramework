package com.quantisan.JFFramework;

import com.dukascopy.api.*;
import com.quantisan.JFFramework.Trade.*;
import com.quantisan.JFUtil.*;

/**
 * @author Paul Lam
 *
 */
public abstract class AbstractStrat implements IStrategy, IName {	
	private final String version = "0.1 alpha";
	
	@Configurable("Minimum observing period")
		public Period minPeriod = Period.ONE_MIN;
	@Configurable("Risk Pct per Trade (0.0, 1.0]") 
		public double riskPct = 0.002;
	@Configurable("Max. Drawdown Pct (0.0, 1.0]") 
		public double maxDrawdown = 0.01;
	@Configurable("Record post-hoc trade data on exit")
		public boolean posthoc = false;
	
	private ISetup setup;
	private IEnter enter;
	private AbsRiskManager riskManager;
	private AbsPositionManager positionManager;	
	
	@Override
	public final void onBar(Instrument instrument, Period period, IBar askBar,
			IBar bidBar) throws JFException 
	{
		if (minPeriod.compareTo(period) > 0)	
			return;		// skipping all periods small than minPeriod
		
		// TODO: multithread diff parts?
		riskManager.checkDrawdown(this);
		positionManager.checkPositions(instrument, period, askBar, bidBar);
		if (riskManager.isNewPositionAllowed()) {
			Sentiment sentiment = setup.calculate(instrument, period, askBar, bidBar);
			if (sentiment != Sentiment.NEUTRAL)
				enter.enterPosition(instrument, sentiment);
		}
	}

	@Override
	public final void onStart(IContext context) throws JFException {
		JForexContext.setContext(context);
		JForexAccount.setAccount(context.getAccount());
		JForexAccount.setRiskPct(this.riskPct);
		Printer.println("-- Quantisan.com JFFramework v. " + this.version  + " --");
		this.initialize();		
		
		// TODO print strategies chosen
		
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

	@Override
	public void onStop() throws JFException {		
//		if (this.posthoc) {
//			Analyzer.record(setup.getTag());
//		}
//		this.cleaner.onStop();
	}

	/**
	 * @param setup the setup to set
	 */
	public void setSetup(ISetup setup) {
		this.setup = setup;
		Printer.println("Entry: " + this.setup.getName());
	}

	/**
	 * @param enter the enter to set
	 */
	public void setEnter(IEnter enter) {
		this.enter = enter;
	}

	/**
	 * @param riskManager the riskManager to set
	 */
	public void setRiskManager(AbsRiskManager riskManager) {
		this.riskManager = riskManager;
		Printer.println("Risk Manager: " + this.riskManager.getName());
	}

	/**
	 * @param orderManager the orderManager to set
	 */
	public void setPositionManager(AbsPositionManager positionManager) {
		this.positionManager = positionManager;
		Printer.println("Position Manager: " + this.positionManager.getName());
	}

	/**
	 * Call in onStart() to initialize the various strategies.
	 * @see {@link #setSetup(ISetup)}, 
	 * {@link #setOrderManager(AbsPositionManager)}, 
	 * {@link #setRiskManager(AbsRiskManager)},
	 * {@link IStrategy#onStart(IContext)},
	 * {@link AbstractStrat#setCleaner(ICleaner)}
	 */
	public abstract void initialize();

}