package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.IBar;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.Sentiment;
import com.quantisan.JFFramework.Trade.AbsSetup;

/**
 * A Singleton setup that does nothing.
 * 
 * @author plam
 *
 */
public class NullSetup extends AbsSetup {
	private static final NullSetup setup = new NullSetup();
	
	private NullSetup() {};
	
	@Override
	public String getName() {
		return "Null Setup";
	}

	@Override
	public String getTag() {
		return "NUL";
	}

	@Override
	public Sentiment calculate(Instrument instrument, Period period,
			IBar askBar, IBar bidBar) {
		return Sentiment.NEUTRAL;
	}
	
	/**
	 * @return a Singleton instance
	 */
	public static AbsSetup getInstance() {
		return setup;
	}

	@Override
	public void initialize(Instrument instrument) {	
	}

}
