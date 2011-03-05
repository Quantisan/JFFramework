package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.IBar;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.IName;
import com.quantisan.JFFramework.ITag;
import com.quantisan.JFFramework.Sentiment;

/**
 * 
 * It is suggested to use State Pattern for this implementation.
 * 
 * @author Paul Lam
 */
public interface ISetup extends IName, ITag {
	// TODO implement with simple conditional statements of a set of ICondition
	//		filter instrument and period here for each condition
	public Sentiment calculate(Instrument instrument, Period period, 
						IBar askBar, IBar bidBar);	
}
