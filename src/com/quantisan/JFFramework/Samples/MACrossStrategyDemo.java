package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.JFException;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.*;
import com.quantisan.JFFramework.Trade.*;

public class MACrossStrategyDemo extends AbstractSemiStrat {

	@Override
	public void onStop() throws JFException {
	}

	@Override
	public String getTag() {
		return "MAC";
	}

	@Override
	public void initialize() {
		Period minPeriod = Period.FIVE_MINS;
		IStop stop = StopFactory.getFixedAbsoluteStopInstance(100d); 
		AbsEntry entry = new SimpleMarketEntry(stop);
		AbsExit exit = new MAStopExit(minPeriod, 200);
		this.setSetup(new DoubleMACrossSetup(entry, exit, 
											minPeriod, Period.THIRTY_MINS,
											50, 200));
		this.setEmergency(new MuteOnDrawdownEmergency(this.getMaxDD(), minPeriod));
		this.setExposure(new StopLossExposure(this.getRiskPct()));
	}

}
