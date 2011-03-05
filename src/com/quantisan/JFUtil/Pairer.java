package com.quantisan.JFUtil;
import java.util.Currency;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.dukascopy.api.Instrument;

public class Pairer {
	private Pairer() {}
	
	/**
	 * Initialize currency pairs for getting all major counter 
	 * to account currency.
	 * 	
	 */	
	public static void initializeMajorPairs(Map<Currency,Instrument> pairs) {
		Set<Currency> curSet = new HashSet<Currency>();
		// add all major currencies
		curSet.add(Currency.getInstance("AUD"));
		curSet.add(Currency.getInstance("CAD"));
		curSet.add(Currency.getInstance("CHF"));
		curSet.add(Currency.getInstance("EUR"));
		curSet.add(Currency.getInstance("GBP"));
		curSet.add(Currency.getInstance("JPY"));
		curSet.add(Currency.getInstance("NZD"));
		curSet.add(Currency.getInstance("USD"));
		Instrument instrument;
		for (Currency curr : curSet) {
			if (!curr.equals(JForexAccount.getAccountCurrency())) {
				instrument = getPair(curr, JForexAccount.getAccountCurrency());	
				pairs.put(curr, instrument);
			}
		}
	}
	
	/**
	 * Get the Instrument given two Currencies
	 * 
	@param first a currency in a pair
	 *
	@param second the other currency in a pair
	 *
	@return an Instrument with the correct base/counter currencies order
	 *
	**/
	private static Instrument getPair(Currency first, Currency second) {
		String pair; 
		pair = first.toString() + Instrument.getPairsSeparator() + 
				second.toString();
		if (Instrument.isInverted(pair))
			pair = second.toString() + Instrument.getPairsSeparator() + 
				first.toString();
		return Instrument.fromString(pair);
	}

}
