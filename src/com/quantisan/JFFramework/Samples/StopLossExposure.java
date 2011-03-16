package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.*;
import com.quantisan.JFFramework.AbsExposure;
import com.quantisan.JFUtil.*;

/**
 * Assumes there is always a stop loss to every order.
 * 
 * @author plam
 *
 */
public class StopLossExposure extends AbsExposure {
	public StopLossExposure(double riskPct) {
		super(riskPct);
	}

	@Override
	public boolean isNewPositionAllowed(Instrument instrument) throws JFException 
	{		
		return (JForexAccount.getAmountAtRisk(instrument) / JForexAccount.getEquity()) <= 0d;
	}

	@Override
	public String toString() {
		return "Stop loss check exposure";
	}
}
