package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.Instrument;
import com.quantisan.JFFramework.AbsExposure;

/**
 * Disable new position entry by always returning false on {@link #isNewPositionAllowed(Instrument)}
 * @author plam
 *
 */
public class NullExposure extends AbsExposure {
	public NullExposure(double riskPct) {
		super(riskPct);
		// TODO Auto-generated constructor stub
	}

	private static final NullExposure exposure = new NullExposure(0);
	
	/**
	 * @return a Singleton instance
	 */
	public static AbsExposure getInstance() {
		return exposure;
	}
	
	@Override
	public boolean isNewPositionAllowed(Instrument instrument) {
		return false;
	}

	@Override
	public String toString() {
		return "Null exposure";
	}

}
