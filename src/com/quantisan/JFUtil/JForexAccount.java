package com.quantisan.JFUtil;

import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.dukascopy.api.IAccount;
import com.dukascopy.api.Instrument;

public enum JForexAccount {
	INSTANCE;
	
	private IAccount account;
	private double riskPct;
	
	private double maxEquity = Double.NEGATIVE_INFINITY;
	private HashMap<Currency, Instrument> pairs = new HashMap<Currency, Instrument>();

	/**
	 * 
	 */
	JForexAccount() {
		Pairer.initializeMajorPairs(pairs);
	}
	
	/**
	 * @return the riskPct
	 */
	public static double getRiskPct() {
		return INSTANCE.riskPct;
	}

	/**
	 * @param riskPct Fraction of account to put at risk per position (0.0, 1.0]
	 */
	public static void setRiskPct(double riskPct) {
		INSTANCE.riskPct = riskPct;
	}
	
	/**
	 * @return the account
	 */
	public static IAccount getAccount() {
		return INSTANCE.account;
	}

	/**
	 * @return the account currency
	 */
	public static Currency getAccountCurrency() {
		return INSTANCE.account.getCurrency();
	}
	
	/**
	 * @param account the account to set
	 */
	public static void setAccount(IAccount account) {
		INSTANCE.account = account;
		INSTANCE.updateMaxEquity();
	}
	
	/**
	 * 
	 * @param maxDrawdown maximum drawdown in percent decimal, [0.0, 1.0]. 
	 * For example, max drawdown of 5% should be entered as 0.05
	 * 
	 * @return true if max drawdown is reached
	 */
	public static boolean isMaxDrawdownBroken(double maxDrawdown) {
		if (maxDrawdown < 0d || maxDrawdown > 1d) {
			throw new IllegalArgumentException("maxDrawdown must be [0.0, 1.0]");
		}
		INSTANCE.updateMaxEquity();
		return (INSTANCE.getCurrentDrawdown() < -maxDrawdown);	
	}
	
	private void updateMaxEquity() {
		if(getEquity() > this.maxEquity)
			this.maxEquity = getEquity();
	}
	
	/**
	 * 
	 * @return current drawdown in negative percentage, positive means profitable
	 */
	private double getCurrentDrawdown() {		
		return 1 - getEquity()/INSTANCE.maxEquity;
	}
	
	/**
	 * 
	 * @return account equity
	 * @see IAccount#getEquity()
	 */
	public static double getEquity() {
		return INSTANCE.account.getEquity();
	}
	
	/**
	 * Subscribe to transitional instruments for converting profit/loss
	 * to account currency
	 * 
	@param instSet set of instruments to be traded
	 *
	**/
	public static void subscribeTransitionalInstruments(Set<Instrument> instSet) {
		Currency firstCurr, secondCurr;
		Set<Instrument> subscribeSet = 
			new HashSet<Instrument>(JForexContext.getContext().getSubscribedInstruments());
		
		for (Instrument instrument : instSet) {
			firstCurr = instrument.getPrimaryCurrency();
			secondCurr = instrument.getSecondaryCurrency();		
			if (!firstCurr.equals(getAccountCurrency()) && 
					!secondCurr.equals(getAccountCurrency()))
			{				
				subscribeSet.add(INSTANCE.pairs.get(secondCurr));		// transitional pair
			}
		}
		JForexContext.getContext().setSubscribedInstruments(subscribeSet);	
	}
	
	/**
	 * Calculate the risked amount in home currency per unit traded of an instrument
	 * 
	@param instrument the instrument traded
	 *
	@param stopSize	the stop size to use for calculation
	 *
	@return	the risked amount per unit traded with given stop size
	**/
	private double getAccountRiskPerUnit(Instrument instrument, double stopSize) {
		double transitionalPrice;
		Instrument transitionalInstrument;
		double riskInitialCurrency = stopSize;
		
		if (instrument.getSecondaryCurrency().equals(getAccountCurrency())) {
			// If second currency in the instrument is account currency, 
			// then risk is equal amount difference 
			return riskInitialCurrency;
		} else  if (instrument.getPrimaryCurrency().equals(getAccountCurrency())) {
			return riskInitialCurrency / JForexContext.getPrice(instrument);
		} else {
			transitionalInstrument = pairs.get(instrument.getSecondaryCurrency());			
			transitionalPrice = JForexContext.getPrice(transitionalInstrument);
			if (transitionalInstrument.getSecondaryCurrency().equals(getAccountCurrency()))
				return riskInitialCurrency * transitionalPrice;
			else				
				return riskInitialCurrency / transitionalPrice;				
		}
	}
	
	/**
	 * Get lot size given size of stop and preferred risk percentage
	 * 
	@param instrument the instrument traded
	 *
	@param stopSize	the stop size to use for calculation
	 *
	@return	a suggested lot size in millions
	**/
	public static double getLot(Instrument instrument, 
			double stopSize) 
	{
		return getPartLot(instrument, stopSize, 1);
	}
	
	/**
	 * Get lot size divided by number of parts 
	 * given size of stop and preferred risk percentage.
	 * For use with splitting a position into multiple trades, such that
	 * the total risk is less than the given risk percentage.
	 * 
	@param instrument the instrument traded
	 *
	@param stopSize	the stop size to use for calculation
	 *
	@param parts	number of parts of the position
	 *
	@return	a suggested partitioned lot size in millions
	**/
	public static double getPartLot(Instrument instrument, 
			double stopSize, int parts) 
	{
		double riskAmount, lotSize;
		double equity = INSTANCE.account.getEquity();
		riskAmount = equity * INSTANCE.riskPct;
		lotSize = riskAmount / INSTANCE.getAccountRiskPerUnit(instrument, stopSize);
		lotSize /= 1e6;		// in millions for JForex API
		
		lotSize /= parts;
		
		return Rounder.lot(lotSize);
	}
}