package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.Instrument;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.AbsEmergency;
import com.quantisan.JFFramework.AbstractSemiStrat;
import com.quantisan.JFUtil.JForexAccount;

public class MuteOnDrawdownEmergency extends AbsEmergency {
	@Override
	public String getName() {
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
		if (JForexAccount.isMaxDrawdownBroken(this.getMaxDD()))
			strategy.setSetup(SetupFactory.getNullEntry());
		
	}

}
