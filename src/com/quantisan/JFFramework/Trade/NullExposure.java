package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.Instrument;
import com.quantisan.JFFramework.AbstractExposure;

/**
 * Disable new position entry by always returning false on {@link #isNewPositionAllowed(Instrument)}
 * @author plam
 *
 */
public class NullExposure extends AbstractExposure {
	public NullExposure(double riskPct) {
		super(riskPct);
		// TODO Auto-generated constructor stub
	}

	private static final NullExposure INSTANCE = new NullExposure(0);
	
	/**
	 * @return a Singleton instance
	 */
	public static AbstractExposure getInstance() {
		return INSTANCE;
	}
	
	@Override
	public boolean isNewPositionAllowed(String strategyTag, Instrument instrument) {
		return false;
	}

	@Override
	public String toString() {
		return "Null exposure";
	}

	@Override
	public String getTag() {
		return "NUL";
	}

}
