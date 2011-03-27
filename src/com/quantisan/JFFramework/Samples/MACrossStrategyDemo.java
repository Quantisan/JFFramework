package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.IBar;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.JFException;
import com.dukascopy.api.Library;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.*;
import com.quantisan.JFFramework.Trade.*;
import com.quantisan.JFUtil.Recorder;

@Library("JFQuantisan.jar")
public class MACrossStrategyDemo extends AbstractSemiStrat {
	
	@Override
	public void onStop() throws JFException {
		Recorder.record(getTag());
	}

	@Override
	public String getTag() {
		return "MAC";
	}

	@Override
	public void initialize() {
		Period minPeriod = Period.TEN_SECS;
		IStop stop = StopFactory.getFixedAbsoluteStopInstance(100d); 
		AbstractEntry entry = new SimpleMarketEntry(stop);
		AbstractExit exit = new MAStopExit(minPeriod, 200);
		this.setSetup(new SingleDualMASetup(entry, stop, exit, 
											minPeriod, 50, 200));
		this.setEmergency(new MuteOnDrawdownEmergency(this.getMaxDD(), minPeriod));
		this.setExposure(new StopLossExposure(this.getRiskPct()));
	}

	@Override
	public String toString() {
		return "MA Strategy Demo";
	}

	@Override
	public void onBar(Instrument instrument, Period period, IBar askBar,
			IBar bidBar) throws JFException {
		// TODO Auto-generated method stub
		
	}

}
