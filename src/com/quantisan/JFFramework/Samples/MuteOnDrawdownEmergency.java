package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.Instrument;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.AbstractEmergency;
import com.quantisan.JFFramework.AbstractSemiStrat;
import com.quantisan.JFFramework.Trade.NullFactory;
import com.quantisan.JFUtil.JForexAccount;

public class MuteOnDrawdownEmergency extends AbstractEmergency {
	public MuteOnDrawdownEmergency(double maxDD, Period checkPeriod) {
		super(maxDD, checkPeriod);
	}
	
	@Override
	public String toString() {
		return "Mute on Drawdown Emergency";
	}

	@Override
	public String getTag() {
		return "MOD";
	}

	@Override
	public void checkEmergency(AbstractSemiStrat strategy, Instrument instrument,
								Period period) 
	{
		if (period == getCheckPeriod()) {
			if (JForexAccount.isMaxDrawdownBroken(this.getMaxDD())) {
				strategy.setExposure(NullFactory.getNullExposure());
			}
		}
//		if (this.getNext() != null)
//			this.getNext().checkEmergency(strategy, instrument, period);
	}
}
