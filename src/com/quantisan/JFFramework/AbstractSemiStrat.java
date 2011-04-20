package com.quantisan.JFFramework;

import java.util.*;
import com.dukascopy.api.*;
import com.quantisan.JFFramework.Trade.*;
import com.quantisan.JFUtil.*;
import com.quantisan.JFValidation.JFValidate;

/**
 * @author Paul Lam
 *
 */
public abstract class AbstractSemiStrat implements IStrategy, ITag {	
	@Configurable("Instrument")
		public Instrument defInst;
	@Configurable("Risk Fraction per Trade (0.0, 1.0]") 
		public double riskPct = 0.0025;
	@Configurable("Max. Drawdown Fraction (0.0, 1.0]") 
		public double maxDD = 0.02;
	
	private AbstractSetup setup;
	private AbstractTakePoint takepoint;
	private AbstractEmergency emergency;
	private AbstractExposure exposure;

	@Override
	public abstract void onBar(Instrument instrument, Period period, IBar askBar,
			IBar bidBar) throws JFException;

	@Override
	public final void onStart(IContext context) throws JFException {
		JForexContext.setContext(context);
		JForexAccount.setAccount(context.getAccount());
		
		Set<Instrument> instSet = new HashSet<Instrument>();
		instSet.add(this.defInst);
		Pairer.subscribeTransitionalInstruments(instSet);
		
		Printer.println("-- " + this.toString() + " --");
		Printer.println("By using this software you are agreeing to be bound " +
				"by the Quantisan Systems EULA as published at " +
				"http://www.quantisan.com/eula");	
					
		
		// TODO move validation check into class and expand on functionality
		if (JFValidate.isLicensed()) {
			Printer.println("Initialising ...");
			this.initialize();
			Printer.println("Pre-calculating matrices for " + this.defInst);
			this.setup.initializeConditions(this.defInst);
			Printer.println("Strategy running ...");
		} else {
			Printer.println("Free trial QTD is only available for DEMO or backtesting");
			throw new AssertionError("Failed license check");
		}		
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
		Printer.println("Spinning the trading setup ...");
	}

	/**
	 * @param emergency the riskManager to set
	 */
	public void setEmergency(AbstractEmergency emergency) {
		this.emergency = emergency;
		Printer.println("Implementing emergency exit ...");
	}

	public void setExposure(AbstractExposure exposure) {
		this.exposure = exposure;
		Printer.println("Aligning exposure check ...");
	}

	/**
	 * Call in onStart() to set the various components.
	 * @throws JFException 
	 * @see {@link #setSetup(AbstractSetup)}, 
	 * {@link #setOrderManager(AbsExit)}, 
	 * {@link #setEmergency(AbstractEmergency)},
	 * {@link IStrategy#onStart(IContext)},
	 */
	public abstract void initialize() throws JFException;

	public AbstractSetup getSetup() {
		return setup;
	}

	public AbstractTakePoint getTakePoint() {
		return takepoint;
	}

	public void setTakePoint(AbstractTakePoint takepoint) {
		this.takepoint = takepoint;
		Printer.println("Scouting take point location ...");
	}

	public AbstractEmergency getEmergency() {
		return emergency;
	}

	public AbstractExposure getExposure() {
		return exposure;
	}

	protected double getRiskPct() {
		return riskPct;
	}

	protected double getMaxDD() {
		return maxDD;
	}
	
//	/**
//	 * @return the posthoc
//	 */
//	protected boolean isPosthoc() {
//		return posthoc;
//	}

	@Override public abstract String toString();

}