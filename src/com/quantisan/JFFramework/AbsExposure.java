package com.quantisan.JFFramework;

import com.dukascopy.api.*;

public abstract class AbsExposure {
	private double riskPct;
	public AbsExposure(double riskPct) {
		this.riskPct = riskPct;
	}
	
	protected double getRiskPct() {
		return this.riskPct;
	}
	
	public abstract boolean isNewPositionAllowed(Instrument instrument) throws JFException;
	
	@Override public abstract String toString();
}
